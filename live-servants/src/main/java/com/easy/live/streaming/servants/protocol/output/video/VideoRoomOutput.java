package com.easy.live.streaming.servants.protocol.output.video;

import lombok.Data;

/**
 * @Description:直播房间出参
 * @Author: zhangliangfu
 * @Create on: 2019-06-24 19:45
 */

@Data
public class VideoRoomOutput {

    //房间名称
    private String roomName;

    //标题
    private String title;

    //封面
    private String cover;

    //类型
    private Integer cateId;

    //直播URL
    private String liveUrl;
}
