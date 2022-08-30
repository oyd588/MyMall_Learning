package com.oyd.mall.tiny.controller;

import com.oyd.mall.tiny.common.api.CommonPage;
import com.oyd.mall.tiny.common.api.CommonResult;
import com.oyd.mall.tiny.nosql.elasticsearch.document.EsProduct;
import com.oyd.mall.tiny.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "EsProductContoller", description = "搜索商品管理")
@RestController
@RequestMapping("/esProduct")
public class EsProductController {
    @Autowired
    private EsProductService esProductService;

    @ApiOperation("导入所有数据库中商品到ES")
    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
    public CommonResult<Integer> importAllList() {
        int count = esProductService.importAll();
        return CommonResult.success(count);
    }

    @ApiOperation("根据id删除商品")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable Long id) {
        esProductService.delete(id);
        return CommonResult.success(null);
    }

    @ApiOperation("根据id批量删除商品")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        esProductService.delete(ids);
        return CommonResult.success(null);
    }

    @ApiOperation("根据id创建商品")
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    public CommonResult<EsProduct> create(@PathVariable Long id) {
        EsProduct esProduct = esProductService.create(id);
        if(esProduct==null){
            return CommonResult.failed();
        }else {
            return CommonResult.success(esProduct);
        }
    }
    @ApiOperation(value = "简单搜索")
    @RequestMapping(value = "/search/simple", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsProduct> esProductPage = esProductService.search(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(esProductPage));
    }
}
