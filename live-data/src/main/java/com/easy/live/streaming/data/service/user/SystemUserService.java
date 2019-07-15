package com.easy.live.streaming.data.service.user;
import com.easy.live.streaming.common.util.jpa.PageUtil;
import com.easy.live.streaming.data.entity.user.SystemMenu;
import com.easy.live.streaming.data.entity.user.SystemRole;
import com.easy.live.streaming.data.entity.user.SystemUser;
import com.easy.live.streaming.data.repository.user.SystemMenuRepository;
import com.easy.live.streaming.data.repository.user.SystemUserRepository;
import com.easy.live.streaming.data.service.BaseService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <用户信息接口>
 * 
 *
 */
@Service
public class SystemUserService extends BaseService<SystemUser,Integer>
{
    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private SystemMenuRepository systemMenuRepository;
    
    /**
     * <查询分页返回的用户列表信息>
     * 
     * @param searchParams 查询参数
     * @param pageNumber 查询起始页
     * @param pageSize 查询页大小
     * @return
     * 
     */
    public Page<SystemUser> getUsers(Map<String, Object> searchParams, int pageNumber, int pageSize)
    {
        PageRequest pageRequest = PageUtil.buildPageRequest(pageNumber, pageSize);
        Specification<SystemUser> spec = PageUtil.buildSpecification(searchParams, SystemUser.class);
        return systemUserRepository.findAll(spec, pageRequest);
    }
    
    public List<SystemUser> findUser(Map<String, Object> searchParams, Sort sort)
    {
        Specification<SystemUser> spec = PageUtil.buildSpecification(searchParams, SystemUser.class);
        return systemUserRepository.findAll(spec, sort);
    }

    public List<SystemMenu> getMenuByUserId(Integer userId) {
        SystemUser user = systemUserRepository.findOne(userId);
        System.out.println(userId+"*****************");
        List<SystemMenu> menus = Lists.newArrayList();
            if (user.getType()==0) {
                menus = (List<SystemMenu>) systemMenuRepository.findAll();
            } else {
                Set<SystemRole> roles = user.getRoleSet();
                if (roles.isEmpty())
                    return Lists.newArrayList();
                for (SystemRole role : roles) {
                    for (SystemMenu menu : role.getMenuSet()) {
                        if (!menus.contains(menu)) {
                            menus.add(menu);
                        }
                    }
                }
            }
        return menus;
    }

    /**
     * 根据userId获取菜单列表 当前菜单较少，采取一次查询后封装 后续菜单增加后，可多次查询DB再封装
     *
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<SystemMenu> getMenuTreeByUserId(Integer userId)
    {
        List<SystemMenu> retMenus = Lists.newArrayList();
        List<SystemMenu> menus= getMenuByUserId(userId);
        List<SystemMenu>allMenus=(List<SystemMenu>) systemMenuRepository.findSortAll();
        if (!CollectionUtils.isEmpty(menus)){
            for(SystemMenu menu:allMenus){
                for (SystemMenu menut : menus){
                    if(menut.getParentId()!=null&&menut.getParentId().equals(menu.getId())){
                        if(!retMenus.contains(menu))
                        retMenus.add(menu);
                        if(menu.getChildMenus()==null){
                            menu.setChildMenus(Lists.<SystemMenu>newArrayList());
                        }
                        menu.getChildMenus().add(menut);
                    }
                }
            }
        }
        return retMenus;
    }
    public List<SystemMenu> getMenuTree()
    {
        List<SystemMenu> retMenus = Lists.newArrayList();
        List<SystemMenu> menus=(List<SystemMenu>) systemMenuRepository.findAll();
        List<SystemMenu>allMenus=(List<SystemMenu>) systemMenuRepository.findSortAll();
        if (!CollectionUtils.isEmpty(menus)){
            for(SystemMenu menu:allMenus){
                for (SystemMenu menut : menus){
                    if(menut.getParentId()!=null&&menut.getParentId().equals(menu.getId())){
                        if(!retMenus.contains(menu))
                            retMenus.add(menu);
                        if(menu.getChildMenus()==null){
                            menu.setChildMenus(Lists.<SystemMenu>newArrayList());
                        }
                        menu.getChildMenus().add(menut);
                    }
                }
            }
        }
        return retMenus;
    }

    public SystemUser findUserByAccount(String account){
        Map<String,Object> searchParams = new HashedMap();
        searchParams.put("EQ_account",account);
        List<SystemUser> systemUsers = findAll(searchParams);
        if (CollectionUtils.isEmpty(systemUsers)){
            return null;
        }
        return systemUsers.get(0);
    }
}
