package com.example.empMgmtWithLogin.service;

import com.example.empMgmtWithLogin.dao.EmployeeDao;
import com.example.empMgmtWithLogin.dao.RoleDao;
import com.example.empMgmtWithLogin.entity.Employee;
import com.example.empMgmtWithLogin.entity.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;
    private RoleDao roleDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao,RoleDao roleDao) {
        this.employeeDao = employeeDao;
        this.roleDao = roleDao;
    }

    @Override
    public Employee findByUserName(String userName) {
        return employeeDao.findByUserName(userName);
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public Optional<Employee> findById(int id) {
        return employeeDao.findById(id);
    }

    @Override
    public void deleteById(int id) {
        Optional<Employee> employee = employeeDao.findById(id);
        if(employee.isPresent()){
            employee.get().getRoles().clear();
            employeeDao.deleteById(id);
        }
    }



    @Override
    @Transactional
    public void updateDetails(Employee theEmployee) {
        Employee existingEmployee = employeeDao.findById(theEmployee.getId()).orElse(null);
        existingEmployee.setPassword(theEmployee.getPassword());

        if(existingEmployee != null){
            BeanUtils.copyProperties(theEmployee,existingEmployee,"id");
            existingEmployee.setEnabled(true);
            employeeDao.save(existingEmployee);
        }
    }



    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Employee employee = employeeDao.findByUserName(userName);

        if(employee == null){
            throw new UsernameNotFoundException("Invalid username or password");
        }

        Collection<SimpleGrantedAuthority> authorities = mapRolesToAuthorities(employee.getRoles());

        return new org.springframework.security.core.userdetails.User(employee.getUserName(), employee.getPassword(),
                authorities);
    }

    private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role tempRole : roles) {
            SimpleGrantedAuthority tempAuthority = new SimpleGrantedAuthority(tempRole.getName());
            authorities.add(tempAuthority);
        }

        return authorities;
    }
}

