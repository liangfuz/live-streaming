package com.easy.live.streaming.servants.api.video.servant;

import com.easy.live.streaming.data.bean.BaseOutput;
import com.easy.live.streaming.servants.api.video.fallback.VideoRoomServantFallback;
import com.easy.live.streaming.servants.protocol.output.video.VideoRoomOutput;
import com.easy.live.streaming.servants.protocol.input.video.VideoRoomInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description:直播房间Servant
 * @Author: zhangliangfu
 * @Create on: 2019-06-20 17:21
 */

@FeignClient(name = "live-video-servant", fallbackFactory = VideoRoomServantFallback.class)
@Api(value = "VideoRoomServant", description = "直播间接口")
public interface VideoRoomServant {
    /**
     * 新建直播房间
     * @param input
     * @return
     */
    @ApiOperation(value="创建房间接口", notes="创建房间接口")
    @RequestMapping("/live/video/room/addVideoRoom")
    BaseOutput<VideoRoomOutput> addVideoRoom(VideoRoomInput input);
    /**
     * 获取直播房间列表
     * @param input
     * @return
     */
    @ApiOperation(value="房间列表接口", notes="房间列表接口")
    @RequestMapping("/open/live/video/room/videoRoomList")
    BaseOutput<List<VideoRoomOutput>> videoRoomList(VideoRoomInput input);
}
