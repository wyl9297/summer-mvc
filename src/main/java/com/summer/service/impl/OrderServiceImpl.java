package com.summer.service.impl;

import com.summer.annotation.SummerService;
import com.summer.service.OrderService;

/**
 * Created by Administrator on 2018/7/27.
 */
@SummerService("OrderServiceImpl")
public class OrderServiceImpl implements OrderService {
    @Override
    public String payOrder(String name, String orderPrice) {
        return "用户:" + name + ",订单价格:" + orderPrice;
    }
}