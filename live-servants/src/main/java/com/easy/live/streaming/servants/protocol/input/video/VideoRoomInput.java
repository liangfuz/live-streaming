package com.easy.live.streaming.servants.protocol.input.video;

import lombok.Data;

/**
 * @Description:直播房间入参
 * @Author: zhangliangfu
 * @Create on: 2019-06-24 19:45
 */

@Data
public class VideoRoomInput {

    //房间名称
    private String roomName;

    //标题
    private String title;

    //封面
    private String cover;

    //类型
    private Integer cateId;
}
