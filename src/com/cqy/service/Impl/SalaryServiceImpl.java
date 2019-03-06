package com.cqy.service.Impl;
import com.cqy.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.List;
import com.cqy.service.*;
@Service("Salary")
@Scope("prototype")
public class SalaryServiceImpl implements SalaryService{
    @Autowired
    private SalaryDAO salaryDAO;

    @Override
    public List getSalary(String depart_id, int pageNum,int  pageSize){
        return salaryDAO.getSalary(depart_id,pageNum,pageSize);
    }

    @Override
    public Integer getAccount(String depart_id){
        return salaryDAO.getAccount(depart_id);
    }

    @Override
    public List getSalaryByName(String name,String depart_id,int pageNum,int pageSize){
        return salaryDAO.getSalaryByName(name,depart_id,pageNum,pageSize);
    }

    @Override
    public Integer getSalaryAccountByName(String name,String depart_id){
        return salaryDAO.getSalaryAccountByName(name,depart_id);
    }

    @Override
    public List  getSalaryById(String userId,String depart_id,int pageNum,int pageSize){
        return salaryDAO.getSalaryById(userId,depart_id,pageNum,pageSize);
    }

    @Override
    public Integer getSalaryAccountById(String userId,String depart_id){
        return salaryDAO.getSalaryAccountById(userId,depart_id);
    }

    @Override
    public List  getSalaryByIdAndName(String userId,String name,String depart_id,int pageNum,int pageSize){
        return salaryDAO.getSalaryByIdAndName(userId,name,depart_id,pageNum,pageSize);
    }

    @Override
    public Integer getSalaryAccountByIdAndName(String userId,String name,String depart_id){
        return salaryDAO.getSalaryAccountByIdAndName(userId,name,depart_id);
    }

    @Override
    public boolean editSalary(int intId, Date month, int baseSalary, int bonus){
        if(salaryDAO.editSalary(intId, month,baseSalary,bonus)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean checkDate(String userId,Date month){
        if(salaryDAO.checkDate(userId,month)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean saveSalary(String userId,String name,String depart_id, Date month,int baseSalary,int bonus){
        if(salaryDAO.saveSalary(userId,name,depart_id, month,baseSalary,bonus)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public List  searchByLike(String userId,String depart_id,String role_id){
        return salaryDAO.searchByLike(userId,depart_id,role_id);
    }

    @Override
    public boolean deleteSalary(String  id){
        if(salaryDAO.deleteSalary(id)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public List checkSalary(String depart_id,int pageNum,int pageSize){
        return salaryDAO.checkSalary(depart_id,pageNum,pageSize);
    }

    @Override
    public Integer checkSalaryAccount(String depart_id){
        return salaryDAO.checkSalaryAccount(depart_id);
    }

    @Override
    public List queryOwnSalary(String userId,int pageNum,int pageSize){
        return salaryDAO.queryOwnSalary(userId,pageNum,pageSize);
    }

    @Override
    public Integer queryOwnSalaryAccount(String userId){
        return salaryDAO.queryOwnSalaryAccount(userId);
    }

    @Override
    public List queryOwnSalaryByDate(String userId,int pageNum,int pageSize,Date month){
        return salaryDAO.queryOwnSalaryByDate(userId,pageNum,pageSize,month);
    }

    @Override
    public Integer queryOwnSalaryAccountByDate(String userId,Date month){
        return salaryDAO.queryOwnSalaryAccountByDate(userId,month);
    }

    @Override
    public List getMonth(String departId){
        return salaryDAO.getMonth(departId);
    }

    @Override
    public List getMonthList(Date month,String departId){
        return salaryDAO.getMonthList(month,departId);
    }

    @Override
    public List getTeacherSalary(String userId){
        return salaryDAO.getTeacherSalary(userId);
    }
}
