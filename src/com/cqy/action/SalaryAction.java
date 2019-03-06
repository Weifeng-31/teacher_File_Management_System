package com.cqy.action;

import com.cqy.entity.TeacherSalary;
import com.cqy.entity.UserDepartment;
import com.cqy.entity.UserInfo;
import com.cqy.service.*;
import com.cqy.util.ExportExcelUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.poi.hssf.usermodel.*;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Calendar.*;

public class SalaryAction extends ActionSupport {
    private Map<String, Object> dataMap;
    private Map<String, Object> data;
    private Map<String, Object> data2;
     String id;
     String userId;
     String name;
     String role_id;
     String depart_id;
     Date month;
     Integer baseSalary;
     Integer bonus;
     int pageNum;
     int pageSize;


    /**
     *查询工资的方法querySalary
     */
    @Autowired
    private SalaryService salaryService;
    public String querySalary() {
        if((name==null&&userId==null)||(userId.equals("")&&name.equals(""))) {
            int total = salaryService.getAccount(depart_id);
            List newList = salaryService.getSalary(depart_id,pageNum,pageSize);
            data2.put("list", newList);
            data2.put("total", total);
            data.put("data", data2);
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
            System.out.println("现在是查询所有教师工资，部门id为"+depart_id+"总数为"+total);

        }
        else if(userId.equals("")){
            List newList = salaryService.getSalaryByName(name,depart_id,pageNum,pageSize);
            int total = salaryService.getSalaryAccountByName(name,depart_id);
            data2.put("list", newList);
            data2.put("total", total);
            data.put("data", data2);
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
            System.out.println("现在是根据教师名字"+name+"查询工资，总数为"+total);
        }
        else if(name.equals("")){
            List newList = salaryService.getSalaryById(userId,depart_id,pageNum,pageSize);
            int total = salaryService.getSalaryAccountById(userId,depart_id);
            data2.put("list", newList);
            data2.put("total", total);
            data.put("data", data2);
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
            System.out.println("现在是根据教师工号"+userId+"查询工资，总数为"+total);
        }
        else{
            int total = salaryService.getSalaryAccountByIdAndName(userId,name,depart_id);
            List newList = salaryService.getSalaryByIdAndName(userId,name,depart_id,pageNum,pageSize);
            data2.put("list", newList);
            data2.put("total", total);
            data.put("data", data2);
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
            System.out.println("现在是根据管理员所在院系的教师工号"+userId+"和姓名"+name+"精准查询工资，总数为"+total);
        }
        return SUCCESS;
    }
    /**
     *编辑工资的方法editSalary
     */
    public String editSalary() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(month);
        int day = cal.get(DAY_OF_MONTH); //日
        int intId=Integer.valueOf(id);
        if(day!=1){
            data.put("data", "1");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
            dataMap.put("errorInfo", "每月必须以1号代表当月月份！");
            System.out.println("每月必须以1号代表当月月份！");
        }
        else if(salaryService.editSalary(intId, month,baseSalary,bonus)){
            data.put("data", "1");
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
            System.out.println("现在是编辑ID为"+id+"的工资");
        }
        return SUCCESS;
    }

    /**
     *新增工资的方法saveSalary
     */
    public String saveSalary() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(month);
        int day = cal.get(DAY_OF_MONTH); //日
        int mon = cal.get(MONTH) + 1; //月(从0开始, 一般加1，实际是否 Calendar 里面常量的值决定的)
        int year = cal.get(YEAR ); //年
        if(day!=1){
            data.put("data", "1");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
            dataMap.put("errorInfo", "每月必须以1号代表当月月份！");
            System.out.println("每月必须以1号代表当月月份！");
        }
        else if(!salaryService.checkDate(userId,month)){
            data.put("data", "1");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
            dataMap.put("errorInfo", "此教师"+year+"年"+mon+"月的工资已经添加，不能重复添加！");
            System.out.println("此教师"+year+"年"+mon+"月的工资已经添加，不能重复添加！");
        }
        else if(salaryService.saveSalary(userId,name,depart_id, month,baseSalary,bonus)){
            System.out.println("现在是保存用户为"+userId+"的工资");
            data.put("data", "1");
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
        }
        return SUCCESS;
    }

    /**
     *新增工资时工号联想与名字自动填充
     */
    public String search(){
        role_id="3";
        List<UserInfo> list=salaryService.searchByLike(userId,depart_id,role_id);
        if(list.size()!=0){
            dataMap.put("error", 0);
            dataMap.put("data", list);
            System.out.println("现在在自动联想教师工号与姓名！");
        }else {
            dataMap.put("error", 1);
            dataMap.put("data", "");
            System.out.println("自动联想时输入的教师工号不存在！");
        }
        return SUCCESS;
    }

    /**
     *删除工资的方法deleteSalary
     */
    public String deleteSalary() {
        if(salaryService.deleteSalary(id)){
            System.out.println("现在是删除id为"+id+"的工资记录");
            data.put("data", "1");
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
        }
        return SUCCESS;
    }

    /**
     *院系管理员导出本院教师的工资记录
     **/
    public String exportSalary() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            String departId = request.getParameter("departId");
            String departmentName=request.getParameter("departmentName");
            HttpServletResponse response = ServletActionContext.getResponse();
            OutputStream out = response.getOutputStream();
            String fileName =departmentName+"教师工资表.xls";
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            List monthList=salaryService.getMonth(departId);
            ExportExcelUtils eeu = new ExportExcelUtils();
            HSSFWorkbook workbook = new HSSFWorkbook();
            for(int i= 0;i<monthList.size();i++){
               List getMonthList=salaryService.getMonthList((java.sql.Date)monthList.get(i),departId);
                List<List<String>> data = new ArrayList<List<String>>();
                for (int j = 0; j < getMonthList.size(); j++) {
                    TeacherSalary u= (TeacherSalary)getMonthList.get(j);
                    List rowData = new ArrayList();
                    rowData.add(u.getUserId());
                    rowData.add(u.getName());
                    rowData.add(String.valueOf(u.getMonth()));
                    rowData.add(String.valueOf(u.getBaseSalary()));
                    rowData.add(String.valueOf(u.getBonus()));
                    rowData.add(String.valueOf(u.getTotal()));
                    data.add(rowData);
                }
                String[] headers = { "工号", "姓名","月份", "基本工资" , "奖金","总计"};
                eeu.exportExcel(workbook,i,monthList.get(i).toString() , headers, data, out);
            }
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    /**
     *检查工资的方法checkSalary
     */
    public String checkSalary()   {
        List  list =salaryService.checkSalary(depart_id,pageNum,pageSize);
        int total = salaryService.checkSalaryAccount(depart_id);
        if(total==0){
            data.put("data", "null");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
            dataMap.put("errorInfo", "温馨提示：院系管理员您好，本学院的教师已经全部有工资记录！");
            System.out.println("本学院的教师已经全部有工资记录！！");
        }
        else {
            data2.put("list", list);
            data2.put("total", total);
            data.put("data", data2);
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
            System.out.println("现在是检查院系是否有未添加工资的教师，本学院还有"+total+"位教师没有添加工资记录！");
        }
        return SUCCESS;
    }
    /**
     *教师查看自己工资的方法queryOwnSalary
     */
    public String queryOwnSalary()   {
        if((month==null)||(month.equals(""))){
            int total = salaryService.queryOwnSalaryAccount(userId);
            List  list =salaryService.queryOwnSalary(userId,pageNum,pageSize);
            if(total==0){
                data.put("data", "null");
                dataMap.put("results", data);
                dataMap.put("errorNo", "1");
                dataMap.put("errorInfo", "温馨提示：教师您好，您还没有工资记录，请联系所在院系管理员添加！");
                System.out.println("教师查看自己工资，没有工资记录！");
                return  SUCCESS;
            }
            else {
                Map session= ActionContext.getContext().getSession();
                session.put("salaryList",list);
                data2.put("list", list);
                data2.put("total", total);
                data.put("data", data2);
                dataMap.put("results", data);
                dataMap.put("errorNo", "0");
                System.out.println("现在是教师查看自己工资，共有"+total+"条工资记录！");
                return  SUCCESS;
            }
        }
        else if(month!=null){
            Calendar cal = Calendar.getInstance();
            cal.setTime(month);
            int day = cal.get(DAY_OF_MONTH); //日
            if(day!=1){
                data.put("data", "1");
                dataMap.put("results", data);
                dataMap.put("errorNo", "1");
                dataMap.put("errorInfo", "温馨提示：月份查询必须以每月的1号开始！");
                System.out.println("每月必须以1号代表当月月份！");
                return  SUCCESS;
            }
            else {
                int total=salaryService.queryOwnSalaryAccountByDate(userId,month);
                List  list =salaryService.queryOwnSalaryByDate(userId,pageNum,pageSize,month);
                data2.put("list", list);
                data2.put("total", total);
                data.put("data", data2);
                dataMap.put("results", data);
                dataMap.put("errorNo", "0");
                System.out.println("现在是教师根据月份查找工资，共有"+total+"条工资记录！");
                return  SUCCESS;
            }
        }
        return  SUCCESS;
    }


    /**
     *普通教师导出自己的所有工资记录
     **/
    public String exportTeacherSalary() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            String userId = request.getParameter("userId");
            String name = request.getParameter("name");
            HttpServletResponse response = ServletActionContext.getResponse();
            OutputStream out = response.getOutputStream();
            String fileName =name+"工资表.xls";
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            ExportExcelUtils eeu = new ExportExcelUtils();
            HSSFWorkbook workbook = new HSSFWorkbook();
                List list=salaryService.getTeacherSalary(userId);
                List<List<String>> data = new ArrayList<List<String>>();
                for (int j = 0; j < list.size(); j++) {
                    TeacherSalary u= (TeacherSalary)list.get(j);
                    List rowData = new ArrayList();
                    rowData.add(u.getUserId());
                    rowData.add(u.getName());
                    rowData.add(String.valueOf(u.getMonth()));
                    rowData.add(String.valueOf(u.getBaseSalary()));
                    rowData.add(String.valueOf(u.getBonus()));
                    rowData.add(String.valueOf(u.getTotal()));
                    data.add(rowData);
            }
            String[] headers = { "工号", "姓名","月份", "基本工资" , "奖金","总计"};
            eeu.exportExcel(workbook,0,name+"工资表" , headers, data, out);
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getDepart_id() {
        return depart_id;
    }

    public void setDepart_id(String depart_id) {
        this.depart_id = depart_id;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public Integer getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Integer baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }
    /**
     * 构造方法
     */
    public SalaryAction(){
        //初始化Map对象
        dataMap = new HashMap<String, Object>();
        data = new HashMap<String, Object>();
        data2= new HashMap<String, Object>();
    }
    public Map<String, Object> getDataMap() {
        return dataMap;
    }
}
