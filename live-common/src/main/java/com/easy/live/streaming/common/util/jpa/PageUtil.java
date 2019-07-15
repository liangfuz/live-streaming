package com.easy.live.streaming.common.util.jpa;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

/**
 * 
 * 分页工具类
 * 
 */
public class PageUtil
{
    
    /**
     * 创建分页请求.
     */
    public static PageRequest buildPageRequest(int pageNumber, int pageSize)
    {
        Sort sort = null;
        return buildPageRequest(pageNumber, pageSize, sort);
    }
    
    /**
     * 
     * <创建分页请求,并按照指定的顺序和属性进行排序 单个字段排序><br />
     * <比如direction为"desc",oderBy为id,会按照id降序>
     *
     * @param pageNumber
     * @param pageSize
     * @param direction
     * @param orderBy
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static PageRequest buildPageRequest(int pageNumber, int pageSize, Direction direction, String orderBy)
    {
        Sort sort = null;
        if (null != direction && StringUtils.isNotBlank(orderBy))
        {
            sort = new Sort(direction, orderBy);
        }
        return buildPageRequest(pageNumber, pageSize, sort);
    }
    
    public static PageRequest buildPageRequest(int pageNumber, int pageSize, String direction, String... orderBys)
    {
        Sort sort = null;
        if ("desc".equalsIgnoreCase(direction))
        {
            sort = new Sort(Direction.DESC, orderBys);
        }
        else
        {
            sort = new Sort(Direction.ASC, orderBys);
        }
        return new PageRequest(pageNumber - 1, pageSize, sort);
    }
    
    /**
     * 
     * <创建分页请求,并按照指定的顺序和属性进行排序 排序方向相同的多个字段排序><br />
     * <比如direction为"desc",oderBy为{"id","age"},会按照id降序,然后age降序>
     *
     * @param pageNumber
     * @param pageSize
     * @param direction
     * @param orderBys
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static PageRequest buildPageRequest(int pageNumber, int pageSize, Direction direction, String... orderBys)
    {
        Sort sort = null;
        if (null != direction && ArrayUtils.isNotEmpty(orderBys))
        {
            sort = new Sort(direction, orderBys);
        }
        return buildPageRequest(pageNumber, pageSize, sort);
    }
    
    /**
     * 
     * <创建分页请求,并按照指定的顺序和属性进行排序 排序方向相同的多个字段排序><br />
     * <比如direction为"desc",oderBy为{"id","age"},会按照id降序,然后age降序>
     *
     * @param pageNumber
     * @param pageSize
     * @param direction
     * @param orderBys
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static PageRequest buildPageRequest(int pageNumber, int pageSize, Direction direction, List<String> orderBys)
    {
        Sort sort = null;
        if (null != direction && CollectionUtils.isNotEmpty(orderBys))
        {
            sort = new Sort(direction, orderBys);
        }
        return buildPageRequest(pageNumber, pageSize, sort);
    }
    
    /**
     * 
     * <创建分页请求,并按照指定的顺序和属性进行排序 排序方向不同的多个字段排序><br />
     * <例如directions中包含排序方向{"asc","desc","asc"},orderBys中包含需要排序的字段
     * {"id","age","name"},则排序的效果为先按照id升序,再按age降序,再按name升序排列.directions和oderBys list大小要相同>
     *
     * @param pageNumber
     * @param pageSize
     * @param directions
     * @param orderBys
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static PageRequest buildPageRequest(int pageNumber, int pageSize, List<Direction> directions,
                                               List<String> orderBys)
    {
        Sort sort = null;
        if (CollectionUtils.isNotEmpty(directions) && CollectionUtils.isNotEmpty(orderBys))
        {
            Validate.isTrue((directions.size() == orderBys.size()), "directions of size eq to the size of orderBys.");
            for (int i = 0, len = directions.size(); i < len; i++)
            {
                if (i == 0)
                {
                    sort = new Sort(directions.get(i), orderBys.get(i));
                }
                else
                {
                    sort.and(new Sort(directions.get(i), orderBys.get(i)));
                }
            }
        }
        return buildPageRequest(pageNumber, pageSize, sort);
    }
    
    public static PageRequest buildPageRequest(int pageNumber, int pageSize, Sort sort)
    {
        Validate.isTrue((pageNumber >= 1), "pageNumber must be gte one.");
        Validate.isTrue((pageSize >= 1), "pageSize must be gte one.");
        
        return new PageRequest(pageNumber - 1, pageSize, sort);
    }
    
    /**
     * 
     * <创建动态查询条件组合><br />
     * <功能详细描述>
     *
     * @param searchParams
     * @param t
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public static <T> Specification<T> buildSpecification(Map<String, Object> searchParams, Class<T> t)
    {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<T> spec = (Specification<T>)DynamicSpecifications.bySearchFilter(filters.values(), t.getClass());
        return spec;
    }
    
}
