package com.summer.service.impl;

import com.summer.annotation.SummerService;
import com.summer.service.SecondService;

/**
 * Created by Administrator on 2018/12/17.
 */
@SummerService("SeondServiceImpl")
public class SeondServiceImpl implements SecondService {
    @Override
    public String laugh(String who, String where) {

        return  who + " is laugh in " + where;
    }
}
