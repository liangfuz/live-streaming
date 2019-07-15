package com.easy.live.streaming.data.entity.video;

import com.easy.live.streaming.data.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description:直播分类
 * @Author: zhangliangfu
 * @Create on: 2019-06-17 16:18
 */

@Data
@Entity
@Table(name = "video_live_cate")
public class VideoLiveCate extends BaseEntity {

    private String name;

}
