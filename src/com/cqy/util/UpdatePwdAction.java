package com.cqy.util;

import com.cqy.service.TeacherService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import com.cqy.entity.*;
import java.util.List;

public class UpdatePwdAction extends ActionSupport{
    @Autowired
    private TeacherService teacherService;
     public  String update() throws Exception {
         UpdatePwd up=new UpdatePwd();
         List<UserInfo> list = teacherService.getMember("3",1,1000);
         for(int i=0;i<list.size();i++){
              UserInfo uid=list.get(i);
              String uid2=uid.getUserId();
              String newPwd=uid2.substring(uid2.length()- 6,uid2.length());//截取工号后六位为默认密码
              Md5 md5=new Md5();
              String md5Pwd=md5.EncoderByMd5(newPwd);
              up.updateDate(md5Pwd,uid2);
             System.out.println(uid2+md5Pwd);
         }
         return SUCCESS;
     }
}
