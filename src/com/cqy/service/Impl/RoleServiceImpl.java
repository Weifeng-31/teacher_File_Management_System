package com.cqy.service.Impl;

import com.cqy.dao.LoginDAO;
import com.cqy.dao.RoleDAO;
import com.cqy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.List;
import com.cqy.entity.*;
@Service("Role")
@Scope("prototype")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private LoginDAO loginDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public List getRole(){
        return roleDAO.getRole();

    }

    @Override
    public Integer queryAccount(){
        UserRole u = new UserRole();
        return loginDAO.find(u).size();
    }

    @Override
    public  boolean editRole(String roleId,String  roleName,String description){
        if(roleDAO.editRole(roleId,roleName,description)){
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public boolean checkRoleId(String roleId){
        if(roleDAO.checkRoleId(roleId)){
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public boolean addRole(String roleId,String roleName,String description){
        if(roleDAO.addRole(roleId,roleName,description)){
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public boolean deleteRole(String roleId){
        if(roleDAO.deleteRole(roleId)){
            return true;
        }
        else {
            return false;
        }
    }

}
