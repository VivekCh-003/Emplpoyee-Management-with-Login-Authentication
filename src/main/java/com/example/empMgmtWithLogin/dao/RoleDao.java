package com.example.empMgmtWithLogin.dao;

import com.example.empMgmtWithLogin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Integer> {
}
