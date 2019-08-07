package com.easy.live.streaming.common.config;

/**
 * Created by ZLF on 2017/6/21.
 */
public class Constants {

    public enum RetMsg{
        SUCCESS(200, "操作成功"),

        FAIL(500, "操作失败"),

        EXCEPTION_LOGIN(301, "未登录异常");

        public Integer code;

        public String msg;

        RetMsg(Integer retCode, String retMsg) {
            this.code = retCode;
            this.msg = retMsg;
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
