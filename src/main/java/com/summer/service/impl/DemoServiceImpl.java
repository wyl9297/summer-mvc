package com.summer.service.impl;

import com.summer.annotation.SummerService;
import com.summer.service.DemoService;

/**
 * Created by Administrator on 2018/7/26.
 *
 * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
 * @date :2018-07-27 10:28:55
 */
@SummerService("DemoServiceImpl")
public class DemoServiceImpl implements DemoService {

    @Override
    public String query(String name, String age) {
        return "我叫" + name + ",年方"+ age;
    }

    @Override
    public String delete(String name, String age) {
        return "用户:" + name + "====年龄" + age + "已删除。。。。" ;
    }
}
