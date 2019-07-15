package com.easy.live.streaming.servants.api.video.callback;

import com.easy.live.streaming.common.config.Constants;
import com.easy.live.streaming.servants.api.video.servant.VideoRoomServant;
import com.easy.live.streaming.servants.protocol.input.video.VideoRoomInput;
import com.easy.live.streaming.servants.protocol.output.BaseOutput;
import com.easy.live.streaming.servants.protocol.output.video.VideoRoomOutput;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Description:直播房间fallback
 * @Author: zhangliangfu
 * @Create on: 2019-06-24 19:40
 */

@Slf4j
public class VideoRoomServantFallback implements FallbackFactory<VideoRoomServant> {
    @Override
    public VideoRoomServant create(Throwable throwable) {
        return new VideoRoomServant() {
            /**
             * 新建直播房间
             *
             * @param input
             * @return
             */
            @Override
            public BaseOutput<VideoRoomOutput> addVideoRoom(VideoRoomInput input) {
                log.error("新建直播房间失败, input:{}", input);
                return new BaseOutput<>(Constants.RetMsg.FAIL.getCode(), "新建直播房间失败");
            }

            /**
             * 获取直播房间列表
             *
             * @param input
             * @return
             */
            @Override
            public BaseOutput<List<VideoRoomOutput>> videoRoomList(VideoRoomInput input) {
                log.error("获取直播房间列表失败,input:{}",input);
                return new BaseOutput<>(Constants.RetMsg.FAIL.getCode(), "获取直播房间列表失败");
            }
        };
    }
}
