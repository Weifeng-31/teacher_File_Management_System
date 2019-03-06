package com.cqy.action;
import com.cqy.service.*;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@Scope("prototype")
public class GetDictActon  extends ActionSupport{
    private Map<String, Object> data;
    private Map<String, Object> dataMap;
    private String roleId;

    @Autowired
    private GetDictService getDictService;


    public String getAdminMenu() {
        roleId="1";
        List menuList = getDictService.getMenu(roleId);
        data.put("data", menuList);
        dataMap.put("results", data);
        dataMap.put("errorNo", "0");
        System.out.println("现在是获取系统管理员的菜单列表");
        return SUCCESS;
    }

    public String getCollegeAdminMenu() {
        roleId="2";
        List menuList = getDictService.getMenu(roleId);
        data.put("data", menuList);
        dataMap.put("results", data);
        dataMap.put("errorNo", "0");
        System.out.println("现在是获取院系管理员的菜单列表");
        return SUCCESS;
    }

    public String getTeacherMenu() {
        roleId="3";
        List menuList = getDictService.getMenu(roleId);
        data.put("data", menuList);
        dataMap.put("results", data);
        dataMap.put("errorNo", "0");
        System.out.println("现在是获取普通教师的菜单列表");
        return SUCCESS;
    }

    public String getRoleDict() {
        List roleDictList=getDictService.getRoleDict();
        data.put("data", roleDictList);
        dataMap.put("results", data);
        dataMap.put("errorNo", "0");
        System.out.println("现在是返回角色字典参数，方法:getRoleDict");
        return SUCCESS;
    }

    public String getDepartDict() {
        List roleList=getDictService.getDepartDict();
        data.put("data", roleList);
        dataMap.put("results", data);
        dataMap.put("errorNo", "0");
        System.out.println("现在是返回学院字典参数,方法:getDepartDict");
        return SUCCESS;
    }


    public String getProfessionDict() {
        List roleList=getDictService.getProfessionDict();
        data.put("data", roleList);
        dataMap.put("results", data);
        dataMap.put("errorNo", "0");
        System.out.println("现在是返回职称字典参数,方法:getProfessionDict");
        return SUCCESS;
    }


    public GetDictActon() {
        data = new HashMap<String, Object>();
        dataMap = new HashMap<String, Object>();
    }
    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}
