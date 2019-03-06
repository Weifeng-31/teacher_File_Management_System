package com.cqy.service.Impl;

import com.cqy.dao.LoginDAO;
import com.cqy.dao.*;
import com.cqy.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.List;
import com.cqy.entity.*;

@Service("Menu")
@Scope("prototype")
public class MenuServiceImpl  implements MenuService {
    @Autowired
    private LoginDAO loginDAO;

    @Autowired
    private MenuDAO menuDAO;

    @Override
    public List queryMenu(String roleId,int pageNum,int pageSize){
        return menuDAO.getMenu(roleId,pageNum,pageSize);
    }

    @Override
    public Integer queryAccount(String roleId) {

            UserMenu u = new UserMenu();
            u.setRoleId(roleId);
            return loginDAO.find(u).size();
    }

    @Override
    public boolean checkParentMenuId(String parentMenuId,String roleId){
        if(menuDAO.checkParentMenuId(parentMenuId,roleId)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean editAdminMenuAction(Integer id,String roleId,String roleName,String menuId,String menuName,String menuHref,String menuIcon,String parentMenuId){
        if(menuDAO.editAdminMenuAction(id,roleId,roleName,menuId,menuName,menuHref,menuIcon,parentMenuId)){
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public boolean addAdminMenu(String roleId, String roleName, String menuId,String  menuName,String  menuHref,String  menuIcon,String  parentMenuId){
        if(menuDAO.addAdminMenu(roleId, roleName, menuId, menuName, menuHref, menuIcon, parentMenuId)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean deleteMenuById(Integer id){
        if(menuDAO.deleteMenuById(id)){
            return true;
        }
        else {
            return false;
        }
    }
}
