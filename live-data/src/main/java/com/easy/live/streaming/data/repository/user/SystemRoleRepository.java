package com.easy.live.streaming.data.repository.user;

import com.easy.live.streaming.data.entity.user.SystemMenu;
import com.easy.live.streaming.data.entity.user.SystemRole;
import com.easy.live.streaming.data.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SystemRoleRepository extends BaseRepository<SystemRole, Integer>
{
    /**
     * <查询不在 所选roleid的 其他角色列表>
     * 
     * @param roleId
     * @return
     */
    List<SystemRole> findByIdNotIn(List<Integer> roleId);
    
    /**
     * <查询所有角色>
     * 
     * @return
     */
    List<SystemRole> findAll();
    
    /**
     * <一句话功能简述>
     * 
     * @param roleName
     * @return
     */
    SystemRole findByName(String roleName);
    
    /**
     * <一句话功能简述>
     * 
     * @param roleId
     * @param roleName
     * @return
     */
    @Query("select p from SystemRole p where name =?2 and id !=?1")
    SystemRole findByIdAndName(Integer roleId, String roleName);

    @Query("select distinct m from SystemRole p  join p.menuSet m where p.id in (?1)")
    List<SystemMenu> findMenusByRoleIds(List<Integer> roleIds);

}
