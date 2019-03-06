package com.cqy.service.Impl;

import com.cqy.dao.LoginDAO;
import com.cqy.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.cqy.entity.*;

import java.util.Date;

@Service("User")
@Scope("prototype")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDAO loginDAO;

    @Override
    public UserInfo checkLogin(String userId, String pass) {
            return loginDAO.checkLogin(userId,pass);
        }


    @Override
    public UserDepartment getDepartment(String departId) {
        return loginDAO.getDepartment(departId);
    }


    @Override
    public UserRole getRole(String roleId) {
        return loginDAO.getRole(roleId);
    }


    @Override
    public UserProfession getProfession(String professionId) {
        return loginDAO.getProfession(professionId);
    }

    @Override
    public Notice getNotice(String roleId,String departId) {
        return loginDAO.getNotice(roleId,departId);
    }

    @Override
    public Integer getAccount(String roleId,String departId){
        return  loginDAO.getAccount(roleId,departId);
    }

    @Override
    public boolean setLastLoginTime(Integer id){
        if(loginDAO.setLastLoginTime(id)){
            return true;
        }
        else {
            return false;
        }
    }
}

