package com.easy.live.streaming.data.service.user;
import com.easy.live.streaming.common.util.jpa.PageUtil;
import com.easy.live.streaming.data.entity.user.SystemMenu;
import com.easy.live.streaming.data.repository.user.SystemMenuRepository;
import com.easy.live.streaming.data.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <功能详细描述>
 * 
 */
@Service
public class SystemMenuService extends BaseService<SystemMenu,Integer>
{
    
    @Autowired
    private SystemMenuRepository systemMenuRepository;
    
    /**
     * <根据 parentId获得menu列表>
     * 
     * @param parentId
     * @return
     */
    public List<SystemMenu> getMenuByParentId(Integer parentId)
    {
        List<SystemMenu> list = systemMenuRepository.findByParentIdOrderBySortAsc(parentId);
        for (SystemMenu menu : list)
        {
            if (menu != null)
            {
                List<SystemMenu> childMenus = getMenuByParentId(menu.getId());
                menu.setChildMenus(childMenus);
            }
        }
        return list;
    }
    
    /**
     * <保存menu>
     * 
     * @param menu
     */
    public void saveMenu(SystemMenu menu)
    {
        systemMenuRepository.save(menu);
    }
    
    /**
     * <更新菜单信息>
     * 
     * @param
     */
    public void updateMenu(Integer parentId, Integer sort)
    {
        systemMenuRepository.updateSort(parentId, sort);
    }
    
    /**
     * <根据parentId获得最大的sortnum>
     * 
     * @param parentId
     * @return
     */
    public Integer getMaxSortNumByParentId(Integer parentId)
    {
        return systemMenuRepository.getMaxSortNumByParentId(parentId);
    }
    
    /**
     * <获得菜单管理信息>
     * 
     * @param searchParams
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Page<SystemMenu> getMenuList(Map<String, Object> searchParams, int pageNumber, int pageSize)
    {
        String parentId = "parentId";
        String sortnum = "sort";
        PageRequest pageRequest = PageUtil.buildPageRequest(pageNumber, pageSize, parentId, sortnum);
        Specification<SystemMenu> spec = PageUtil.buildSpecification(searchParams, SystemMenu.class);
        
        return systemMenuRepository.findAll(spec, pageRequest);
    }
    
    /**
     * <获取菜单列表>
     * 
     * @return
     */
    public List<SystemMenu> getAllMenuList()
    {
        return (List<SystemMenu>)systemMenuRepository.findAll();
    }
    
    /**
     * <根据menuId查询>
     * 
     * @param id
     * @return
     */
    public SystemMenu getMenuById(Integer id)
    {
        return systemMenuRepository.findOne(id);
    }

}
