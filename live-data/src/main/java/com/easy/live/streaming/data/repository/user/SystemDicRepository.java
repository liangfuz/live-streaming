package com.easy.live.streaming.data.repository.user;

import com.easy.live.streaming.data.entity.user.SystemDic;
import com.easy.live.streaming.data.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SystemDicRepository extends BaseRepository<SystemDic, Integer>
{
    @Query("select s from SystemDic s where s.type =(?1) order by s.sort")
    List<SystemDic> findDicByType(Integer type);
}
