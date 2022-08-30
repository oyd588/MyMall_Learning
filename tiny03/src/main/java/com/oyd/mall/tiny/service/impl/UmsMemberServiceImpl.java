package com.oyd.mall.tiny.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.oyd.mall.tiny.common.api.CommonResult;
import com.oyd.mall.tiny.service.RedisService;
import com.oyd.mall.tiny.service.UmsMemberService;
import org.mybatis.generator.internal.util.StringUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE ;

    @Value("${redis.key.expire.authCode}")
    private long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public CommonResult generateAuthCode(String telephone) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        //生成6位数字验证码
        String authCode = stringBuilder.toString();
        //验证码绑定手机号，存储到redis中
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE+ telephone, authCode);
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE+ telephone,AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(authCode, "获取验证码成功");
    }

    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        if(StringUtils.isEmpty(authCode)){
            return CommonResult.failed("请输入验证码");
        }
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        boolean result = authCode.equals(realAuthCode);
        if(result){
            return CommonResult.success(null, "验证码校验成功");
        }else {
            return CommonResult.failed("验证码不正确");
        }

    }
}
