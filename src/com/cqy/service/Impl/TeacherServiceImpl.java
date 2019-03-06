package com.cqy.service.Impl;

import com.cqy.dao.LoginDAO;
import com.cqy.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import com.cqy.entity.*;
import com.cqy.service.*;
@Service("Teacher")
@Scope("prototype")
public class TeacherServiceImpl implements TeacherService{
    @Autowired
    private LoginDAO loginDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public List getMember(String roleId,int pageNum, int pageSize) {
        return userDAO.getMember(roleId,pageNum,pageSize);
    }

    @Override
    public List exportTeacher(String roleId, String departId) {
        return userDAO.exportTeacher(roleId,departId);
    }
    @Override
    public Integer getAccount(String roleId) {
        return userDAO.getAccount(roleId);
    }

    @Override
    public List getTeacherByDepart_id(String roleId,String departId,int pageNum,int pageSize) {
        return userDAO.getTeacherByDepart_id(roleId,departId,pageNum,pageSize);
    }

    @Override
    public Integer getAccountByDepart_id(String roleId,String departId){
        return userDAO.getAccountByDepart_id(roleId,departId);
    }


    @Override
    public List getTeacherByName(String roleId,String name,int pageNum,int pageSize) {
        return userDAO.getTeacherByName(roleId,name,pageNum,pageSize);

    }

    @Override
    public Integer getAccountByName(String roleId,String name){
        return userDAO.getAccountByName(roleId,name);
    }


    @Override
    public List getTeacherById(String roleId,String userId,int pageNum,int pageSize) {
        return userDAO.getTeacherById(roleId,userId,pageNum,pageSize);
    }

    @Override
    public Integer getAccountById(String roleId,String userId){
            return userDAO.getAccountById(roleId,userId);
    }


    @Override
    public List getTeacherByDepart_idAndId(String roleId,String departId,String userId,int pageNum,int pageSize) {
        return userDAO.getTeacherByDepart_idAndId(roleId,departId,userId,pageNum,pageSize);
    }

    @Override
    public Integer getAccountByDepart_idAndId(String roleId,String departId,String userId){
        return userDAO.getAccountByDepart_idAndId(roleId,departId,userId);
    }

    @Override
    public List getTeacherDepart_idAndName(String roleId,String departId,String name,int pageNum,int pageSize) {
        return userDAO.getTeacherDepart_idAndName(roleId,departId,name,pageNum,pageSize);
    }

    @Override
    public Integer getAccountByDepart_idAndName(String roleId,String departId,String name){
        return userDAO.getAccountByDepart_idAndName(roleId,departId,name);
    }

    @Override
    public List getTeacherByIdAndName(String roleId,String userId,String name,int pageNum,int pageSize) {
        return userDAO.getTeacherByIdAndName(roleId,userId,name,pageNum,pageSize);

    }

    @Override
    public Integer getAccountByIdAndName(String roleId,String userId,String name){
        return userDAO.getAccountByIdAndName(roleId,userId,name);

    }
    @Override
    public List getTeacherByDepart_idAndIdAndName(String roleId,String userId,String name,String departId,int pageNum,int pageSize){
        return userDAO.getTeacherByDepart_idAndIdAndName(roleId,userId,name,departId,pageNum,pageSize);
    }

    @Override
    public Integer getAccountByDepart_idAndIdAndName(String roleId,String userId,String name,String departId){
        return userDAO.getAccountByDepart_idAndIdAndName(roleId,userId,name,departId);
    }

    @Override
    public boolean editTeacher(String userId, String name, String roleId, String departId, Date born, String phone, String sex, String idCard, String address, int state, String political, String professionId, String description, String filePath,String qualification,String subject,int age,String province,String nation,String email)
    {
        if(userDAO.editTeacher(userId,name,roleId,departId,born,phone,sex,idCard,address,state,political,professionId,description,filePath,qualification,subject,age,province,nation,email)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean checkUser_id(String userId)
    {
        if(userDAO.checkUser_id(userId)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean saveTeacher(String userId, String name, String roleId, String departId, Date born, String phone,String  sex, String idCard, String address,int state, String political, String professionId, String description,String filePath,String qualification,String subject,int age,String province,String nation,String email)
    {
        if(userDAO.saveTeacher(userId, name,roleId, departId, born, phone, sex, idCard, address, state, political, professionId, description,filePath,qualification,subject,age,province,nation,email)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public  boolean deleteTeacherById(String userId){
        if(userDAO.deleteTeacherById(userId)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public  boolean resetPwd(String userId){
        if(userDAO.resetPwd(userId)){
            return true;
        }
        else {
            return false;
        }
    }
}
