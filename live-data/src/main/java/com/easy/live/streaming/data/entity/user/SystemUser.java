package com.easy.live.streaming.data.entity.user;

import com.easy.live.streaming.data.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * <用户表>
 * 
 */
@Data
@Entity
@Table(name = "system_user")
public class SystemUser extends BaseEntity
{
    /**
     * 注释
     */
    private static final long serialVersionUID = 1007873213039304224L;

    private String account;

    private String name;
    
    // 不持久化到数据库，也不显示在Restful接口的属性.
    @Transient
    @JsonIgnore
    private String plainPassword;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String salt;
    //类型（超级用户|普通用户）
    private Integer type;

    @Column(name = "use_flag")
    private Boolean useFlag=true;

    private String phone;

    private String img;

    private String email;

    private String remark;

    private String address;

    private String company;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "system_user_role_rel", joinColumns = {
            @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<SystemRole> roleSet = new HashSet<>();


    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

}