package com.easy.live.streaming.servants.protocol.output;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: base input
 * @Author: zhangliangfu
 * @Create on: 2019-06-25 10:59
 */

@Data
public class BaseOutput<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public BaseOutput(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public BaseOutput(Integer code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
