package com.oyd.mall.tiny.service.impl;

import com.oyd.mall.tiny.common.utils.JwtTokenUtils;
import com.oyd.mall.tiny.dto.AdminUserDetails;
import com.oyd.mall.tiny.mbg.mapper.UmsAdminMapper;
import com.oyd.mall.tiny.mbg.mapper.UmsAdminRoleRelationMapper;
import com.oyd.mall.tiny.mbg.model.UmsAdmin;
import com.oyd.mall.tiny.mbg.model.UmsAdminExample;
import com.oyd.mall.tiny.mbg.model.UmsPermission;
import com.oyd.mall.tiny.service.UmsAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Autowired
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authentication);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        //登录成功，根据
        AdminUserDetails userDetails = (AdminUserDetails) authenticate.getPrincipal();
        String token = jwtTokenUtils.generateToken(userDetails);

        UsernamePasswordAuthenticationToken successAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(successAuthentication);
        return token;
    }

    @Override
    public UmsAdmin register(UmsAdmin umsAdmin) {
        if (getUmdAdminByUsername(umsAdmin.getUsername()) != null) {
            return null;
        }
        UmsAdmin umsAdminToRegist = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminToRegist, umsAdminToRegist);
        umsAdminToRegist.setCreateTime(new Date());
        umsAdminToRegist.setStatus(1);
        umsAdminToRegist.setPassword(passwordEncoder.encode(umsAdmin.getPassword()));
        umsAdminMapper.insert(umsAdmin);
        return umsAdminToRegist;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return umsAdminRoleRelationMapper.getPermissionListByAdminId(adminId);
    }

    @Override
    public UmsAdmin getUmdAdminByUsername(String username) {
        UmsAdminExample umsAdminExample = new UmsAdminExample();
        UmsAdminExample.Criteria criteria = umsAdminExample.createCriteria().andUsernameEqualTo(username);

        //去数据库查询UmsAdmin
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectByExample(umsAdminExample);
        if (umsAdmins.isEmpty()) {
            throw new UsernameNotFoundException("该用户未注册");
        }
        UmsAdmin umsAdmin = umsAdmins.get(0);
        return umsAdmin;
    }
}
