package com.easy.live.streaming.video.service.impl;

import com.easy.live.streaming.data.entity.video.VideoLiveRoom;
import com.easy.live.streaming.data.service.video.VideoLiveRoomService;
import com.easy.live.streaming.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:直播Service实现
 * @Author: zhangliangfu
 * @Create on: 2019-06-13 17:54
 */

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoLiveRoomService videoLiveRoomService;

    @Override
    public VideoLiveRoom saveVideoRoom(VideoLiveRoom videoLiveRoom) {
        VideoLiveRoom result;
        if (videoLiveRoom.isNew()){
            result = videoLiveRoomService.save(videoLiveRoom);
        }else {
            VideoLiveRoom one = videoLiveRoomService.findOne(videoLiveRoom.getId());
            if (one!=null){
                one.setCateId(videoLiveRoom.getCateId());
                one.setRoomName(videoLiveRoom.getRoomName());
                one.setCover(videoLiveRoom.getCover());
                one.setTitle(videoLiveRoom.getTitle());
                videoLiveRoomService.save(one);
            }
            result = one;
        }
        return result;
    }

    @Override
    public VideoLiveRoom delVideoRoom(Integer roomId) {
        return null;
    }

    @Override
    public List<VideoLiveRoom> listVideoRooms() {
        return null;
    }
}
