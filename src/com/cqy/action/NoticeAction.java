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
public class NoticeAction extends ActionSupport {
    private Map<String, Object> data;
    private Map<String, Object> dataMap;
    private Map<String, Object>  roleData;
    public NoticeAction() {
        data = new HashMap<String, Object>();
        dataMap = new HashMap<String, Object>();
        roleData = new HashMap<String, Object>();
    }
    public Map<String, Object> getDataMap() {
        return dataMap;
    }
    Integer  id;
    String roleId;
    String content;
    String userId;
    String departId;

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     *获取公告queryNotice
     */
    @Autowired
    private NoticeService noticeService;
    public String queryNotice() {
        List noticeList=noticeService.getNotice(roleId,departId);
        int total = noticeService.getAccount();
        roleData.put("list", noticeList);
        roleData.put("total", total);
        data.put("data", roleData);
        dataMap.put("results", data);
        dataMap.put("errorNo", "0");
        System.out.println("现在是获取公告，方法:queryNotice");
        return SUCCESS;
    }

    /**
     *编辑公告editNotice
     */
    public String editNotice() {
        if(noticeService.editNotice(id,roleId,content,departId)){
            dataMap.put("results", "data");
            dataMap.put("errorNo", "0");
            System.out.println("现在是编辑公告，ID为"+id);
            return SUCCESS;
        }
        else {
            dataMap.put("results", "");
            dataMap.put("errorInfo", "公告编辑失败");
            dataMap.put("errorNo", "1");
            System.out.println("公告编辑失败");
            return SUCCESS;
        }
    }

    /**
     *新增公告addNotice
     */
    public String addNotice() {
        if(roleId.equals("1")){
            dataMap.put("results", "");
            dataMap.put("errorInfo", "系统管理员无需添加公告！");
            dataMap.put("errorNo", "1");
            System.out.println("系统管理员无需添加公告");
            return SUCCESS;
        }
        else if(roleId.equals("2")||roleId.equals("3")){
            dataMap.put("results", "");
            dataMap.put("errorInfo", "该角色已有公告，无需重复添加！");
            dataMap.put("errorNo", "1");
            System.out.println("该角色已有公告，无需重复添加！");
            return SUCCESS;
        }
        else  if(noticeService.addNotice(roleId,content)){
            dataMap.put("results", "data");
            dataMap.put("errorNo", "0");
            System.out.println("现在是新增公告");
            return SUCCESS;
        }
        else {
            dataMap.put("results", "");
            dataMap.put("errorInfo", "公告新增失败");
            dataMap.put("errorNo", "1");
            System.out.println("公告新增失败");
            return SUCCESS;
        }
    }

    /**
     *删除公告queryNotice
     */
    public String deleteNotice() {
        if (roleId.equals("2") || roleId.equals("3")) {
            dataMap.put("results", "");
            dataMap.put("errorInfo", "内置重要角色的公告无法删除！");
            dataMap.put("errorNo", "1");
            System.out.println("内置重要角色的公告无法删除");
            return SUCCESS;
        } else if (noticeService.deleteNotice(id)) {
            dataMap.put("results", "data");
            dataMap.put("errorNo", "0");
            System.out.println("现在是删除公告");
            return SUCCESS;
        } else {
            dataMap.put("results", "");
            dataMap.put("errorInfo", "公告删除失败");
            dataMap.put("errorNo", "1");
            System.out.println("公告删除失败");
            return SUCCESS;
        }

    }
    /**
     *更新公告状态queryNotice
     */
    public String updateNotice() {
        noticeService.updateNotice(userId);
        dataMap.put("results", "已读");
        dataMap.put("errorNo", "0");
        System.out.println("现在是阅读公告，并更改公告状态");
        return  SUCCESS;
    }
}


