package com.easy.live.streaming.servants.protocol.input.user;

import lombok.Data;

/**
 * @Description:用户入参
 * @Author: zhangliangfu
 * @Create on: 2019-07-10 19:07
 */

@Data
public class UserInput {
    private String password;
    private String title;
    private String avatar;
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private boolean useFlag;

}
