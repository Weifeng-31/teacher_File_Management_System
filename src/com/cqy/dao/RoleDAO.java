package com.cqy.dao;

import java.util.List;

public interface RoleDAO {
    List getRole();
    boolean editRole(String roleId,String  roleName,String description);
    boolean checkRoleId(String roleId);
    boolean addRole(String roleId,String roleName,String description);
    boolean deleteRole(String roleId);
}
