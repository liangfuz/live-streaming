package com.easy.live.streaming.data.entity.video;

import com.easy.live.streaming.data.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description:直播标签
 * @Author: zhangliangfu
 * @Create on: 2019-06-17 16:17
 */

@Data
@Entity
@Table(name = "video_live_tag")
public class VideoLiveTag extends BaseEntity {
    private String name;
}
