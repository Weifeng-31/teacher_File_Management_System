package com.cqy.util;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
public class UpdatePwd {
    @Autowired
    private SessionFactory sessionFactory;

     public void updateDate(String pwd,String uid){
         try {
             Session session=sessionFactory.getCurrentSession();
                 Query query = session.createQuery(" update UserInfo set password=?where userId =?");
                 query.setParameter(0,pwd);
                 query.setParameter(1,uid);
                 query.executeUpdate();
                 session.clear();
         }catch (Exception e){
            e.printStackTrace();
        }
     }
}
