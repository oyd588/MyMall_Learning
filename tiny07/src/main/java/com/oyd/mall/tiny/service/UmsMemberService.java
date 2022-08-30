package com.oyd.mall.tiny.service;

import com.oyd.mall.tiny.common.api.CommonResult;

public interface UmsMemberService {

    CommonResult generateAuthCode(String telephone);

    CommonResult verifyAuthCode(String telophone,String authCode);
}
