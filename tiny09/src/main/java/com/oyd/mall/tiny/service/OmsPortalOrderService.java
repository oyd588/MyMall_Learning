package com.oyd.mall.tiny.service;

import com.oyd.mall.tiny.common.api.CommonResult;
import com.oyd.mall.tiny.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

public interface OmsPortalOrderService {
    /**
     * 根据提交信息生成订单
     * @param
     */
    @Transactional
    CommonResult generateOrder(OrderParam orderParam);

    /**
     * 取消单个超时订单
     * @param orderId
     */
    void cancelOrder(Long orderId);

}
