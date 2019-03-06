package com.cqy.dao.Impl;

import com.cqy.dao.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
@Scope("prototype")
public class CollegeTeacherDAOImpl implements CollegeTeacherDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List getAllCollegeTeacher(String depart_id,int pageNum, int pageSize) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery(" from UserInfo where (departId =? and (roleId = '3'))");
            query.setParameter(0,depart_id);
            query.setFirstResult((pageNum - 1) * pageSize);//开始索引
            query.setMaxResults(pageSize);//取几条
            List menuList =query.list();
            return menuList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Integer getTotalCollegeAccount( String depart_id) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserInfo where (departId =? and (roleId = '3'))");
            query.setParameter(0,depart_id);
            List list =query.list();
            int total = list.size();
            return total;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List getCollegeTeacherByName(String name,String depart_id,int pageNum,int pageSize) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery(" from UserInfo  where name like CONCAT('%',?,'%') and (departId =? and (roleId = '3'))");
            query.setParameter(0,name);
            query.setParameter(1,depart_id);
            query.setFirstResult((pageNum - 1) * pageSize);//开始索引
            query.setMaxResults(pageSize);//取几条
            List menuList =query.list();
            return menuList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getCollegeAccountByName(String name,String depart_id) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserInfo  where name like CONCAT('%',?,'%') and (departId =? and (roleId = '3'))");
            query.setParameter(0,name);
            query.setParameter(1,depart_id);
            List list =query.list();
            int total = list.size();
            return total;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List getCollegeTeacherById(String user_id,String depart_id,int pageNum,int pageSize) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery(" from UserInfo  where userId like CONCAT('%',?,'%') and (departId =? and (roleId = '3'))");
            query.setParameter(0,user_id);
            query.setParameter(1,depart_id);
            query.setFirstResult((pageNum - 1) * pageSize);//开始索引
            query.setMaxResults(pageSize);//取几条
            List menuList =query.list();
            return menuList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getCollegeAccountById(String user_id,String depart_id) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserInfo  where userId like CONCAT('%',?,'%') and (departId =? and (roleId = '3'))");
            query.setParameter(0,user_id);
            query.setParameter(1,depart_id);
            List list =query.list();
            int total = list.size();
            return total;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List getCollegeTeacherByIdAndName(String user_id,String name,String depart_id,int pageNum,int pageSize) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserInfo  where name like CONCAT('%',?,'%')and userId like CONCAT('%',?,'%') and (departId =? and (roleId = '3'))");
            query.setParameter(0,name);
            query.setParameter(1,user_id);
            query.setParameter(2,depart_id);
            query.setFirstResult((pageNum - 1) * pageSize);//开始索引
            query.setMaxResults(pageSize);//取几条
            List menuList =query.list();
            return menuList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getCollegeAccountByIdAndName(String user_id,String name,String depart_id) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserInfo  where name like CONCAT('%',?,'%')and userId like CONCAT('%',?,'%') and (departId =? and (roleId = '3'))");
            query.setParameter(0,name);
            query.setParameter(1,user_id);
            query.setParameter(2,depart_id);
            List list =query.list();
            int total = list.size();
            return total;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
