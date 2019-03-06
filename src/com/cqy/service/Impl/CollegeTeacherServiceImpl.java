package com.cqy.service.Impl;

import com.cqy.dao.*;
import com.cqy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("CollegeTeacher")
@Scope("prototype")
public class CollegeTeacherServiceImpl implements CollegeTeacherService {

    @Autowired
    private CollegeTeacherDAO collegeTeacherDAO;

    @Override
    public List getAllCollegeTeacher(String departId, int pageNum, int pageSize) {
        return collegeTeacherDAO.getAllCollegeTeacher(departId, pageNum, pageSize);
    }

    @Override
    public Integer getTotalCollegeAccount(String departId){
        return collegeTeacherDAO.getTotalCollegeAccount(departId);
    }

    @Override
    public List getCollegeTeacherByName(String name,String departId,int pageNum,int pageSize) {
        return collegeTeacherDAO.getCollegeTeacherByName(name,departId,pageNum,pageSize);
    }

    @Override
    public Integer getCollegeAccountByName(String name,String departId){
        return collegeTeacherDAO.getCollegeAccountByName(name,departId);
    }

    @Override
    public List getCollegeTeacherById(String userId,String departId,int pageNum,int pageSize) {
        return collegeTeacherDAO.getCollegeTeacherById(userId,departId,pageNum,pageSize);
    }

    @Override
    public Integer getCollegeAccountById(String userId,String departId){
        return collegeTeacherDAO.getCollegeAccountById(userId,departId);
    }

    @Override
    public List getCollegeTeacherByIdAndName(String userId,String name,String departId,int pageNum,int pageSize) {
        return collegeTeacherDAO.getCollegeTeacherByIdAndName(userId,name,departId,pageNum,pageSize);
    }

    @Override
    public Integer getCollegeAccountByIdAndName(String userId,String name,String departId){
        return collegeTeacherDAO.getCollegeAccountByIdAndName(userId,name,departId);
    }

}
