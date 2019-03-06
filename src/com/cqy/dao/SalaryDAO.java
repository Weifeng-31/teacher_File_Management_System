package com.cqy.dao;
import java.sql.Date;
import java.util.List;

public interface SalaryDAO {
    List getSalary(String depart_id, int pageNum, int  pageSize);
    Integer getAccount(String depart_id);
    List getSalaryByName(String name,String depart_id,int pageNum,int pageSize);
    Integer getSalaryAccountByName(String name,String depart_id);
    List  getSalaryById(String userId,String depart_id,int pageNum,int pageSize);
    Integer getSalaryAccountById(String userId,String depart_id);
    List  getSalaryByIdAndName(String userId,String name,String depart_id,int pageNum,int pageSize);
    Integer getSalaryAccountByIdAndName(String userId,String name,String depart_id);
    boolean editSalary(Integer intId, Date month, Integer baseSalary, Integer bonus);
    boolean checkDate(String userId,Date month);
    boolean saveSalary(String userId,String name,String depart_id, Date month,Integer baseSalary,Integer bonus);
    List searchByLike(String userId,String depart_id,String role_id);
    boolean deleteSalary(String  id);
    List checkSalary(String depart_id,int pageNum,int pageSize);
    Integer checkSalaryAccount(String depart_id);
    Integer queryOwnSalaryAccount(String userId);
    List queryOwnSalary(String userId,int pageNum,int pageSize);
    Integer queryOwnSalaryAccountByDate(String userId,Date month);
    List queryOwnSalaryByDate(String userId,int pageNum,int pageSize,Date month);
    List getMonth(String departId);
    List getMonthList(Date month,String departId);
    List getTeacherSalary(String userId);
}
