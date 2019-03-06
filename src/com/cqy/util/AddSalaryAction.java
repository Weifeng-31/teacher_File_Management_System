package com.cqy.util;

import com.opensymphony.xwork2.ActionSupport;
import com.cqy.entity.*;

public class AddSalaryAction extends ActionSupport{

     public  String update() throws Exception {
         AddSalary addSalary=new AddSalary();
         for(int i=1;i<601;i++){
             TeacherSalary t=new TeacherSalary();
             String newUID=null;
             String name=null;
             String departID=null;
             if(i<10){
                  newUID="201410000"+i;
                 departID="2";
             }
             else if(i>=10&&i<100){
                  newUID="20141000"+i;
                  departID="2";
             }
            else if(i>=100&&i<1000){
                 if(i==100){
                     newUID="2014100"+i;
                     departID="2";
                 }
                 else if(i>100&&i<=200){
                     newUID="2014100"+i;
                     departID="3";
                 }
                 else if(i>200&&i<=300){
                     newUID="2014100"+i;
                     departID="4";
                 }
                 else if(i>300&&i<=400){
                     newUID="2014100"+i;
                     departID="5";
                 }
                 else if(i>400&&i<=500){
                     newUID="2014100"+i;
                     departID="6";
                 }
                 else if(i>500&&i<=600){
                     newUID="2014100"+i;
                     departID="7";
                 }
             }
              name="教师"+i;
              addSalary.addSalary(newUID,name,departID);
              System.out.println("新用户名："+newUID+"新姓名："+name+"部门ID"+departID);
         }

         return SUCCESS;
     }
}
