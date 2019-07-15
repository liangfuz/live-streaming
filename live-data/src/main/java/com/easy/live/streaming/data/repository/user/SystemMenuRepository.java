package com.easy.live.streaming.data.repository.user;

import com.easy.live.streaming.data.entity.user.SystemMenu;
import com.easy.live.streaming.data.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SystemMenuRepository extends BaseRepository<SystemMenu, Integer>
{
    /**
     * 根据父id获取menu
     * 
     * @param parentId
     * @return
     */
    List<SystemMenu> findByParentId(Integer parentId);
    
    /**
     * 根据id获取menu
     * 
     * @param id
     * @return
     */
    List<SystemMenu> findById(Integer id);
    
    /**
     * <根据父id获取menu>
     * 
     * @param parentId
     * @return
     */
    List<SystemMenu> findByParentIdOrderBySortAsc(Integer parentId);
    
    /**
     * <更新菜单排序>
     * 
     * @param parentId
     * @param sort
     */
    @Modifying
    @Query("update SystemMenu set sort=sort-1  where parentId =?1 and sort>?2")
    void updateSort(Integer parentId, Integer sort);
    
    /**
     * <根据父菜单 查询 最大排序号>
     * 
     * @param parentId
     * @return
     */
    @Query("select MAX(sort) from SystemMenu where parentId =?1")
    Integer getMaxSortNumByParentId(Integer parentId);
    
    /**
     * <查询菜单>
     * 
     * @param menuIdList
     * @return
     */
    @Query("select s from SystemMenu s where s.id in (?1)")
    List<SystemMenu> findByMenuIds(List<Integer> menuIdList);

    @Query("select s from SystemMenu s order by s.sort")
    List<SystemMenu> findSortAll();
}
