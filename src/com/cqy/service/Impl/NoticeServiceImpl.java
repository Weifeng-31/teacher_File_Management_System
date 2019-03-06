package com.cqy.service.Impl;

import com.cqy.dao.LoginDAO;
import com.cqy.dao.NoticeDAO;
import com.cqy.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.List;
import com.cqy.entity.*;

@Service("Notice")
@Scope("prototype")
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private LoginDAO loginDAO;

    @Autowired
    private NoticeDAO noticeDAO;

    @Override
    public List getNotice(String roleId,String departId){
        return noticeDAO.getNotice(roleId,departId);
    }

    @Override
    public Integer getAccount(){
        Notice u = new Notice();
        return loginDAO.find(u).size();
    }

    @Override
    public boolean editNotice(Integer id,String roleId,String content,String departId){
        if(noticeDAO.editNotice(id,roleId,content,departId)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean addNotice(String roleId,String content){
        if(noticeDAO. addNotice(roleId,content)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean  deleteNotice(Integer id){
        if(noticeDAO.deleteNotice(id)){
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public boolean updateNotice(String userId){
        if(noticeDAO.updateNotice(userId)){
            return true;
        }
        else {
            return false;
        }
    }
}
