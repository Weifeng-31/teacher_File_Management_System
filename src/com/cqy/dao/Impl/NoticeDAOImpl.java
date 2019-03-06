package com.cqy.dao.Impl;
import com.cqy.dao.*;
import com.cqy.entity.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
@Scope("prototype")
public class NoticeDAOImpl implements NoticeDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List getNotice(String roleId,String departId){
        try {
            Session session=sessionFactory.getCurrentSession();
            if(roleId.equals("2")){
                Query query=session.createQuery("from Notice where roleId=?");
                query.setParameter(0,roleId);
                List menuList =query.list();
                return menuList;
            }
            else {
                Query query=session.createQuery("from Notice where roleId=? and departId=?");
                query.setParameter(0,roleId);
                query.setParameter(1,departId);
                List menuList =query.list();
                return menuList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
    @Override
    public boolean editNotice(Integer id,String roleId,String content,String departId){
        try {
            Session session=sessionFactory.getCurrentSession();
            if(roleId.equals("2")){
                Query query=session.createQuery("update  Notice set content =? where id=?");
                query.setParameter(0,content);
                query.setParameter(1,id);
                query.executeUpdate();
                Query query1=session.createQuery("update  UserInfo set notice_state = 'NEW'where roleId='2'");
                query1.executeUpdate();
                return true;
            }
            else {
                Query query=session.createQuery("update  Notice set content =? where id=?");
                query.setParameter(0,content);
                query.setParameter(1,id);
                query.executeUpdate();
                Query query1=session.createQuery("update  UserInfo set notice_state = 'NEW'where roleId=? and  departId=?");
                query1.setParameter(0,roleId);
                query1.setParameter(1,departId);
                query1.executeUpdate();
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean addNotice(String roleId,String content){
        try {
            Session session=sessionFactory.getCurrentSession();
            Notice notice =new Notice();
            notice.setRoleId(roleId);
            notice.setContent(content);
            session.save(notice);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean deleteNotice(Integer id){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("delete from Notice where id=?");
            query.setParameter(0,id);
            query.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateNotice(String userId){
        try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("update  UserInfo set notice_state = '已读'where userId=?");
            query.setParameter(0,userId);
            query.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
