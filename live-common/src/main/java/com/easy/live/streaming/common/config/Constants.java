package com.easy.live.streaming.common.config;

/**
 * Created by ZLF on 2017/6/21.
 */
public class Constants {

    public static final String LIVE_URL_PREFIX = "rtmp://192.168.10.110:1935/live/room";

    public static final String LOGIN_CACHE = "LOGIN_CACHE_";
    public static final int LOGIN_CACHE_TIME = 600; //十分钟

    public enum RetMsg{
        SUCCESS(200, "操作成功"),

        FAIL(500, "操作失败"),
        LOGIN_YES(401, "已登录"),
        LOGIN_NO(402, "未登录"),

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
