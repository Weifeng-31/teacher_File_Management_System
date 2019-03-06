package com.cqy.service.Impl;

import com.cqy.dao.LoginDAO;
import com.cqy.dao.DepartDAO;
import com.cqy.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.List;
import com.cqy.entity.*;

@Service("Depart")
@Scope("prototype")
public class DepartServiceImpl implements DepartService {

    @Autowired
    private LoginDAO loginDAO;

    @Autowired
    private DepartDAO departDAO;


    @Override
    public List queryDepart() {
        return departDAO.getDepart();
    }

    @Override
    public Integer queryAccount(){
            UserDepartment u = new UserDepartment();
            return loginDAO.find(u).size();
    }

    @Override
    public boolean checkDepartId(String departmentId){
        if(departDAO.checkDepartId(departmentId)){
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public boolean addDepart(String departmentId,String departmentName){
        if(departDAO.addDepart(departmentId,departmentName)){
            return true;
        }
        else {
            return false;
        }

    }
    @Override
    public boolean editDepart(String departmentId,String departmentName){
        if(departDAO.editDepart(departmentId,departmentName)){
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public boolean checkDepart(String departmentId){
        if(departDAO.checkDepart(departmentId)){
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public boolean deleteDepart(String departmentId){
        if(departDAO.deleteDepart(departmentId)){
            return true;
        }
        else {
            return false;
        }
    }
}
