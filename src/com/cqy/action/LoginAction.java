package com.cqy.action;
import com.cqy.service.LoginService;
import com.cqy.util.IdCardUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.cqy.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
@Scope("prototype")
public class LoginAction extends ActionSupport {
    @Autowired
    private LoginService loginService;
    private String userId;
    private String password;
    private String securityCode;

    /**
     * 系统登录
     */
    public String Login()  {
        UserInfo user = loginService.checkLogin(userId, password);
        if(user!=null){
            int getTeacherAccount=loginService.getAccount("3","");
            int getCollegeAdminAccount=loginService.getAccount("2","");
            int getSystemAdminAccount=loginService.getAccount("1","");
            int getCollegeTeacherAccount=loginService.getAccount("3",user.getDepartId());
            UserDepartment department= loginService.getDepartment(user.getDepartId());
            UserRole userRole= loginService.getRole(user.getRoleId());
            UserProfession profession= loginService.getProfession(user.getProfessionId());
            Notice notice= loginService.getNotice(user.getRoleId(),user.getDepartId());
            if(user.getState()==1){
                loginService.setLastLoginTime(user.getId());
                if(user.getRoleId().equals("1")) {
                    System.out.println("现在是"+department.getDepartmentName()+userRole.getRolename()+user.getName()+"登录");
                    Map session= ActionContext.getContext().getSession();
                    session.put("login",user);
                    session.put("userDepartment",department);
                    session.put("userRole",userRole);
                    session.put("user_profession",profession);
                    session.put("getTeacherAccount",getTeacherAccount);
                    session.put("getSystemAdminAccount",getSystemAdminAccount);
                    session.put("getCollegeAdminAccount",getCollegeAdminAccount);
                    session.put("role","admin");
                    return "systemAdmin";
                }
                else if(user.getRoleId().equals("2")){
                    System.out.println("现在是"+department.getDepartmentName()+userRole.getRolename()+user.getName()+"登录");
                    Map session= ActionContext.getContext().getSession();
                    session.put("login",user);
                    session.put("userDepartment",department);
                    session.put("userRole",userRole);
                    session.put("user_profession",profession);
                    session.put("notice",notice);
                    session.put("getTeacherAccount",getTeacherAccount);
                    session.put("getSystemAdminAccount",getSystemAdminAccount);
                    session.put("getCollegeAdminAccount",getCollegeAdminAccount);
                    session.put("getCollegeTeacherAccount",getCollegeTeacherAccount);
                    session.put("role","colAdmin");
                    return "collegeAdmin";
                }
                else{
                    System.out.println("现在是"+department.getDepartmentName()+userRole.getRolename()+user.getName()+"登录");
                    Map session= ActionContext.getContext().getSession();
                    session.put("login",user);
                    session.put("userDepartment",department);
                    session.put("userRole",userRole);
                    session.put("user_profession",profession);
                    session.put("notice",notice);
                    session.put("role","teacher");
                    return "teacher";
                }
            }
            else {
                this.addFieldError("userName", "账户"+userId+"已被禁用，请联系管理员！");
                System.out.println("账户被禁用");
                return INPUT;
            }
        }
        this.addFieldError("userName", "账号或者密码错误，请检查！");
        System.out.println("登陆密码错误");
        return INPUT;
    }


    /**
     * 安全退出
     */
    public String LoginOut(){
        Map session= ActionContext.getContext().getSession();
        session.clear();
        System.out.println("现在是退出登录");
        return  "success";
    }
    /**
     * 校验登录字段
     */
    public void validateLogin(){
        System.out.println("现在在校验登录字段");
        Map map = ActionContext.getContext().getSession();
        String serverCode = (String) map.get("securityCode");
        if(userId.length()<10){
            //this.addFieldError("userName", "工号不得少于十位数，请检查！");
        }
        else if(password.length()<6){
          //  this.addFieldError("userName", "密码不得少于六位数，请检查！");
        }
        else if(!(securityCode.equals("a"))&&(!serverCode.equals(securityCode))) {
            this.addFieldError("userName", "验证码错误！");
        }
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

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
}