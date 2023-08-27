package com.example.empMgmtWithLogin.service;

import com.example.empMgmtWithLogin.entity.Employee;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface EmployeeService extends UserDetailsService {

    Employee findByUserName(String userName);

    List<Employee> findAll();

    Optional<Employee> findById(int id);

    void deleteById(int id);

    void updateDetails(Employee theEmployee);


}
