package com.easy.live.streaming.data.entity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Created by ZLF on 2017/6/13.
 */
@MappedSuperclass
public abstract class BaseEntity extends AbstractEntity<Integer> {

    private Date createdTime;

    private Date modifiedTime;

    @PrePersist
    public void beforeAdd(){
        if (createdTime==null){
            createdTime=new Date();
        }
        modifiedTime=new Date();
    }
    @PreUpdate
    public void beforeModified(){
        modifiedTime=new Date();
    }

    @Column(updatable = false, name = "created_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreatedTime() {
        return createdTime;
    }
    @Column(updatable = false, name = "modified_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getModifiedTime() {
        return modifiedTime;
    }
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
