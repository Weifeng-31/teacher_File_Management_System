package com.cqy.util;
import com.cqy.entity.TeacherSalary;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddSalary {

    @Autowired
    private SessionFactory sessionFactory;

     public void addSalary(String userId,String name,String departID){
         try {
                 Session session=sessionFactory.getCurrentSession();
                 String  time = "2017-04-01";
                 SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
                 Date date = sdf.parse(time);
                 java.sql.Date sqlDate=new java.sql.Date(date.getTime());
                 TeacherSalary t=new TeacherSalary();
                 t.setUserId(userId);
                 t.setName(name);
                 t.setRoleId("3");
                 t.setDepartId(departID);
                 t.setMonth(sqlDate);
                 t.setBaseSalary(8400);
                 t.setBonus(950);
                 session.save(t);
                 session.clear();
         }catch (Exception e){
            e.printStackTrace();
        }
     }
}
