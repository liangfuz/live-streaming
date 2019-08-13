package com.easy.live.streaming.video.controller;

import com.easy.live.streaming.common.config.Constants;
import com.easy.live.streaming.data.cache.ThreadLocalUserCache;
import com.easy.live.streaming.data.cache.UserCache;
import com.easy.live.streaming.data.entity.video.VideoLiveRoom;
import com.easy.live.streaming.data.service.video.VideoLiveRoomService;
import com.easy.live.streaming.servants.api.video.servant.VideoRoomServant;
import com.easy.live.streaming.servants.protocol.input.video.VideoRoomInput;
import com.easy.live.streaming.data.bean.BaseOutput;
import com.easy.live.streaming.servants.protocol.output.video.VideoRoomOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        UserCache userCache = ThreadLocalUserCache.getUserCache();
        VideoLiveRoom videoLiveRoom = new VideoLiveRoom();
        videoLiveRoom.setTitle(input.getTitle());
        videoLiveRoom.setCover(input.getCover());
        videoLiveRoom.setRoomName(input.getRoomName());
        videoLiveRoom.setCateId(input.getCateId());
        videoLiveRoom.setUserId(userCache.getUserId());
        videoLiveRoomService.save(videoLiveRoom);
        return new BaseOutput<>(Constants.RetMsg.SUCCESS.code, Constants.RetMsg.SUCCESS.msg);
    }

    /**
     * 获取直播房间列表
     *
     * @param input
     * @return
     */
    @Override
    public BaseOutput<List<VideoRoomOutput>> videoRoomList(VideoRoomInput input) {
        List<VideoRoomOutput> list = new ArrayList<>();
        List<VideoLiveRoom> all = videoLiveRoomService.findAll();
        all.forEach(videoLiveRoom -> {
            VideoRoomOutput videoRoomOutput = new VideoRoomOutput();
            videoRoomOutput.setTitle(videoLiveRoom.getTitle());
            videoRoomOutput.setCateId(videoLiveRoom.getCateId());
            videoRoomOutput.setCover(videoLiveRoom.getCover());
            videoRoomOutput.setRoomName(videoLiveRoom.getRoomName());
            videoRoomOutput.setLiveUrl(Constants.LIVE_URL_PREFIX+videoLiveRoom.getId());
            list.add(videoRoomOutput);
        });
        return new BaseOutput<>(Constants.RetMsg.SUCCESS.code, Constants.RetMsg.SUCCESS.msg, list);
    }
}
