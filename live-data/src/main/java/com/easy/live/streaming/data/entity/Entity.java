package com.easy.live.streaming.data.entity;
import java.io.Serializable;

/**
 * Created by ZLF on 2017/6/13.
 */
public interface Entity<ID extends Serializable> {
    ID getId();
    void setId(final ID id);
    boolean isNew();
}
