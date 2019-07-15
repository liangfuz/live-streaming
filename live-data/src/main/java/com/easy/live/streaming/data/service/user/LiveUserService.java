package com.easy.live.streaming.data.service.user;

import com.easy.live.streaming.data.entity.user.LiveUser;
import com.easy.live.streaming.data.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:直播用户
 * @Author: zhangliangfu
 * @Create on: 2019-07-12 14:25
 */

@Slf4j
@Service
public class LiveUserService extends BaseService<LiveUser,Integer> {

    public LiveUser findLiveUserByName(String name){
        Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("name", name);
        LiveUser one = null;
        try {
            one = findOne(searchParams);
        }catch (Exception e){
            log.error("查询出错，", e.getMessage());
        }
        return one;
    }
}
