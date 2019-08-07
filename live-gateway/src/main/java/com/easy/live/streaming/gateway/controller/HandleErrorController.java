package com.easy.live.streaming.gateway.controller;

import com.alibaba.fastjson.JSON;
import com.easy.live.streaming.data.bean.BaseOutput;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 错误处理controller
 * Author: zhangliangfu
 * Create on: 2019-08-07 16:03
 */
@Controller
public class HandleErrorController implements ErrorController {

    @Value("${error.path:/error}")
    private String errorPath;

    @Override
    public String getErrorPath() {
        return errorPath;
    }

    @RequestMapping(value = "${error.path:/error}")
    public @ResponseBody
    ResponseEntity error(HttpServletRequest request) {

        int status = getErrorStatus(request);
        String errorMessage = getErrorMessage(request);
        BaseOutput baseOutput = new BaseOutput(status, errorMessage);
        return ResponseEntity.status(status).body(JSON.toJSONString(baseOutput));
    }

    private int getErrorStatus(HttpServletRequest request) {
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        return statusCode != null ? statusCode : HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    private String getErrorMessage(HttpServletRequest request) {
        final Throwable exc = (Throwable) request.getAttribute("javax.servlet.error.exception");
        return exc != null ? exc.getMessage() : "Unexpected error occurred";
    }
}
