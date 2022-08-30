package com.oyd.mall.tiny.controller;

import com.oyd.mall.tiny.common.api.CommonResult;
import com.oyd.mall.tiny.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "UmsMemberController",description = "会员注册管理")
@RestController
@RequestMapping("/sso")
public class UmsMemberController {

    @Autowired
    private UmsMemberService umsMemberService;

    @ApiOperation(value = "获取验证码")
    @GetMapping("/getAuthCode")
    public CommonResult getAuthCode(@RequestParam String telephone){
        return umsMemberService.generateAuthCode(telephone);
    }


    @ApiOperation(value = "判断验证码是否正确")
    @PostMapping("/verifyAuthcode")
    public CommonResult verifyAuthCode(@RequestParam String telephone,@RequestParam String authCode){
        return umsMemberService.verifyAuthCode(telephone, authCode);
    }

}
