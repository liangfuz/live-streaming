package com.easy.live.streaming.data.service.user;

import com.easy.live.streaming.data.entity.user.SystemDic;
import com.easy.live.streaming.data.repository.user.SystemDicRepository;
import com.easy.live.streaming.data.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统字典表.
 * 
 * @author
 */
@Service
public class SystemDicService extends BaseService<SystemDic,Integer>
{
    @Autowired
    private SystemDicRepository systemDicRepository;
    public List<SystemDic> findDicByType(Integer type){
        return systemDicRepository.findDicByType(type);
    }
}
