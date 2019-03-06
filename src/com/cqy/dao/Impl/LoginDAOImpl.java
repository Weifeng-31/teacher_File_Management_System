package com.cqy.dao.Impl;

import com.cqy.dao.*;
import com.cqy.entity.*;
import com.cqy.util.Md5;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
@Scope("prototype")
public class LoginDAOImpl implements LoginDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private HibernateTemplate ht;
    @Autowired
    public void setHt(HibernateTemplate ht) {
        this.ht = ht;
    }

    private HibernateTemplate getHt() {
        ht.setCacheQueries(true);
        ht.getSessionFactory().getCurrentSession().setHibernateFlushMode(FlushMode.AUTO);
        return ht;
    }

    @Override
    public boolean add(Object o) {
        try {
            this.getHt().save(o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Object o) {
        try {
            this.getHt().delete(o);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Object o) {
        try {
            this.getHt().update(o);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List find(Object o) {
        return this.getHt().findByExample(o);
    }

    @Override
    public UserInfo checkLogin(String user_id, String password){
        UserInfo teacherInfo =null;
        try{
            Session session=sessionFactory.getCurrentSession();
            Md5 md5=new Md5();
            String md5Pwd=md5.EncoderByMd5(password);
            System.out.println("MD5校验登录密码，MD5码："+md5Pwd);
            Query query=session.createQuery("from UserInfo where userId=? and password=?");
            query.setParameter(0,user_id);
            query.setParameter(1,md5Pwd);
            query.setMaxResults(1);
            teacherInfo =(UserInfo)query.uniqueResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return teacherInfo;
    }

    @Override
    public UserDepartment getDepartment(String departId){
        UserDepartment userDepartment =null;
        try{
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserDepartment where departmentId = ? ");
            query.setParameter(0,departId);
            userDepartment =(UserDepartment)query.uniqueResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return userDepartment;
    }

    @Override
    public UserRole getRole(String roleId){
        UserRole userRole =null;
        try{
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserRole where roleId = ?");
            query.setParameter(0,roleId);
            userRole =(UserRole)query.uniqueResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return userRole;
    }
    @Override
    public UserProfession getProfession(String professionId){
        UserProfession userProfession =null;
        try{
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserProfession where professionId = ?");
            query.setParameter(0,professionId);
            userProfession =(UserProfession)query.uniqueResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return userProfession;
    }

    @Override
    public Notice getNotice(String roleId,String departId){
        Notice notice =null;
        try{
            Session session=sessionFactory.getCurrentSession();
            if(roleId.equals("2")){
                Query query=session.createQuery("from Notice where roleId = ?");
                query.setParameter(0,roleId);
                notice =(Notice)query.uniqueResult();
            }
            else {
                Query query=session.createQuery("from Notice where roleId = ? and departId=?");
                query.setParameter(0,roleId);
                query.setParameter(1,departId);
                notice =(Notice)query.uniqueResult();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return notice;
    }

    @Override
    public Integer getAccount(String roleId,String departId){
        int total;
        try {
            Session session=sessionFactory.getCurrentSession();
            if(departId.equals("")){
                Query query=session.createQuery("from UserInfo where roleId=?");
                query.setParameter(0,roleId);
                List list =query.list();
                total = list.size();
            }
            else {
                Query query=session.createQuery("from UserInfo where roleId=? and departId=?");
                query.setParameter(0,roleId);
                query.setParameter(1,departId);
                List list =query.list();
                total = list.size();
            }
            return total;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public  boolean setLastLoginTime(Integer id){
        try {
            Session session=sessionFactory.getCurrentSession();
            long timeStamp = System.currentTimeMillis();  //获取当前时间戳,也可以是你自已给的一个随机的或是别人给你的时间戳(一定是long型的数据)
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
            String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));   // 时间戳转换成时间
            Query query = session.createQuery(" update UserInfo set lastLoginTime=? where id =?");
            query.setParameter(0,sd);
            query.setParameter(1,id);
            query.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
