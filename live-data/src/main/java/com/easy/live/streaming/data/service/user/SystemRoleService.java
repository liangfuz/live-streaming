package com.easy.live.streaming.data.service.user;

import com.easy.live.streaming.common.util.jpa.PageUtil;
import com.easy.live.streaming.data.entity.user.SystemRole;
import com.easy.live.streaming.data.repository.user.SystemRoleRepository;
import com.easy.live.streaming.data.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * <角色管理接口> <处理角色信息接口>
 * 
 */
@Service
public class SystemRoleService extends BaseService<SystemRole,Integer>
{
    
    @Autowired
    private SystemRoleRepository systemRoleRepository;

    
    /**
     * <保存角色信息>
     * 
     * @param entity
     */
    public void saveRole(SystemRole entity)
    {
        systemRoleRepository.save(entity);
    }

    
    /**
     * <根据id获得角色信息>
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    public SystemRole getRole(Integer id)
    {
        return systemRoleRepository.findOne(id);
    }
    
    /**
     * <获得所有角色信息>
     * 
     * @return
     */
    public List<SystemRole> getAllRole()
    {
        return systemRoleRepository.findAll();
    }
    
    /**
     * <删除角色信息>
     * 
     * @param id
     */
    public void deleteRole(Integer id)
    {
        systemRoleRepository.delete(id);
    }
    
    /**
     * <获得角色信息列表>
     * 
     * @param searchParams
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Page<SystemRole> getRoleList(Map<String, Object> searchParams, int pageNumber, int pageSize)
    {
        PageRequest pageRequest = PageUtil.buildPageRequest(pageNumber, pageSize);
        Specification<SystemRole> spec = PageUtil.buildSpecification(searchParams, SystemRole.class);
        return systemRoleRepository.findAll(spec, pageRequest);
    }
    
    /**
     * <获得用户没有绑定的角色信息列表>
     * 
     * @param roleId
     * @return
     */
    public List<SystemRole> getRoleLists(List<Integer> roleId)
    {
        return systemRoleRepository.findByIdNotIn(roleId);
        
    }
    
    /**
     * <根据角色名查询>
     * 
     * @param roleName
     * @return
     */
    public SystemRole findRoleByName(String roleName)
    {
        return systemRoleRepository.findByName(roleName);
    }
    
    /**
     * <一句话功能简述>
     * 
     * @param roleName
     * @param roleId
     * @return
     */
    public SystemRole findRoleByNameAndId(String roleName, Integer roleId)
    {
        return systemRoleRepository.findByIdAndName(roleId, roleName);
    }
    
    /**
     * <一句话功能简述>
     * 
     * @param roleId
     * @return
     */
    public SystemRole findOne(Integer roleId)
    {
        return systemRoleRepository.findOne(roleId);
    }
}
