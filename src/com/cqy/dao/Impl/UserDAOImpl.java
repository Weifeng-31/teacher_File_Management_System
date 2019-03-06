package com.cqy.dao.Impl;

import com.cqy.dao.UserDAO;
import com.cqy.entity.*;
import com.cqy.util.Md5;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
@Scope("prototype")
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List getMember(String roleId,int pageNum,int pageSize) {
        try {
            Session session=sessionFactory.getCurrentSession();
            if(roleId.equals("3")){
                Query query=session.createQuery("from UserInfo where roleId = ? order by userId asc");
                query.setParameter(0,roleId);
                query.setFirstResult((pageNum - 1) * pageSize);//开始索引
                query.setMaxResults(pageSize);//取几条
                List menuList =query.list();
                return menuList;
            }
          else{
                Query query=session.createQuery("from UserInfo where roleId != '3' order by userId asc");
                query.setFirstResult((pageNum - 1) * pageSize);//开始索引
                query.setMaxResults(pageSize);//取几条
                List menuList =query.list();
                return menuList;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List exportTeacher(String roleId, String departId){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserInfo where roleId = ? and departId=?");
            query.setParameter(0,roleId);
            query.setParameter(1,departId);
            List menuList =query.list();
            return menuList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer getAccount(String roleId) {
        try {
            Session session=sessionFactory.getCurrentSession();
            int total;
            if(roleId.equals("3")){
                Query query=session.createQuery("from UserInfo where roleId = ?");
                query.setParameter(0,roleId);
                List list =query.list();
                total = list.size();
            }
            else {
                Query query=session.createQuery("from UserInfo where roleId != '3'");
                List list =query.list();
                total = list.size();
            }
            return total;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer getAccountByDepart_id(String roleId,String departId) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserInfo where roleId = ? and departId=?");
            query.setParameter(0,roleId);
            query.setParameter(1,departId);
            List list =query.list();
            int total = list.size();
            return total;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List getTeacherByDepart_id(String roleId,String departId,int pageNum,int pageSize){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserInfo where roleId = ? and departId=?");
            query.setParameter(0,roleId);
            query.setParameter(1,departId);
            query.setFirstResult((pageNum - 1) * pageSize);//开始索引
            query.setMaxResults(pageSize);//取几条
            List menuList =query.list();
            return menuList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List getTeacherByName(String roleId,String name,int pageNum,int pageSize){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserInfo where roleId = ? and name like  CONCAT('%',?,'%')");
            query.setParameter(0,roleId);
            query.setParameter(1,name);
            query.setFirstResult((pageNum - 1) * pageSize);//开始索引
            query.setMaxResults(pageSize);//取几条
            List menuList =query.list();
            return menuList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Integer getAccountByName(String roleId,String name) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserInfo where roleId = ? and name like  CONCAT('%',?,'%')");
            query.setParameter(0,roleId);
            query.setParameter(1,name);
            List list =query.list();
            int total = list.size();
            return total;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List getTeacherById(String roleId,String userId,int pageNum,int pageSize){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserInfo where roleId = ? and userId like  CONCAT('%',?,'%')");
            query.setParameter(0,roleId);
            query.setParameter(1,userId);
            query.setFirstResult((pageNum - 1) * pageSize);//开始索引
            query.setMaxResults(pageSize);//取几条
            List menuList =query.list();
            return menuList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
    }
    }

    @Override
    public Integer getAccountById(String roleId,String userId){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserInfo where roleId = ? and userId like  CONCAT('%',?,'%')");
            query.setParameter(0,roleId);
            query.setParameter(1,userId);
            List list =query.list();
            int total = list.size();
            return total;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List getTeacherByDepart_idAndId(String roleId,String departId,String userId,int pageNum,int pageSize){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserInfo where roleId = ? and departId =? and userId like  CONCAT('%',?,'%')");
            query.setParameter(0,roleId);
            query.setParameter(1,departId);
            query.setParameter(2,userId);
            query.setFirstResult((pageNum - 1) * pageSize);//开始索引
            query.setMaxResults(pageSize);//取几条
            List menuList =query.list();
            return menuList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer getAccountByDepart_idAndId(String roleId,String departId,String userId){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserInfo where roleId = ? and departId =? and userId like  CONCAT('%',?,'%')");
            query.setParameter(0,roleId);
            query.setParameter(1,departId);
            query.setParameter(2,userId);
            List list =query.list();
            int total = list.size();
            return total;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List getTeacherDepart_idAndName(String roleId,String departId,String name,int pageNum,int pageSize){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserInfo where roleId = ? and departId=? and name like  CONCAT('%',?,'%')");
            query.setParameter(0,roleId);
            query.setParameter(1,departId);
            query.setParameter(2,name);
            query.setFirstResult((pageNum - 1) * pageSize);//开始索引
            query.setMaxResults(pageSize);//取几条
            List menuList =query.list();
            return menuList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getAccountByDepart_idAndName(String roleId,String departId,String name){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserInfo where roleId = ? and departId=? and name like  CONCAT('%',?,'%')");
            query.setParameter(0,roleId);
            query.setParameter(1,departId);
            query.setParameter(2,name);
            List list =query.list();
            int total = list.size();
            return total;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List getTeacherByIdAndName(String roleId,String userId,String name,int pageNum,int pageSize){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserInfo where roleId = ? and name like  CONCAT('%',?,'%') and userId like  CONCAT('%',?,'%')");
            query.setParameter(0,roleId);
            query.setParameter(1,name);
            query.setParameter(2,userId);
            query.setFirstResult((pageNum - 1) * pageSize);//开始索引
            query.setMaxResults(pageSize);//取几条
            List menuList =query.list();
            return menuList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getAccountByIdAndName(String roleId,String userId,String name){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserInfo where roleId = ? and name like  CONCAT('%',?,'%') and userId like  CONCAT('%',?,'%')");
            query.setParameter(0,roleId);
            query.setParameter(1,name);
            query.setParameter(2,userId);
            List list =query.list();
            int total = list.size();
            return total;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List getTeacherByDepart_idAndIdAndName(String roleId,String userId,String name,String departId,int pageNum,int pageSize){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserInfo where roleId = ? and userId like  CONCAT('%',?,'%') and name like  CONCAT('%',?,'%') and departId=?");
            query.setParameter(0,roleId);
            query.setParameter(1,userId);
            query.setParameter(2,name);
            query.setParameter(3,departId);
            query.setFirstResult((pageNum - 1) * pageSize);//开始索引
            query.setMaxResults(pageSize);//取几条
            List menuList =query.list();
            return menuList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getAccountByDepart_idAndIdAndName(String roleId,String userId,String name,String departId){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from UserInfo where roleId = ? and userId like  CONCAT('%',?,'%') and name like  CONCAT('%',?,'%') and departId=?");
            query.setParameter(0,roleId);
            query.setParameter(1,userId);
            query.setParameter(2,name);
            query.setParameter(3,departId);
            List list =query.list();
            int total = list.size();
            return total;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public boolean editTeacher(String user_id, String name, String role_id, String depart_id, Date born, String phone, String sex, String id_card, String address, int state, String political, String profession_id, String description, String filePath,String qualification,String subject,int age,String province,String nation,String email) {
        try {
            Session session=sessionFactory.getCurrentSession();
            if(filePath==null||filePath.equals("")){
                Query query = session.createQuery(" update UserInfo set name=?,roleId=?,departId=?,born=?,phone=?,sex=?,idCard=?,address=?,state=?,political=?,professionId=?,description=?,qualification=?,subject=?,age=?,province=?,nation=?,email=? where userId =?");
                query.setParameter(0,name);
                query.setParameter(1,role_id);
                query.setParameter(2,depart_id);
                query.setParameter(3,born);
                query.setParameter(4,phone);
                query.setParameter(5,sex);
                query.setParameter(6,id_card);
                query.setParameter(7,address);
                query.setParameter(8,state);
                query.setParameter(9,political);
                query.setParameter(10,profession_id);
                query.setParameter(11,description);
                query.setParameter(12,qualification);
                query.setParameter(13,subject);
                query.setParameter(14,age);
                query.setParameter(15,province);
                query.setParameter(16,nation);
                query.setParameter(17,email);
                query.setParameter(18,user_id);
                query.executeUpdate();
                Query updateSalary = session.createQuery(" update TeacherSalary set name=?,departId=?,roleId=? where userId=?");
                updateSalary.setParameter(0,name);
                updateSalary.setParameter(1,depart_id);
                updateSalary.setParameter(2,role_id);
                updateSalary.setParameter(3,user_id);
                updateSalary.executeUpdate();
                return true;
            }
            else{
                Query query = session.createQuery(" update UserInfo set name=?,roleId=?,departId=?,born=?,phone=?,sex=?,idCard=?,address=?,state=?,political=?,professionId=?,description=?,headUrl=?,qualification=?,subject=?,age=?,province=? ,nation=?,email=? where userId =?");
                query.setParameter(0,name);
                query.setParameter(1,role_id);
                query.setParameter(2,depart_id);
                query.setParameter(3,born);
                query.setParameter(4,phone);
                query.setParameter(5,sex);
                query.setParameter(6,id_card);
                query.setParameter(7,address);
                query.setParameter(8,state);
                query.setParameter(9,political);
                query.setParameter(10,profession_id);
                query.setParameter(11,description);
                query.setParameter(12,filePath);
                query.setParameter(13,qualification);
                query.setParameter(14,subject);
                query.setParameter(15,age);
                query.setParameter(16,province);
                query.setParameter(17,nation);
                query.setParameter(18,email);
                query.setParameter(19,user_id);
                query.executeUpdate();
                Query updateSalary = session.createQuery(" update TeacherSalary set name=?,departId=?,roleId=? where userId=?");
                updateSalary.setParameter(0,name);
                updateSalary.setParameter(1,depart_id);
                updateSalary.setParameter(2,role_id);
                updateSalary.setParameter(3,user_id);
                updateSalary.executeUpdate();
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkUser_id(String user_id) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from  UserInfo where userId=?");
            query.setParameter(0,user_id);
            List list =query.list();
            if(list.size()==0){
                return true;
            }
            else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean saveTeacher(String userId, String name, String roleId, String departId, Date born, String phone,String  sex, String idCard, String address,int state, String political, String professionId, String description,String filePath,String qualification,String subject,int age,String province,String nation,String email)  {
        try {
            Session session=sessionFactory.getCurrentSession();
            String newPwd=userId.substring(userId.length()- 6,userId.length());//截取工号后六位为默认密码
            Md5 md5=new Md5();
            String md5Pwd=md5.EncoderByMd5(newPwd);
            UserInfo teacherInfo =new UserInfo();
            if(filePath==null||filePath.equals("")){
                if(sex.equals("男")){
                    teacherInfo.setHeadUrl("/upload/default_man.png");
                }else {
                    teacherInfo.setHeadUrl("/upload/default_woman.png");
                }
            }
            else{
                teacherInfo.setHeadUrl(filePath);
            }
            teacherInfo.setUserId(userId);
            teacherInfo.setName(name);
            teacherInfo.setPassword(md5Pwd);
            teacherInfo.setRoleId(roleId);
            teacherInfo.setDepartId(departId);
            teacherInfo.setBorn(born);
            teacherInfo.setPhone(phone);
            teacherInfo.setSex(sex);
            teacherInfo.setIdCard(idCard);
            teacherInfo.setAddress(address);
            teacherInfo.setState(state);
            teacherInfo.setPolitical(political);
            teacherInfo.setProfessionId(professionId);
            teacherInfo.setDescription(description);
            teacherInfo.setQualification(qualification);
            teacherInfo.setSubject(subject);
            teacherInfo.setAge(age);
            teacherInfo.setProvince(province);
            teacherInfo.setNation(nation);
            teacherInfo.setEmail(email);
            teacherInfo.setLastLoginTime("新增账号，还未有登录记录");
            teacherInfo.setNotice_state("NEW");
            session.save(teacherInfo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean deleteTeacherById(String userId){
        try {
            Session session=sessionFactory.getCurrentSession();
            String[] MenuId=userId.split(",");
            for (int i = 0; i < MenuId.length; i++){
                Query query=session.createQuery("delete from UserInfo where userId=?");
                query.setParameter(0,MenuId[i]);
                query.executeUpdate();
                Query querySalary=session.createQuery("delete from TeacherSalary where userId=?");
                querySalary.setParameter(0,MenuId[i]);
                querySalary.executeUpdate();
            }
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean resetPwd(String userId){
        try {
            Session session=sessionFactory.getCurrentSession();
            String newStr=userId.substring(userId.length()- 6,userId.length());//重置密码，截取工号后六位
            Md5 md5=new Md5();
            String newPwd=md5.EncoderByMd5(newStr);
            Query updateSalary = session.createQuery(" update UserInfo set password=? where userId=?");
            updateSalary.setParameter(0,newPwd);
            updateSalary.setParameter(1,userId);
            updateSalary.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}