package com.cqy.service;

import com.cqy.entity.*;


public interface LoginService {
    UserInfo checkLogin(String userId, String pass);
    UserDepartment getDepartment(String departId);
    UserRole getRole(String roleId);
    UserProfession getProfession(String professionId);
    Notice getNotice(String roleId,String departId);
    Integer getAccount(String roleId,String departId);
    boolean setLastLoginTime(Integer id);
}
