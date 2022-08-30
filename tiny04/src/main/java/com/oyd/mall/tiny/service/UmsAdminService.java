package com.oyd.mall.tiny.service;

import com.oyd.mall.tiny.mbg.model.UmsAdmin;
import com.oyd.mall.tiny.mbg.model.UmsPermission;

import java.util.List;

public interface UmsAdminService {

    String login(String username,String password);

    UmsAdmin register(UmsAdmin umsAdminParam);

    List<UmsPermission> getPermissionList(Long adminId);

    UmsAdmin getUmdAdminByUsername(String username);
}
