package com.easy.live.streaming.data.cache;

import lombok.Data;

/**
 * Description: 用户缓存
 * Author: zhangliangfu
 * Create on: 2019-08-08 13:49
 */

@Data
public class UserCache {
    private Integer userId;
    private String sessionId;
}
