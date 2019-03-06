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
public class RoleAction extends ActionSupport {
    private Map<String, Object> data;
    private Map<String, Object> dataMap;
    private Map<String, Object> roleData;
    String roleId;
    String rolename;
    String description;
    String id;

    /**
     *查询角色方法queryRoleDict
     */
    @Autowired
    RoleService roleService;
    public String queryRole() {
        List roleList=roleService.getRole();
        int total = roleService.queryAccount();
        roleData.put("list", roleList);
        roleData.put("total", total);
        data.put("data", roleData);
        dataMap.put("results", data);
        dataMap.put("errorNo", "0");
        System.out.println("现在是查询角色字典，总数为"+total);
        return SUCCESS;
    }
    /**
     *编辑角色的方法editRole
     */
    public String editRole() {
        if((roleService.editRole(roleId,rolename,description))) {
            System.out.println("现在是编辑ID为"+roleId+"的角色");
            data.put("data", "1");
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
        }
        else{
            dataMap.put("errorInfo", "编辑失败！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        return  SUCCESS;
    }
    /**
     *新增角色的方法addRole
     */
    public String addRole() {

        if(!roleService.checkRoleId(roleId)){
            System.out.println("角色ID不能重复");
            dataMap.put("errorInfo", "角色ID不能重复！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        else if((roleService.addRole(roleId,rolename,description))) {
            System.out.println("现在是新增ID为"+roleId+"的角色");
            data.put("data", "1");
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
        }
        else{
            dataMap.put("errorInfo", "新增失败！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        return  SUCCESS;
    }
    /**
     *删除角色的方法addRole
     */
    public String deleteRole() {
        if(roleId.contains("1")||roleId.contains("2")||roleId.contains("3")){
            dataMap.put("errorInfo", "删除失败！此角色为内置重要角色，只可更改名称，不可删除！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        else if((roleService.deleteRole(roleId))) {
            System.out.println("现在是删除ID为"+roleId+"的角色");
            data.put("data", "1");
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
        }
        else{
            dataMap.put("errorInfo", "删除失败！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        return  SUCCESS;
    }












    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public RoleAction() {
        data = new HashMap<String, Object>();
        dataMap = new HashMap<String, Object>();
        roleData = new HashMap<String, Object>();
    }
    public Map<String, Object> getDataMap() {
        return dataMap;
    }
}
