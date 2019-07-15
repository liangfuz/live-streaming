package com.easy.live.streaming.video.service;

import com.easy.live.streaming.data.entity.video.VideoLiveRoom;

import java.util.List;

/**
 * @Description:直播Service
 * @Author: zhangliangfu
 * @Create on: 2019-06-13 17:53
 */
public interface VideoService {
    VideoLiveRoom saveVideoRoom(VideoLiveRoom videoLiveRoom);
    VideoLiveRoom delVideoRoom(Integer roomId);
    List<VideoLiveRoom> listVideoRooms();
}
