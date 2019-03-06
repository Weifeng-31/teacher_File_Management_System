package com.cqy.dao.Impl;

import com.cqy.dao.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.cqy.util.Md5;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
@Scope("prototype")
public class UserInfoDAOImpl implements UserInfoDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean userInfoModify(String user_id, String name, String role_id, String depart_id, Date born, String phone, String sex, String id_card, String address, String political, String profession_id, String description, String filePath,String qualification,String subject,int age,String province,String nation,String email)
    {
        try {
            Session session=sessionFactory.getCurrentSession();
            if(filePath==null||filePath.equals("")){
                Query query = session.createQuery(" update UserInfo set name=?,roleId=?,departId=?,born=?,phone=?,sex=?,idCard=?,address=?,political=?,professionId=?,description=?,qualification=?,subject=?,age=?, province=?,nation=?,email=? where userId =?");
                query.setParameter(0,name);
                query.setParameter(1,role_id);
                query.setParameter(2,depart_id);
                query.setParameter(3,born);
                query.setParameter(4,phone);
                query.setParameter(5,sex);
                query.setParameter(6,id_card);
                query.setParameter(7,address);
                query.setParameter(8,political);
                query.setParameter(9,profession_id);
                query.setParameter(10,description);
                query.setParameter(11,qualification);
                query.setParameter(12,subject);
                query.setParameter(13,age);
                query.setParameter(14,province);
                query.setParameter(15,nation);
                query.setParameter(16,email);
                query.setParameter(17,user_id);
                query.executeUpdate();
                return true;
            }else {
                Query query = session.createQuery(" update UserInfo set name=?,roleId=?,departId=?,born=?,phone=?,sex=?,idCard=?,address=?,political=?,professionId=?,description=?,headUrl=?,qualification=?,subject=?,age=?, province=?,nation=?,email=? where userId =?");
                query.setParameter(0,name);
                query.setParameter(1,role_id);
                query.setParameter(2,depart_id);
                query.setParameter(3,born);
                query.setParameter(4,phone);
                query.setParameter(5,sex);
                query.setParameter(6,id_card);
                query.setParameter(7,address);
                query.setParameter(8,political);
                query.setParameter(9,profession_id);
                query.setParameter(10,description);
                query.setParameter(11,filePath);
                query.setParameter(12,qualification);
                query.setParameter(13,subject);
                query.setParameter(14,age);
                query.setParameter(15,province);
                query.setParameter(16,nation);
                query.setParameter(17,email);
                query.setParameter(18,user_id);
                query.executeUpdate();
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean userPasswordModify(String user_id,String password)
    {
        try {
            Session session=sessionFactory.getCurrentSession();
            Md5 md5=new Md5();
            String md5Pwd=md5.EncoderByMd5(password);
            Query query = session.createQuery(" update UserInfo set password=?where userId =?");
            query.setParameter(0,md5Pwd);
            query.setParameter(1,user_id);
            query.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean checkUserPassword(String user_id,String password) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Md5 md5=new Md5();
            String md5Pwd=md5.EncoderByMd5(password);
            Query query=session.createQuery("from UserInfo where userId=? and password=?");
            query.setParameter(0,user_id);
            query.setParameter(1,md5Pwd);
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
    public boolean checkSecondPassword(String newPassword,String newPassword1)
    {
        if(newPassword.equals(newPassword1)){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public boolean checkThirdPassword(String Password,String newPassword)
    {
        try {
            Md5 md5=new Md5();
            String md5Pwd=md5.EncoderByMd5(Password);
            String md5Pwd1=md5.EncoderByMd5(newPassword);
            if(md5Pwd.equals(md5Pwd1)){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
