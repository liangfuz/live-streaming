package com.easy.live.streaming.data.entity.video;

import com.easy.live.streaming.data.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description:房间标签
 * @Author: zhangliangfu
 * @Create on: 2019-06-17 17:04
 */

@Data
@Entity
@Table(name = "video_live_room_tag")
public class VideoLiveRoomTag extends BaseEntity {
    private Integer roomId;
    private Integer tagId;
}
