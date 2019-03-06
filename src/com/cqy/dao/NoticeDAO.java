package com.cqy.dao;

import java.util.List;

public interface NoticeDAO {
    List getNotice(String roleId,String departId);
    boolean editNotice(Integer id,String roleId,String content,String departId);
    boolean addNotice(String roleId,String content);
    boolean  deleteNotice(Integer id);
    boolean updateNotice(String userId);
}
