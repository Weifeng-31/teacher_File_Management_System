package com.cqy.dao.Impl;

import com.cqy.dao.MenuDAO;
import com.cqy.entity.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
@Scope("prototype")
public class MenuDAOImpl implements MenuDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List getMenu(String roleId, int pageNum, int pageSize) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserMenu where roleId = ?");
            query.setParameter(0,roleId);
            query.setFirstResult((pageNum - 1) * pageSize);//开始索引
            query.setMaxResults(pageSize);//取几条
            List menuList =query.list();
            return menuList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    @Override
    public boolean checkParentMenuId(String parentMenuId,String roleId){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserMenu where menuId=? and roleId=?");
            query.setParameter(0,parentMenuId);
            query.setParameter(1,roleId);
            List list =query.list();
            if(list.size()==0){
                return true;
            }
            else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editAdminMenuAction(Integer id,String roleId,String roleName,String menuId,String menuName,String menuHref,String menuIcon,String parentMenuId){
        try {
            Session session=sessionFactory.getCurrentSession();
            UserMenu menu =new UserMenu();
            menu.setId(id);
            menu.setRoleId(roleId);
            menu.setRoleName(roleName);
            menu.setMenuId(menuId);
            menu.setMenuName(menuName);
            menu.setMenuHref(menuHref);
            menu.setMenuIcon(menuIcon);
            menu.setParentMenuId(parentMenuId);
            session.merge(menu);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public  boolean addAdminMenu(String roleId, String roleName, String menuId,String  menuName,String  menuHref,String  menuIcon,String  parentMenuId){
        try {
            Session session=sessionFactory.getCurrentSession();
            UserMenu menu =new UserMenu();
            menu.setRoleId(roleId);
            menu.setRoleName(roleName);
            menu.setMenuId(menuId);
            menu.setMenuName(menuName);
            menu.setMenuHref(menuHref);
            menu.setMenuIcon(menuIcon);
            menu.setParentMenuId(parentMenuId);
            session.save(menu);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean deleteMenuById(Integer id){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("delete from UserMenu where id=?");
            query.setParameter(0,id);
            query.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
