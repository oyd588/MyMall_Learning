package com.oyd.mall.tiny.controller;

import com.oyd.mall.tiny.common.api.CommonPage;
import com.oyd.mall.tiny.common.api.CommonResult;
import com.oyd.mall.tiny.mbg.model.PmsBrand;
import com.oyd.mall.tiny.service.PmsBrandService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌管理Controller
 */
@Controller
@Slf4j
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService pmsBrandService;

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> getBrandList() {
        return CommonResult.success(pmsBrandService.listAllBrand());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
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

    @RequestMapping(value = "/list" ,method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum",defaultValue = "1" )Integer pageNum,
                                                  @RequestParam(value = "pageSize",defaultValue = "3") Integer pageSize){
        List<PmsBrand> pmsBrands = pmsBrandService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(pmsBrands));
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> brand(@PathVariable("id")Long id){
        PmsBrand brand = pmsBrandService.getBrand(id);
        return CommonResult.success(brand);
    }
}
