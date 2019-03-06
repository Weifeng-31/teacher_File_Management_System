package com.cqy.service.Impl;

import com.cqy.dao.LoginDAO;
import com.cqy.service.GetDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.List;
import com.cqy.entity.*;

@Service("GetDict")
@Scope("prototype")
public class GetDictServiceImpl implements GetDictService {
    @Autowired
    private LoginDAO loginDAO;

    @Override
    public List getMenu(String roleId) {
        UserMenu u = new UserMenu();
        u.setRoleId(roleId);
        return loginDAO.find(u);
    }

    @Override
    public List getRoleDict() {
        UserRole u = new UserRole();
        return loginDAO.find(u);
    }

    @Override
    public List getDepartDict(){
        UserDepartment u = new UserDepartment();
        return loginDAO.find(u);
    }

    @Override
    public List getProfessionDict() {
        UserProfession u = new UserProfession();
        return loginDAO.find(u);
    }

}
