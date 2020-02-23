package com.july.test.controller;

import com.july.test.config.ApiResult;
import com.july.test.config.Constants;
import com.july.test.entity.ApiRequestParam;
import com.july.test.util.ApiTestUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自动化工具接口
 * @author zqk
 * @since 2020/2/3
 */
@RestController
@RequestMapping("/api")
public class ApiTestController {

    /**
     * 自动化工具方法
     * @param apiParam
     * @return com.july.test.config.ApiResult<java.lang.String>
     * @author zqk
     * @since 2020/2/7 8:23 下午
     */
    @PostMapping("/test")
    public ApiResult<String> apiTest(@RequestBody ApiRequestParam apiParam) {
        if(Constants.REQUEST_GET.equals(apiParam.getMethod())){
            return ApiResult.ok(ApiTestUtil.httpGet(apiParam));
        }else{
            return ApiResult.ok(ApiTestUtil.httpPost(apiParam));
        }

    }

}
