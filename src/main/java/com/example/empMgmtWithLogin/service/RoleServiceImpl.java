package com.example.empMgmtWithLogin.service;

import com.example.empMgmtWithLogin.dao.RoleDao;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }


}
