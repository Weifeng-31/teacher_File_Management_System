package com.cqy.service;

import java.util.Date;
import java.util.List;

public interface TeacherService {
    List getMember(String roleId, int pageNum, int pageSize);
    Integer getAccount(String roleId);
    List getTeacherByDepart_id(String roleId,String departId,int pageNum,int pageSize);
    Integer getAccountByDepart_id(String roleId,String departId);
    List getTeacherByName(String roleId,String name,int pageNum,int pageSize);
    Integer getAccountByName(String roleId,String name);
    List getTeacherById(String roleId,String userId,int pageNum,int pageSize);
    Integer getAccountById(String roleId,String userId);
    List getTeacherByDepart_idAndId(String roleId,String departId,String userId,int pageNum,int pageSize);
    Integer getAccountByDepart_idAndId(String roleId,String departId,String userId);
    List getTeacherDepart_idAndName(String roleId,String departId,String name,int pageNum,int pageSize);
    Integer getAccountByDepart_idAndName(String roleId,String departId,String name);
    List getTeacherByIdAndName(String roleId,String userId,String name,int pageNum,int pageSize);
    Integer getAccountByIdAndName(String roleId,String userId,String name);
    List getTeacherByDepart_idAndIdAndName(String roleId,String userId,String name,String departId,int pageNum,int pageSize);
    Integer getAccountByDepart_idAndIdAndName(String roleId,String userId,String name,String departId);
    boolean editTeacher(String userId, String name, String roleId, String departId, Date born, String phone, String sex, String idCard, String address, int state, String political, String professionId, String description, String filePath, String qualification,String subject,int age,String province,String nation,String email);
    boolean checkUser_id(String userId);
    boolean saveTeacher(String userId, String name, String roleId, String departId, Date born, String phone,String  sex, String idCard, String address,int state, String political, String professionId, String description,String filePath,String qualification,String subject,int age,String province,String nation,String email);
    boolean deleteTeacherById(String userId);
    boolean resetPwd(String userId);
    List exportTeacher(String roleId, String departId);
}
