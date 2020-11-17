package com.xhf.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

//@NoRepositoryBean,这样Spring Data Jpa在启动时就不会去实例化BaseRepository这个接口
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    void deleteAllByIdIn(Iterable<ID> iterable);

}
