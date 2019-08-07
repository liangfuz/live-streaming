package com.easy.live.streaming.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 全局异常
 * Author: zhangliangfu
 * Create on: 2019-08-01 14:57
 */
@Getter
@Setter
public class GlobalException extends Exception {
    private Integer code;
    private String message;

    public GlobalException(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }

}
