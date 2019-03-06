package com.cqy.util;

import com.cqy.entity.UserInfo;
import com.cqy.service.TeacherService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UpdateUIDAction extends ActionSupport{
    @Autowired
    private TeacherService teacherService;
     public  String update() throws Exception {
         UpdateUID up=new UpdateUID();
         List<UserInfo> list = teacherService.getMember("3",1,1000);
         for(int i=1;i<list.size()+1;i++){
             UserInfo uid=list.get(i-1);
             int id=uid.getId();
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
              up.updateUID(newUID,name,departID,id);
              System.out.println(id+"新用户名："+newUID+"新姓名："+name+"部门ID"+departID);
         }

         return SUCCESS;
     }
}
