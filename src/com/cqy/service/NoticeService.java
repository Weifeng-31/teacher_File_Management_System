package com.cqy.service;

import java.util.List;

public interface NoticeService {
    List getNotice(String roleId,String departId);
    Integer getAccount();
    boolean editNotice(Integer id,String roleId,String content,String departId);
    boolean addNotice(String roleId,String content);
    boolean  deleteNotice(Integer id);
    boolean updateNotice(String userId);
}
