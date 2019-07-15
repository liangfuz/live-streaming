package com.easy.live.streaming.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ZLF on 2017/6/13.
 */
@MappedSuperclass
public class AbstractEntity<ID extends Serializable> implements Entity<ID>, Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
    @JsonIgnore
    public boolean isNew() {
        if (this.id == null || "".equals(this.id)) {
            this.setId(null);
            return true;
        }

        return false;
    }
}
