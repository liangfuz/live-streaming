package com.easy.live.streaming.servants.protocol.input.video;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:直播房间入参
 * @Author: zhangliangfu
 * @Create on: 2019-06-24 19:45
 */

@Data
@ApiModel
public class VideoRoomInput {

    //房间名称
    @ApiModelProperty(name = "房间名称", value = "Coder的直播间", dataType = "string", example = "Coder的直播间")
    private String roomName;

    //标题
    @ApiModelProperty(name = "标题", value = "今天直播撸代码1000行", dataType = "string", example = "今天直播撸代码1000行")
    private String title;

    //封面
    @ApiModelProperty(name = "直播间封面", value = "http://xxxx", dataType = "string", example = "http://xxxx")
    private String cover;

    //类型
    @ApiModelProperty(name = "直播间类型", value = "1", dataType = "int", example = "1")
    private Integer cateId;
}
