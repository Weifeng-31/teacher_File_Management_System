package com.cqy.service;
import com.cqy.entity.*;

import java.util.List;
public interface MenuService {
    List queryMenu(String roleId,int pageNum,int pageSize);
    Integer queryAccount(String roleId);
    boolean checkParentMenuId(String parentMenuId,String roleId);
    boolean editAdminMenuAction(Integer id,String roleId,String roleName,String menuId,String menuName,String menuHref,String menuIcon,String parentMenuId);
    boolean addAdminMenu(String roleId, String roleName, String menuId,String  menuName,String  menuHref,String  menuIcon,String  parentMenuId);
    boolean deleteMenuById(Integer id);
}
