package com.easy.live.streaming.data.entity.user;

import com.easy.live.streaming.data.entity.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <系统字典表>
 * 
 * @author
 * @version [1.0, 2015年7月17日]
 */
@Data
@Entity
@Table(name = "system_dic")
public class SystemDic extends BaseEntity
{
    
    /**
     * 注释内容
     */

    private Integer type;

    private String name;

    private String value;

    private Integer sort;

    
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}
