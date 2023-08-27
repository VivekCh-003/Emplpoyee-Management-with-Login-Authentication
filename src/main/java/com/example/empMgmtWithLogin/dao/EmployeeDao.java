package com.example.empMgmtWithLogin.dao;

import com.example.empMgmtWithLogin.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee,Integer> {
    Employee findByUserName(String userName);
}
