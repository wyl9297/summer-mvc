package com.summer.service;

/**
 * Created by Administrator on 2018/7/26.
 *
 * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
 * @date :2018-07-27 10:29:07
 */
public interface DemoService {

    /**
     * Query string.
     *
     * @param name the name
     * @param age  the age
     * @return the string
     * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
     * @date :2018-07-27 10:29:07
     */
    String query(String name ,String age);

    /**
     * Delete string.
     *
     * @param name the name
     * @param age  the age
     * @return the string
     * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
     * @date :2018-07-27 10:29:07
     */
    String delete(String name ,String age);
}
