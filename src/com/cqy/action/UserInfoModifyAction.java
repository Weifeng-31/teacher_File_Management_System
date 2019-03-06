package com.cqy.action;

import com.cqy.service.UserInfoService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import com.cqy.util.*;

/**
 * Created by qiyu on 2018/2/20.
 */
@Controller
@Scope("prototype")
public class UserInfoModifyAction extends ActionSupport {
    private Map<String, Object> data;
    private Map<String, Object> dataMap;
    private Map<String, Object>  roleData;
    public UserInfoModifyAction()  {
        data = new HashMap<String, Object>();
        dataMap = new HashMap<String, Object>();
        roleData = new HashMap<String, Object>();
    }
    public Map<String, Object> getDataMap() {
        return dataMap;
    }
    String name;
    String user_id;
    String password;
    String role_id;
    String depart_id;
    Date born;
    String phone;
    String sex;
    String id_card;
    String address;
    String political;
    String profession_id;
    String  description;
    String filePath;
    String newPassword;
    String newPassword1;
    String qualification;
    String  subject;
    String nation;
    String email;

    /**
     *修改个人资料userInfoModify
     */
    @Autowired
    private UserInfoService userInfoModify;
    public String userInfoModify() throws Exception {
        IdCardUtil idCardInfo=new IdCardUtil(id_card);
        if(idCardInfo.getProvince()==null)
        {
            System.out.println("请输入有效的身份证信息！");
            dataMap.put("errorInfo", "请输入有效的身份证信息！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        else{
            String cityName= idCardInfo.getCityNames(id_card);
            String origin=idCardInfo.getProvince()+cityName;
            userInfoModify.userInfoModify(user_id,name,role_id,depart_id,idCardInfo.getBirthday(),phone,idCardInfo.getGender(),id_card,address,political,profession_id,description,filePath,qualification,subject,idCardInfo.getAge(),origin,nation,email);
            System.out.println("现在是工号为："+user_id+"名字为："+name+"的教师修改自己的个人资料，保存了路径为"+filePath+"的头像");
            data.put("data", "1");
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
          }
          return  SUCCESS;
        }

    /**
     *修改个人密码userPasswordModify
     */
    public String userPasswordModify() {
        if (newPassword.length()<6||newPassword1.length()<6){
            System.out.println("修改失败！密码小于六位");
            dataMap.put("errorInfo", "新密码不得小于六位数！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        else if(userInfoModify.checkUserPassword(user_id,password)){
            System.out.println("修改失败！旧密码错误！");
            dataMap.put("errorInfo", "修改失败！旧密码错误！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        else if(!userInfoModify.checkSecondPassword(newPassword,newPassword1)){
            System.out.println("修改失败！两次输入的密码不一致！");
            dataMap.put("errorInfo", "修改失败！两次输入的密码不一致！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        else if(userInfoModify.checkThirdPassword(password,newPassword)){
            System.out.println("修改失败！新旧密码不能一样！");
            dataMap.put("errorInfo", "修改失败！新旧密码不能一样！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        else {
            userInfoModify.userPasswordModify(user_id,newPassword);
            System.out.println("现在是工号为："+user_id+"名字为："+name+"的教师修改自己的密码");
            data.put("data", "1");
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
        }
        return  SUCCESS;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword1() {
        return newPassword1;
    }

    public void setNewPassword1(String newPassword1) {
        this.newPassword1 = newPassword1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getDepart_id() {
        return depart_id;
    }

    public void setDepart_id(String depart_id) {
        this.depart_id = depart_id;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getProfession_id() {
        return profession_id;
    }

    public void setProfession_id(String profession_id) {
        this.profession_id = profession_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}

