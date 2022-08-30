package com.oyd.mall.tiny.service;

import com.oyd.mall.tiny.nosql.elasticsearch.document.EsProduct;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EsProductService {

    /**
     * 从数据库导入所有商品到ES
     */
    int importAll();

    /**
     * 根据id删除商品
     */
    void delete(Long id);

    /**
     * 根据id创建商品
     */
    EsProduct create (Long id);

    /**
     * 批量删除商品
     */
    void delete(List<Long> ids);

    /**
     * 根据关键字、副标题或名称检索
     */
    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);
}
