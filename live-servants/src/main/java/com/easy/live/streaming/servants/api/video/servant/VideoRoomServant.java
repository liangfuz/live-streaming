package com.easy.live.streaming.servants.api.video.servant;

import com.easy.live.streaming.data.bean.BaseOutput;
import com.easy.live.streaming.servants.api.video.fallback.VideoRoomServantFallback;
import com.easy.live.streaming.servants.protocol.output.video.VideoRoomOutput;
import com.easy.live.streaming.servants.protocol.input.video.VideoRoomInput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description:直播房间Servant
 * @Author: zhangliangfu
 * @Create on: 2019-06-20 17:21
 */

@FeignClient(name = "live-video-servant", fallbackFactory = VideoRoomServantFallback.class)
public interface VideoRoomServant {
    /**
     * 新建直播房间
     * @param input
     * @return
     */
    @RequestMapping("/live/video/room/addVideoRoom")
    BaseOutput<VideoRoomOutput> addVideoRoom(VideoRoomInput input);
    /**
     * 获取直播房间列表
     * @param input
     * @return
     */
    @RequestMapping("/open/live/video/room/videoRoomList")
    BaseOutput<List<VideoRoomOutput>> videoRoomList(VideoRoomInput input);
}
