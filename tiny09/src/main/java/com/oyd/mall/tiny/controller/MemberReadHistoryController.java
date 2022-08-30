package com.oyd.mall.tiny.controller;


import com.oyd.mall.tiny.common.api.CommonResult;
import com.oyd.mall.tiny.nosql.mongodb.document.MemberReadHistory;
import com.oyd.mall.tiny.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "MemberReadHistoryController", description = "会员商品浏览记录管理")
@RequestMapping("/member/readHistory")
@RestController
public class MemberReadHistoryController {

    @Autowired
    private MemberReadHistoryService memberReadHistoryService;

    @ApiOperation("创建浏览记录")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody MemberReadHistory memberReadHistory) {
        int count = memberReadHistoryService.create(memberReadHistory);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("删除浏览记录")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam("ids") List<String> ids) {
        int count = memberReadHistoryService.delete(ids);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("展示浏览记录")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonResult<List<MemberReadHistory>> list(Long memberId){
        List<MemberReadHistory> list = memberReadHistoryService.list(memberId);
        return CommonResult.success(list);
    }
}
