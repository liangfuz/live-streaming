package com.easy.live.streaming.servants.protocol.output.user;

import lombok.Data;

/**
 * @Description:用户出参
 * @Author: zhangliangfu
 * @Create on: 2019-07-10 19:05
 */

@Data
public class UserOutput {
    private String password;
    private String title;
    private String avatar;
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private boolean useFlag;
}
