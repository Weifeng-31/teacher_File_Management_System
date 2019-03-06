package com.cqy.action;
import com.cqy.service.DepartService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by qiyu on 2018/2/19.
 */
@Controller
@Scope("prototype")
public class DepartAction extends ActionSupport {
    private Map<String, Object> data;
    private Map<String, Object> dataMap;
    private Map<String, Object> departMap;
    String departmentId;
    String departmentName;

    /**
     *查询学院方法queryRoleDict
     */
    @Autowired
    private DepartService departService;
    public String queryDepart() {
        List DepartList=departService.queryDepart();
        int total = departService.queryAccount();
        departMap.put("list", DepartList);
        departMap.put("total", total);
        data.put("data", departMap);
        dataMap.put("results", data);
        dataMap.put("errorNo", "0");
        System.out.println("现在是查询角色字典，总数为"+total);
        return SUCCESS;
    }

    /**
     *新增学院的方法addDepart
     */
    public String addDepart() {
        if(!departService.checkDepartId(departmentId)){
            System.out.println("学院ID不能重复");
            dataMap.put("errorInfo", "学院ID不能重复！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        else if((departService.addDepart(departmentId,departmentName))) {
            System.out.println("现在是新增ID为"+departmentId+"的学院");
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
     *编辑学院的方法editDepart
     */
    public String editDepart() {
        if((departService.editDepart(departmentId,departmentName))) {
            System.out.println("现在是编辑ID为"+departmentId+"的学院");
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
     *删除学院的方法deleteDepart
     */
    public String deleteDepart() {
        if(!(departService.checkDepart(departmentId))) {
            System.out.println("ID为"+departmentId+"的学院正在使用，无法删除！");
            dataMap.put("errorInfo", "要删除的学院正在使用，无法删除！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        else  if(departmentId.equals("1")){
            dataMap.put("errorInfo", "删除失败！此部门为内置重要部门，只可更改名称，不可删除！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        else if((departService.deleteDepart(departmentId))) {
            System.out.println("现在是删除ID为"+departmentId+"的学院");
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


    public DepartAction() {
        data = new HashMap<String, Object>();
        dataMap = new HashMap<String, Object>();
        departMap = new HashMap<String, Object>();
    }
    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
