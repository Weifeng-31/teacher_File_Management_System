package com.cqy.action;

import com.cqy.entity.TeacherSalary;
import com.cqy.entity.UserDepartment;
import com.cqy.entity.UserInfo;
import com.cqy.service.DepartService;
import com.cqy.service.TeacherService;
import com.cqy.util.ExportExcelUtils;
import com.cqy.util.IdCardUtil;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.poi.hssf.usermodel.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by qiyu on 2018/2/16.
 */
@Controller
@Scope("prototype")
public class TeacherAction extends ActionSupport{

    private Map<String, Object> dataMap;
    private Map<String, Object> data;
    private Map<String, Object> data2;
    /**
     * 构造方法
     */
    public TeacherAction(){
        //初始化Map对象
        dataMap = new HashMap<String, Object>();
        data = new HashMap<String, Object>();
        data2= new HashMap<String, Object>();
    }
    public Map<String, Object> getDataMap() {
        return dataMap;
    }
    Integer id;
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
    String qualification;
    String  subject;
    String nation;
    String email;
    String ownUserId;

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private DepartService departService;
    public String queryMember() {
        if((name==null&&userId==null&&departId==null)||(userId.equals("")&&name.equals("")&&departId.equals(""))) {
            int total = teacherService.getAccount(roleId);
            List list = teacherService.getMember(roleId,pageNum,pageSize);
            data2.put("list", list);
            data2.put("total", total);
            data.put("data", data2);
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
            System.out.println("现在是查询人员，总数为"+total);
        }
        else if(userId.equals("")&&name.equals("")){
            int total = teacherService.getAccountByDepart_id(roleId,departId);
            List list = teacherService.getTeacherByDepart_id(roleId,departId,pageNum,pageSize);
            data2.put("list", list);
            data2.put("total", total);
            data.put("data", data2);
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
            System.out.println("现在是根据学院ID"+departId+"查询教师，总数为"+total);
        }
        else if(userId.equals("")&&departId.equals("")){
            int total = teacherService.getAccountByName(roleId,name);
            List list = teacherService.getTeacherByName(roleId,name,pageNum,pageSize);
            data2.put("list", list);
            data2.put("total", total);
            data.put("data", data2);
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
            System.out.println("现在是根据名字"+name+"查询教师，总数为"+total);
        }
        else if(name.equals("")&&departId.equals("")){
            int total = teacherService.getAccountById(roleId,userId);
            List list = teacherService.getTeacherById(roleId,userId,pageNum,pageSize);
            data2.put("list", list);
            data2.put("total", total);
            data.put("data", data2);
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
            System.out.println("现在是根据工号"+userId+"查询教师，总数为"+total);
        }
        else if(name.equals("")){
            int total = teacherService.getAccountByDepart_idAndId(roleId,departId,userId);
            List list = teacherService.getTeacherByDepart_idAndId(roleId,departId,userId,pageNum,pageSize);
            data2.put("list", list);
            data2.put("total", total);
            data.put("data", data2);
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
            System.out.println("现在是根据学院ID："+departId+"和工号："+userId+"组合查询教师，总数为"+total);
        }
        else if(userId.equals("")){
            int total = teacherService.getAccountByDepart_idAndName(roleId,departId,name);
            List list = teacherService.getTeacherDepart_idAndName(roleId,departId,name,pageNum,pageSize);
            data2.put("list", list);
            data2.put("total", total);
            data.put("data", data2);
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
            System.out.println("现在是根据学院ID："+departId+"和姓名："+name+"组合查询教师，总数为"+total);
        }
        else if(departId.equals("")){

            int total = teacherService.getAccountByIdAndName(roleId,userId,name);
            List list = teacherService.getTeacherByIdAndName(roleId,userId,name,pageNum,pageSize);
            data2.put("list", list);
            data2.put("total", total);
            data.put("data", data2);
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
            System.out.println("现在是根据学院ID："+departId+"和姓名："+name+"组合查询教师，总数为"+total);
        }
        else{
            int total = teacherService.getAccountByDepart_idAndIdAndName(roleId,userId,name,departId);
            List list = teacherService.getTeacherByDepart_idAndIdAndName(roleId,userId,name,departId,pageNum,pageSize);
            data2.put("list", list);
            data2.put("total", total);
            data.put("data", data2);
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
            System.out.println("现在是根据工号"+userId+"和姓名"+name+"和部门ID"+departId+"精准查询教师，总数为"+total);
        }
        return SUCCESS;
    }
    /**
     *编辑教师的方法editTeacher
     */
    public String editTeacher() throws Exception {
        if ((roleId.equals("1"))&&(!departId.equals("1"))) {
            dataMap.put("errorInfo", "系统管理员必须在系统管理部门！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");

        }else if ((roleId.equals("2")||roleId.equals("3"))&&departId.equals("1")) {
            dataMap.put("errorInfo", "该角色不能在系统管理部门！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        else{
            IdCardUtil idCardInfo=new IdCardUtil(idCard);
            if(idCardInfo.getProvince()==null)
            {
                System.out.println("请输入有效的身份证信息！");
                dataMap.put("errorInfo", "请输入有效的身份证信息！");
                dataMap.put("results", data);
                dataMap.put("errorNo", "1");
            }
            else
            {
                String cityName= idCardInfo.getCityNames(idCard);
                String origin=idCardInfo.getProvince()+cityName;
                teacherService.editTeacher(userId,name,roleId,departId,idCardInfo.getBirthday(),phone,idCardInfo.getGender(),idCard,address,state,political,professionId,description,filePath,qualification,subject,idCardInfo.getAge(),origin,nation,email);
                System.out.println("现在是ID为"+id+"编辑工号为"+userId+"的教师，保存了路径为"+filePath+"的头像");
                data.put("data", "1");
                dataMap.put("results", data);
                dataMap.put("errorNo", "0");
            }
        }
        return  SUCCESS;
    }
    /**
     *新增教师的方法saveTeacher
     */
    public String saveTeacher() throws Exception {
        IdCardUtil idCardInfo=new IdCardUtil(idCard);
         if ((roleId.equals("1"))&&(!departId.equals("1"))) {
            dataMap.put("errorInfo", "系统管理员必须在系统管理部门！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        else if ((roleId.equals("2")||roleId.equals("3"))&&departId.equals("1")) {
            dataMap.put("errorInfo", "该角色不能在系统管理部门！");
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
            dataMap.put("errorInfo", "工号不能重复！");
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
                System.out.println("现在是系统管理员新增工号为" + userId + "的教师");
                data.put("data", "1");
                dataMap.put("results", data);
                dataMap.put("errorNo", "0");
            }
            else{
                dataMap.put("errorInfo", "新增失败，请重试！");
                dataMap.put("results", data);
                dataMap.put("errorNo", "1");
            }
        }
        return SUCCESS;
    }
    /**
     *删除教师的方法queryTeacher
     */
    public String deleteTeacher() {
        if(ownUserId.equals(userId)){
            System.out.println("无法删除管理员自己！");
            dataMap.put("errorInfo", "无法删除系统管理员本身！");
            dataMap.put("results", data);
            dataMap.put("errorNo", "1");
        }
        else if(teacherService.deleteTeacherById(userId)){
            data.put("data", "1");
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
            System.out.println("现在是删除工号为"+userId+"的教师");
        }
        return  SUCCESS;
    }

    /**
     *重置密码的方法resetPwd
     */
    public String resetPwd() {
        if(teacherService.resetPwd(userId)){
            data.put("data", "1");
            dataMap.put("results", data);
            dataMap.put("errorNo", "0");
            System.out.println("现在是重置工号为"+userId+"的教师的密码为工号后六位");
        }
        return  SUCCESS;
    }
    /**
     *导出各个学院教师名单
     */
    public String exportTeacher() {
        try {
            HttpServletRequest req = ServletActionContext.getRequest();
            HttpServletResponse response = ServletActionContext.getResponse();
            OutputStream out = response.getOutputStream();
            String fileName ="教师信息表.xls";
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            List departList=departService.queryDepart();
            List<String> depaetID = new ArrayList();
            List<String> departName = new ArrayList();
            for(int i=0;i<departList.size();i++){
               UserDepartment userDepartment= (UserDepartment)departList.get(i);
                depaetID.add(userDepartment.getDepartmentId());
                departName.add(userDepartment.getDepartmentName());
            }
            ExportExcelUtils eeu = new ExportExcelUtils();
            HSSFWorkbook workbook = new HSSFWorkbook();
            for(int k=1;k<departList.size();k++){
                List list = teacherService.exportTeacher("3",depaetID.get(k));
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
                String[] headers = { "工号", "姓名","性别","年龄" , "民族", "联系方式","电子邮件","出生日期", "身份证号" , "专业","籍贯", "住址","最后登录时间" };
                    eeu.exportExcel(workbook,k-1, departName.get(k), headers, data, out);
            }
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getOwnUserId() {
        return ownUserId;
    }

    public void setOwnUserId(String ownUserId) {
        this.ownUserId = ownUserId;
    }
}
