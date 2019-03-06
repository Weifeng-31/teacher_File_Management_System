package com.cqy.dao.Impl;

import com.cqy.dao.*;
import com.cqy.entity.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.util.List;

@Repository
@Transactional
@Scope("prototype")
public class SalaryDAOImpl implements SalaryDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List getSalary(String depart_id,int pageNum, int pageSize) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query = session.createQuery(" from TeacherSalary  where (departId = ?) and roleId='3'");
            query.setParameter(0, depart_id);
            query.setFirstResult((pageNum - 1) * pageSize);//开始索引
            query.setMaxResults(pageSize);//取几条
            List menuList = query.list();
            for(int i=0; i<query.list().size();i++) {
                TeacherSalary teacherSalary= (TeacherSalary)menuList.get(i);
                Integer baseSalary=teacherSalary.getBaseSalary();
                Integer bonus=teacherSalary.getBonus();
                int total= baseSalary+bonus;
                teacherSalary.setTotal(total);
            }
            return menuList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getAccount(String depart_id) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query = session.createQuery("from TeacherSalary  where (departId = ?)");
            query.setParameter(0, depart_id);
            List list = query.list();
            int total = list.size();
            return total;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List getSalaryByName(String name,String depart_id,int pageNum,int pageSize) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery(" from TeacherSalary  where name like CONCAT('%',?,'%') and (departId =? and (roleId = '3'))");
            query.setParameter(0,name);
            query.setParameter(1,depart_id);
            query.setFirstResult((pageNum - 1) * pageSize);//开始索引
            query.setMaxResults(pageSize);//取几条
            List menuList =query.list();
            for(int i=0; i<query.list().size();i++) {
                TeacherSalary teacherSalary= (TeacherSalary)menuList.get(i);
                Integer baseSalary=teacherSalary.getBaseSalary();
                Integer bonus=teacherSalary.getBonus();
                int total= baseSalary+bonus;
                teacherSalary.setTotal(total);
            }
            return menuList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getSalaryAccountByName(String name,String depart_id) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from TeacherSalary  where name like CONCAT('%',?,'%') and (departId =? and (roleId = '3'))");
            query.setParameter(0,name);
            query.setParameter(1,depart_id);
            List list =query.list();
            int total = list.size();
            return total;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List getSalaryById(String user_id,String depart_id,int pageNum,int pageSize) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery(" from TeacherSalary  where userId like CONCAT('%',?,'%') and (departId =? and (roleId = '3'))");
            query.setParameter(0,user_id);
            query.setParameter(1,depart_id);
            query.setFirstResult((pageNum - 1) * pageSize);//开始索引
            query.setMaxResults(pageSize);//取几条
            List menuList =query.list();
            for(int i=0; i<query.list().size();i++) {
                TeacherSalary teacherSalary= (TeacherSalary)menuList.get(i);
                Integer baseSalary=teacherSalary.getBaseSalary();
                Integer bonus=teacherSalary.getBonus();
                int total= baseSalary+bonus;
                teacherSalary.setTotal(total);
            }
            return menuList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getSalaryAccountById(String user_id,String depart_id) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from TeacherSalary  where userId like CONCAT('%',?,'%') and (departId =? and (roleId = '3'))");
            query.setParameter(0,user_id);
            query.setParameter(1,depart_id);
            List list =query.list();
            int total = list.size();
            return total;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List getSalaryByIdAndName(String user_id,String name,String depart_id,int pageNum,int pageSize) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from TeacherSalary  where name like CONCAT('%',?,'%')and userId like CONCAT('%',?,'%') and (departId =? and (roleId = '3'))");
            query.setParameter(0,name);
            query.setParameter(1,user_id);
            query.setParameter(2,depart_id);
            query.setFirstResult((pageNum - 1) * pageSize);//开始索引
            query.setMaxResults(pageSize);//取几条
            List menuList =query.list();
            for(int i=0; i<query.list().size();i++) {
                TeacherSalary teacherSalary= (TeacherSalary)menuList.get(i);
                Integer baseSalary=teacherSalary.getBaseSalary();
                Integer bonus=teacherSalary.getBonus();
                int total= baseSalary+bonus;
                teacherSalary.setTotal(total);
            }
            return menuList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getSalaryAccountByIdAndName(String user_id,String name,String depart_id) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from TeacherSalary  where name like CONCAT('%',?,'%')and userId like CONCAT('%',?,'%') and (departId =? and (roleId = '3'))");
            query.setParameter(0,name);
            query.setParameter(1,user_id);
            query.setParameter(2,depart_id);
            List list =query.list();
            int total = list.size();
            return total;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean editSalary(Integer id, java.sql.Date month, Integer baseSalary, Integer bonus) {
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query = session.createQuery(" update TeacherSalary set month=?,baseSalary=?,bonus=? where id =?");
            query.setParameter(0, month);
            query.setParameter(1, baseSalary);
            query.setParameter(2, bonus);
            query.setParameter(3, id);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveSalary(String user_id, String name, String depart_id, java.sql.Date month, Integer baseSalary, Integer bonus) {
        try {
            Session session=sessionFactory.getCurrentSession();
            TeacherSalary teacherSalary =new TeacherSalary();
            teacherSalary.setUserId(user_id);
            teacherSalary.setName(name);
            teacherSalary.setRoleId("3");
            teacherSalary.setDepartId(depart_id);
            teacherSalary.setMonth(month);
            teacherSalary.setBaseSalary(baseSalary);
            teacherSalary.setBonus(bonus);
            session.save(teacherSalary);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkDate(String userId, java.sql.Date month){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query = session.createQuery(" from TeacherSalary  where userId=? and month=?");
            query.setParameter(0,userId);
            query.setParameter(1,month);
            List list =query.list();
            if(list.size()==0){
                return true;
            }
            else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteSalary(String id){
        try {
            Session session=sessionFactory.getCurrentSession();
            String[] salaryId=id.split(",");
            for (int i = 0; i < salaryId.length; i++){
                Query query=session.createQuery("delete from TeacherSalary where id=?");
                query.setParameter(0,Integer.valueOf(salaryId[i]));
                query.executeUpdate();
            }
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public   List searchByLike(String userId,String departId,String roleId){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query = session.createQuery(" select userId,name from UserInfo  where userId like  CONCAT('%',?,'%') and departId=? and roleId=?");
            query.setParameter(0,userId);
            query.setParameter(1,departId);
            query.setParameter(2,roleId);
            List list =query.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List checkSalary(String departId,int pageNum, int pageSize){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query = session.createQuery("select a from UserInfo a left join TeacherSalary b on a.userId = b.userId where b.userId is null and a.roleId='3' and a.departId=?");
            query.setParameter(0,departId);
            query.setFirstResult((pageNum - 1) * pageSize);//开始索引
            query.setMaxResults(pageSize);//取几条
            List list =query.list();
            return  list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public Integer checkSalaryAccount(String depart_id){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query = session.createQuery("select a from UserInfo a left join TeacherSalary b on a.userId = b.userId where b.userId is null and a.roleId='3' and a.departId=?");
            query.setParameter(0, depart_id);
            List list = query.list();
            int total = list.size();
            return total;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public  List queryOwnSalary(String userId, int pageNum, int pageSize){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query = session.createQuery("from TeacherSalary where userId=?");
            query.setParameter(0,userId);
            query.setFirstResult((pageNum - 1) * pageSize);//开始索引
            query.setMaxResults(pageSize);//取几条
            List list =query.list();
            for(int i=0; i<query.list().size();i++) {
                TeacherSalary teacherSalary= (TeacherSalary)list.get(i);
                Integer baseSalary=teacherSalary.getBaseSalary();
                Integer bonus=teacherSalary.getBonus();
                int total= baseSalary+bonus;
                teacherSalary.setTotal(total);
            }
            return  list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public Integer queryOwnSalaryAccount(String userId){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query = session.createQuery("from TeacherSalary where userId=?");
            query.setParameter(0, userId);
            List list = query.list();
            int total = list.size();
            return total;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public  List queryOwnSalaryByDate(String userId, int pageNum, int pageSize, java.sql.Date month){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query = session.createQuery("from TeacherSalary where userId=? and month=?");
            query.setParameter(0,userId);
            query.setParameter(1,month);
            query.setFirstResult((pageNum - 1) * pageSize);//开始索引
            query.setMaxResults(pageSize);//取几条
            List list =query.list();
            for(int i=0; i<query.list().size();i++) {
                TeacherSalary teacherSalary= (TeacherSalary)list.get(i);
                Integer baseSalary=teacherSalary.getBaseSalary();
                Integer bonus=teacherSalary.getBonus();
                int total= baseSalary+bonus;
                teacherSalary.setTotal(total);
            }
            return  list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public Integer queryOwnSalaryAccountByDate(String userId, java.sql.Date month){

        try {
            Session session=sessionFactory.getCurrentSession();
            Query query = session.createQuery("from TeacherSalary where userId=? and month=?");
            query.setParameter(0,userId);
            query.setParameter(1,month);
            List list = query.list();
            int total = list.size();
            return total;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public  List getMonth(String departId){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query = session.createQuery("select distinct month from TeacherSalary where departId =? order by month asc ");
            query.setParameter(0,departId);
            List list =query.list();
            return  list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public  List getMonthList(Date month, String departId){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query = session.createQuery("from TeacherSalary where departId =? and  month=? ");
            query.setParameter(0,departId);
            query.setParameter(1,month);
            List list =query.list();
            for(int i=0; i<query.list().size();i++) {
                TeacherSalary teacherSalary= (TeacherSalary)list.get(i);
                Integer baseSalary=teacherSalary.getBaseSalary();
                Integer bonus=teacherSalary.getBonus();
                int total= baseSalary+bonus;
                teacherSalary.setTotal(total);
            }
            return  list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public List getTeacherSalary(String userId){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery(" from TeacherSalary  where userId =? order by month desc");
            query.setParameter(0,userId);
            List menuList =query.list();
            for(int i=0; i<query.list().size();i++) {
                TeacherSalary teacherSalary= (TeacherSalary)menuList.get(i);
                Integer baseSalary=teacherSalary.getBaseSalary();
                Integer bonus=teacherSalary.getBonus();
                int total= baseSalary+bonus;
                teacherSalary.setTotal(total);
            }
            return menuList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
