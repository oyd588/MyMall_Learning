package com.oyd.mall.tiny.service.impl;

import com.oyd.mall.tiny.dto.AdminUserDetails;
import com.oyd.mall.tiny.mbg.mapper.UmsAdminMapper;
import com.oyd.mall.tiny.mbg.mapper.UmsAdminRoleRelationMapper;
import com.oyd.mall.tiny.mbg.model.*;
import com.oyd.mall.tiny.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Permission;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UmsAdminService umsAdminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UmsAdmin umsAdmin = umsAdminService.getUmdAdminByUsername(username);
        if (umsAdmin == null) {
            throw new UsernameNotFoundException("该用户未注册");
        }
        List<UmsPermission> permissionList = umsAdminService.getPermissionList(umsAdmin.getId());

        return new AdminUserDetails(umsAdmin, permissionList);
    }
}
