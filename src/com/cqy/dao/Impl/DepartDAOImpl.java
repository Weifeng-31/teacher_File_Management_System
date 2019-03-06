package com.cqy.dao.Impl;

import com.cqy.dao.*;
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
public class DepartDAOImpl implements DepartDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List getDepart(){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserDepartment");
            List menuList =query.list();
            return menuList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
    @Override
    public boolean checkDepartId(String departmentId) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserDepartment where departmentId=?");
            query.setParameter(0,departmentId);
            List list =query.list();
            if(list.size()==0){
                return true;
            }
            else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean addDepart(String departmentId,String departmentName){
        try {
            Session session=sessionFactory.getCurrentSession();
            UserDepartment userDepartment =new UserDepartment();
            userDepartment.setDepartmentId(departmentId);
            userDepartment.setDepartmentName(departmentName);
            userDepartment.setCss("layui-badge layui-bg-cyan");
            session.save(userDepartment);
            Notice notice =new Notice();
            notice.setDepartId(departmentId);
            notice.setRoleId("3");
            notice.setContent("新增"+departmentName+"公告，待编辑！");
            session.saveOrUpdate(notice);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean editDepart(String departmentId,String departmentName){
        try {
            Session session=sessionFactory.getCurrentSession();
            UserDepartment userDepartment =new UserDepartment();
            userDepartment.setDepartmentId(departmentId);
            userDepartment.setDepartmentName(departmentName);
            userDepartment.setCss("layui-badge layui-bg-orange");
            session.merge(userDepartment);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteDepart(String departmentId) {
        try {
            String[] department=departmentId.split(",");
            for (int i = 0; i < department.length; i++){
                Session session=sessionFactory.getCurrentSession();
                Query query=session.createQuery("delete from UserDepartment where departmentId=?");
                query.setParameter(0,department[i]);
                query.executeUpdate();
                Query queryNotice=session.createQuery("delete from Notice where departId=?");
                queryNotice.setParameter(0,department[i]);
                queryNotice.executeUpdate();
            }
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkDepart(String departmentId) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserInfo where departId=?");
            query.setParameter(0,departmentId);
            List list =query.list();
            if(list.size()==0){
                return true;
            }
            else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}

