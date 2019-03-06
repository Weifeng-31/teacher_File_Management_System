package com.cqy.action;

import com.cqy.entity.UserInfo;
import com.cqy.service.CollegeTeacherService;
import com.cqy.service.TeacherService;
import com.cqy.util.ExportExcelUtils;
import com.cqy.util.IdCardUtil;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qiyu on 2018/2/14.
 */
public class CollegeTeacherAction extends ActionSupport {
    private Map<String, Object> dataMap;
    private Map<String, Object> data;
    private Map<String, Object> data2;

    String name;
    int pageNum;
    int pageSize;
    String userId;
    String password;
    String roleId;
    String departId;
    Date born;
    String phone;
    String sex;
    String idCard;
    String address;
    int state;
    String political;
    String professionId;
    String  description;
    String  userDepartId;
    String  filePath;
    String  departmentName;
    String qualification;
    String  subject;
    String nation;
    String email;



    /**
     *查询所在院系教师的方法queryCollegeTeacher
     */
    @Autowired
    private CollegeTeacherService collegeTeacherService;
    @Autowired
    private TeacherService teacherService;

    public String queryCollegeTeacher() {
        if((name==null&&userId==null)||(userId.equals("")&&name.equals(""))) {
            int total = collegeTeacherService.getTotalCollegeAccount(departId);
            List list = collegeTeacherService.getAllCollegeTeacher(departId,pageNum,pageSize);
            data2.put("list", list);
            data2.put("total", total);
            data.put("data", data2);
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
            System.out.println("现在是查询管理员所在院系的教师，总数为"+total);
        }
        else if(userId.equals("")){
            List list = collegeTeacherService.getCollegeTeacherByName(name,departId,pageNum,pageSize);
            int total = collegeTeacherService.getCollegeAccountByName(name,departId);
            data2.put("list", list);
            data2.put("total", total);
            data.put("data", data2);
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
            System.out.println("现在是根据管理员所在院系的教师名字"+name+"查询，总数为"+total);
        }
        else if(name.equals("")){
            int total = collegeTeacherService.getCollegeAccountById(userId,departId);
            List list = collegeTeacherService.getCollegeTeacherById(userId,departId,pageNum,pageSize);
            data2.put("list", list);
            data2.put("total", total);
            data.put("data", data2);
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
            System.out.println("现在是根据管理员所在院系的教师工号"+userId+"查询，总数为"+total);
        }
        else{
            int total = collegeTeacherService.getCollegeAccountByIdAndName(userId,name,departId);
            List list = collegeTeacherService.getCollegeTeacherByIdAndName(userId,name,departId,pageNum,pageSize);
            data2.put("list", list);
            data2.put("total", total);
            data.put("data", data2);
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
            System.out.println("现在是根据管理员所在院系的教师工号"+userId+"和姓名"+name+"精准查询，总数为"+total);
        }
        return SUCCESS;
    }

    /**
     *保存所在学院老师的方法saveCollegeTeacher
     */
    public String saveCollegeTeacher() throws Exception {
        IdCardUtil idCardInfo=new IdCardUtil(idCard);
        if ((!roleId.equals("3"))) {
            dataMap.put("errorInfo", "院系管理员只能添加普通教师！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");

        }else if ((roleId.equals("3"))&&departId.equals("1")) {
            dataMap.put("errorInfo", "该角色不能在系统管理部门！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        else if(!userDepartId.equals(departId)){
            System.out.println("只能添加本学院的教师！");
            data.put("data", "");
            dataMap.put("errorInfo", "只能添加管理员所在学院"+"（"+departmentName+"）"+"的教师！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        else if(!userId.matches("[0-9]+")||userId.length()<10){
            System.out.println("新增失败！工号为十位纯数字");
            dataMap.put("errorInfo", "工号为十位纯数字");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        else if (!teacherService.checkUser_id(userId)) {
            System.out.println("工号不能重复");
            data.put("data", "");
            dataMap.put("errorInfo", "工号不能重复");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        else if(idCardInfo.getProvince()==null) {
            System.out.println("请输入有效的身份证信息！");
            dataMap.put("errorInfo", "请输入有效的身份证信息！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        else {
            String cityName= idCardInfo.getCityNames(idCard);
            String origin=idCardInfo.getProvince()+cityName;
            if (teacherService.saveTeacher(userId, name, roleId, departId, idCardInfo.getBirthday(), phone, idCardInfo.getGender(), idCard, address, state, political, professionId, description,filePath,qualification,subject,idCardInfo.getAge(),origin,nation,email)) {
                System.out.println("现在是院系管理员新增工号为" + userId + "的教师");
                data.put("data", "1");
                dataMap.put("results", data);
                dataMap.put("errorNo", "0");
            }
        }
        return SUCCESS;
    }


    /**
     *生成EXCEL，导出本学院所有教师的信息表exportCollegeTeacher
     **/
    public String exportCollegeTeacher() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            String departId = request.getParameter("departId");
            String departmentName=request.getParameter("departmentName");
            HttpServletResponse response = ServletActionContext.getResponse();
            OutputStream out = response.getOutputStream();
            String fileName =departmentName+"教师信息表.xls";
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            List list = collegeTeacherService.getAllCollegeTeacher(departId,0,10000);
            ExportExcelUtils eeu = new ExportExcelUtils();
            HSSFWorkbook workbook = new HSSFWorkbook();
            List<List<String>> data = new ArrayList<List<String>>();
            for (int j = 0; j < list.size(); j++) {
                UserInfo u= (UserInfo)list.get(j);
                List rowData = new ArrayList();
                rowData.add(u.getUserId());
                rowData.add(u.getName());
                rowData.add(u.getSex());
                rowData.add(String.valueOf(u.getAge()));
                rowData.add(u.getNation());
                rowData.add(u.getPhone());
                rowData.add(u.getEmail());
                rowData.add(String.valueOf(u.getBorn()));
                rowData.add(u.getIdCard());
                rowData.add(u.getSubject());
                rowData.add(u.getProvince());
                rowData.add(u.getAddress());
                rowData.add(u.getLastLoginTime());
                data.add(rowData);
            }
            String[] headers = { "工号", "姓名","性别", "年龄","民族" , "联系方式","电子邮件","出生日期", "身份证号" , "专业","籍贯", "住址","最后登录时间" };
            eeu.exportExcel(workbook,0,departmentName+"教师信息表.xls" , headers, data, out);
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }
    @JSON(format = "yyyy-MM-dd")
    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getProfessionId() {
        return professionId;
    }

    public void setProfessionId(String professionId) {
        this.professionId = professionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserDepartId() {
        return userDepartId;
    }

    public void setUserDepartId(String userDepartId) {
        this.userDepartId = userDepartId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 构造方法
     */
    public CollegeTeacherAction(){
        //初始化Map对象
        dataMap = new HashMap<String, Object>();
        data = new HashMap<String, Object>();
        data2= new HashMap<String, Object>();
    }
    public Map<String, Object> getDataMap() {
        return dataMap;
    }

}
