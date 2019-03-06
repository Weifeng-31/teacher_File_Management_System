package com.cqy.service.Impl;
import com.cqy.dao.*;
import com.cqy.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service("UserInfo")
@Scope("prototype")
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDAO userInfoDAO;

    @Override
    public boolean userInfoModify(String user_id, String name, String role_id, String depart_id, Date born, String phone, String sex, String id_card, String address, String political, String  profession_id, String description, String filePath,String qualification,String subject,int age,String province,String nation,String email){
        if(userInfoDAO.userInfoModify(user_id,name,role_id,depart_id,born,phone,sex,id_card,address,political,profession_id,description,filePath, qualification, subject,age,province, nation, email)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean checkUserPassword(String user_id,String password){
        if(userInfoDAO.checkUserPassword(user_id,password)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean checkSecondPassword(String newPassword,String newPassword1){
        if(userInfoDAO.checkSecondPassword(newPassword,newPassword1)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean checkThirdPassword(String password,String newPassword){
        if(userInfoDAO.checkThirdPassword(password,newPassword)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean userPasswordModify(String user_id,String newPassword){
        if(userInfoDAO.userPasswordModify(user_id,newPassword)){
            return true;
        }
        else {
            return false;
        }
    }

}
