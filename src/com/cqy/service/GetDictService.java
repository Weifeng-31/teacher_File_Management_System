package com.cqy.service;

import java.util.List;

public interface GetDictService {
    List getMenu(String roleId);
    List getRoleDict();
    List getDepartDict();
    List getProfessionDict();
}
