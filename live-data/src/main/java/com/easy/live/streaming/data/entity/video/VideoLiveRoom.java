package com.easy.live.streaming.data.entity.video;

import com.easy.live.streaming.data.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @Description:视频直播房间
 * @Author: zhangliangfu
 * @Create on: 2019-06-17 16:04
 */

@Data
@Entity
@Table(name = "video_live_room")
public class VideoLiveRoom extends BaseEntity {

    //用户ID
    @Column(name = "user_id")
    private Integer userId;

    //房间名称
    @Column(name = "room_name")
    private String roomName;

    //标题
    @Column(name = "title")
    private String title;

    //观看人数
    @Column(name = "viewer_count")
    private Integer viewerCount;

    //封面
    @Column(name = "cover")
    private String cover;

    //类型
    @Column(name = "cate_id")
    private Integer cateId;

    //标签
    @Transient
    private List<VideoLiveTag> tags;
}
