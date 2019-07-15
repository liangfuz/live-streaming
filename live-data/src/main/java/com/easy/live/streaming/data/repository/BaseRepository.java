package com.easy.live.streaming.data.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * Created by ZLF on 2017/6/15.
 */

@NoRepositoryBean
public interface BaseRepository<T,ID extends Serializable> extends JpaSpecificationExecutor<T>,PagingAndSortingRepository<T,
        ID> {
}
