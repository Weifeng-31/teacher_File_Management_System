package com.cqy.action;

import com.cqy.service.GetDictService;
import com.cqy.service.MenuService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
public class MenuAction extends ActionSupport {
    private Map<String, Object> dataMap;
    private Map<String, Object> data;
    private Map<String, Object> data2;

    /**
     * 构造方法
     */
    public MenuAction(){
        //初始化Map对象
        dataMap = new HashMap<String, Object>();
        data = new HashMap<String, Object>();
        data2= new HashMap<String, Object>();
    }
    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    int pageNum;
    int pageSize;
    private Integer id;
    private String roleId;
    private String roleName;
    private String menuId;
    private String menuName;
    private String menuHref;
    private String menuIcon;
    private String parentMenuId;

    /**
     *查询系统管理员菜单的方法queryAdminMenu
     */
    @Autowired
    private MenuService menuService;
    public String queryAdminMenu() {
        roleId="1";
        List menuList= menuService.queryMenu(roleId,pageNum,pageSize);
        int total = menuService.queryAccount(roleId);
        data2.put("list", menuList);
        data2.put("total", total);
        data.put("data", data2);
        dataMap.put("results", data);
        dataMap.put("errorNo", "0");
        System.out.println("现在是查询系统管理员的菜单，总数为"+total);
        return SUCCESS;
    }
    /**
     *查询院系管理员菜单的方法queryCollegeAdminMenu
     */
    public String queryCollegeAdminMenu() {
        roleId="2";
        List menuList= menuService.queryMenu(roleId,pageNum,pageSize);
        int total = menuService.queryAccount(roleId);
        data2.put("list", menuList);
        data2.put("total", total);
        data.put("data", data2);
        dataMap.put("results", data);
        dataMap.put("errorNo", "0");
        System.out.println("现在是查询院系管理员的菜单，总数为"+total);
        return SUCCESS;
    }
    /**
     *查询普通教师菜单的方法queryTeacherMenu
     */
    public String queryTeacherMenu() {
        roleId="3";
        List menuList= menuService.queryMenu(roleId,pageNum,pageSize);
        int total = menuService.queryAccount(roleId);
        data2.put("list", menuList);
        data2.put("total", total);
        data.put("data", data2);
        dataMap.put("results", data);
        dataMap.put("errorNo", "0");
        System.out.println("现在是查询普通教师的菜单，总数为"+total);
        return SUCCESS;
    }

    /**
     *编辑系统管理员菜单的方法editAdminMenu
     */
    public String editAdminMenu() {
        if(menuService.checkParentMenuId(parentMenuId,roleId))
        {
            System.out.println("父菜单ID不存在");
            dataMap.put("errorInfo", "编辑失败，父菜单ID不存在！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        else{
            menuService.editAdminMenuAction(id,roleId,roleName,menuId,menuName,menuHref,menuIcon,parentMenuId);
            System.out.println("现在是编辑ID为"+id+"的菜单");
            data.put("data", "1");
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
        }
        return SUCCESS;
    }
    /**
     *新增菜单的方法addAdminMenu
     */
    public String addAdminMenu() {
        if(roleName==null){
            System.out.println("没有选择角色名称");
            dataMap.put("errorInfo", "请勾选角色名称！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        else  if (!menuService.checkParentMenuId(menuId,roleId)) {
            System.out.println("菜单ID不能重复");
            dataMap.put("errorInfo", "菜单ID不能重复！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }else if(menuService.checkParentMenuId(parentMenuId,roleId)){
            System.out.println("父菜单ID不存在");
            dataMap.put("errorInfo", "父菜单ID不存在！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        else {
            if (menuService.addAdminMenu(roleId, roleName, menuId, menuName, menuHref, menuIcon, parentMenuId)) {
                System.out.println("现在是系统管理员新增菜单ID为" + menuId + "的管理员菜单");
                data.put("data", "1");
                dataMap.put("results", data);
                dataMap.put("errorNo", "0");
            }
        }
        return SUCCESS;
    }

    /**
     *删除菜单的方法queryTeacher
     */
    public String deleteMenu() {
        if(menuService.deleteMenuById(id)){
            System.out.println("现在是删除菜单id为"+id+"的菜单");
            data.put("data", "1");
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
            return  SUCCESS;
        }
        return  SUCCESS;
    }









    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuHref() {
        return menuHref;
    }

    public void setMenuHref(String menuHref) {
        this.menuHref = menuHref;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
