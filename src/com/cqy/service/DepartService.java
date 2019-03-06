package com.cqy.service;

import java.util.List;

public interface DepartService {
    List queryDepart();
    Integer queryAccount();
    boolean checkDepartId(String departmentId);
    boolean addDepart(String departmentId,String departmentName);
    boolean editDepart(String departmentId,String departmentName);
    boolean checkDepart(String departmentId);
    boolean deleteDepart(String departmentId);
}
