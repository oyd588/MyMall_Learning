package com.oyd.mall.tiny.controller;

import cn.hutool.core.collection.CollUtil;
import com.oyd.mall.tiny.common.api.CommonResult;
import com.oyd.mall.tiny.dto.UmsAdminLoginParam;
import com.oyd.mall.tiny.mbg.model.UmsAdmin;
import com.oyd.mall.tiny.mbg.model.UmsPermission;
import com.oyd.mall.tiny.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 实现了后台用户登录、注册及获取权限的接口
 */
@RestController
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService adminService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdminParam) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null) {
            return CommonResult.failed("注册失败");
        }
        return CommonResult.success(umsAdmin, "注册成功");
    }

    @ApiOperation("用户登录,并返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdmin) {
        String token = adminService.login(umsAdmin.getUsername(), umsAdmin.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }

        HashMap<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap, "登录成功");

    }


    @ApiOperation("获取用户所有权限（包括+-权限)")
    @RequestMapping(value = "/permission/{adminId}", method = RequestMethod.GET)
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId) {
        List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
        return CommonResult.success(permissionList, "获取权限成功");
    }


}
