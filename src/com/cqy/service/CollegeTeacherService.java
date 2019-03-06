package com.cqy.service;

import java.util.List;

public interface CollegeTeacherService {
    List getAllCollegeTeacher(String departId, int pageNum, int pageSize);
    Integer getTotalCollegeAccount(String departId);
    List getCollegeTeacherByName(String name,String departId,int pageNum,int pageSize);
    Integer getCollegeAccountByName(String name,String departId);
    List getCollegeTeacherById(String userId,String departId,int pageNum,int pageSize);
    Integer getCollegeAccountById(String userId,String departId);
    List  getCollegeTeacherByIdAndName(String userId,String name,String departId,int pageNum,int pageSize);
    Integer getCollegeAccountByIdAndName(String userId,String name,String departId);
}
