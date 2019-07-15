package com.easy.live.streaming.data.entity.user;
import com.easy.live.streaming.data.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * <系统菜单实体类>
 * 
 */
@Data
@Entity
@Table(name = "system_menu")
public class SystemMenu extends BaseEntity
{
    private static final long serialVersionUID = 12131231231233L;

    private String name;

    private String link;

    private Integer sort;

    @JsonProperty("parentId")
    @Column(name = "parent_id")
    private Integer parentId;

    @Transient
    private List<SystemMenu> childMenus;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
    

    
}