package com.easy.live.streaming.data.entity.user;

import com.easy.live.streaming.data.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description:直播用户
 * @Author: zhangliangfu
 * @Create on: 2019-07-12 14:19
 */
@Data
@Entity
@Table(name = "live_user")
public class LiveUser extends BaseEntity {
    private String password;
    private String title;
    private String name;
    private String email;
    private String phone;
    private String avatar;
    private boolean useFlag;
}
