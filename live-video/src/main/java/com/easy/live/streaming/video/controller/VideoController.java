package com.easy.live.streaming.video.controller;

import com.easy.live.streaming.data.entity.video.VideoLiveRoom;
import com.easy.live.streaming.data.service.video.VideoLiveRoomService;
import com.easy.live.streaming.servants.api.video.servant.VideoRoomServant;
import com.easy.live.streaming.servants.protocol.input.video.VideoRoomInput;
import com.easy.live.streaming.servants.protocol.output.BaseOutput;
import com.easy.live.streaming.servants.protocol.output.video.VideoRoomOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:视频直播Controller
 * @Author: zhangliangfu
 * @Create on: 2019-06-13 18:01
 */

@RestController
public class VideoController implements VideoRoomServant {

    @Autowired
    private VideoLiveRoomService videoLiveRoomService;
    /**
     * 新建直播房间
     *
     * @param input
     * @return
     */
    @Override
    public BaseOutput<VideoRoomOutput> addVideoRoom(VideoRoomInput input) {
        VideoLiveRoom videoLiveRoom = new VideoLiveRoom();
        videoLiveRoom.setTitle("title");
        videoLiveRoom.setCover("cover");
        videoLiveRoom.setRoomName("name");
        videoLiveRoom.setCateId(1);
        videoLiveRoom.setUserId(1);
        videoLiveRoom.setViewerCount(1);
        videoLiveRoomService.save(videoLiveRoom);
        return null;
    }

    /**
     * 获取直播房间列表
     *
     * @param input
     * @return
     */
    @Override
    public BaseOutput<List<VideoRoomOutput>> videoRoomList(VideoRoomInput input) {
        return null;
    }
}
