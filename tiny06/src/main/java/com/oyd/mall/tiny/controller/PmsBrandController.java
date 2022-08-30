package com.oyd.mall.tiny.controller;

import com.oyd.mall.tiny.common.api.CommonPage;
import com.oyd.mall.tiny.common.api.CommonResult;
import com.oyd.mall.tiny.mbg.model.PmsBrand;
import com.oyd.mall.tiny.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌管理Controller
 */
@Api(tags = "PmsBrandController",description = "商品品牌管理")
@Controller
@Slf4j
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService pmsBrandService;


    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ApiOperation(value = "获取所有品牌")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> getBrandList() {
        return CommonResult.success(pmsBrandService.listAllBrand());
    }

    @PreAuthorize("hasAuthority('pms:brand:create')")
    @ApiOperation(value = "添加品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBrand(@ApiParam(name = "新建品牌详情",value = "新建品牌详情对象") @RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int count = pmsBrandService.createBrand(pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
            log.info("createBrand success:{}", pmsBrand);
        } else {
            commonResult = CommonResult.failed("操作失败");
            log.info("createBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }

    @PreAuthorize("hasAuthority('pms:brand:update')")
    @ApiOperation(value = "更新指定id品牌信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrand, BindingResult result) {
        CommonResult commonResult;
        int count = pmsBrandService.updateBrand(id, pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
            log.info("updateBrand success:{}", pmsBrand);
        } else {
            commonResult = CommonResult.failed("操作失败");
            log.info("updateBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }

    @PreAuthorize("hasAuthority('pms:brand:delete')")
    @ApiOperation(value = "删除指定id品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        CommonResult commonResult;
        int count = pmsBrandService.deleteBrand(id);
        if (count == 1) {
            commonResult = CommonResult.success(null);
            log.info("deleteBrand success id =:{}", id);
        } else {
            commonResult = CommonResult.failed("操作失败");
            log.info("deleteBrand failed id = :{}", id);
        }
        return commonResult;
    }

    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ApiOperation(value = "分页查询品牌")
    @RequestMapping(value = "/list" ,method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum",defaultValue = "1" )Integer pageNum,
                                                  @RequestParam(value = "pageSize",defaultValue = "3") Integer pageSize){
        List<PmsBrand> pmsBrands = pmsBrandService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(pmsBrands));
    }

    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ApiOperation(value = "获取指定id的品牌详情")
    @RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> brand(@PathVariable("id")Long id){
        PmsBrand brand = pmsBrandService.getBrand(id);
        return CommonResult.success(brand);
    }
}
