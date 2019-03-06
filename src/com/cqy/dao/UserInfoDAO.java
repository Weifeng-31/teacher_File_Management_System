package com.cqy.dao;

import java.util.Date;

public interface UserInfoDAO {
    boolean userInfoModify(String user_id, String name, String role_id, String depart_id, Date born, String phone, String sex, String id_card, String address, String political, String  profession_id, String description, String filePath,String qualification,String subject,int age,String province,String nation,String email);
    boolean checkUserPassword(String user_id,String password);
    boolean checkSecondPassword(String newPassword,String newPassword1);
    boolean checkThirdPassword(String password,String newPassword);
    boolean userPasswordModify(String user_id,String newPassword);
}
