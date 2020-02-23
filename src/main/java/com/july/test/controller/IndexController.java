package com.july.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 自动化测试工具入口
 * @author zqk
 * @since 2020/2/3
 */
@Controller
public class IndexController {

    /**
     * 首页
     * @return java.lang.String
     * @author zqk
     * @since 2020/2/7 8:22 下午
     */
    @RequestMapping("/index1")
    public String index(){
        return "/api";
    }

    /**
     * 首页
     * @return java.lang.String
     * @author zqk
     * @since 2020/2/7 8:22 下午
     */
    @RequestMapping("/index")
    public String test(){
        return "/index";
    }

}
