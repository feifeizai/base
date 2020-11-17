package com.xhf.demo.service;

import com.xhf.demo.common.PageRequestInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID extends Serializable> {


    T save(T t);

    List<T> saveOrUpdateAll(Iterable<T> iterable);

    T update(T t);

    T updateSelective(T t);

    void deleteById(ID id);

    void delete(T t);

    /**
     * 删除集合中元素对应的记录
     */
    void deleteInBatch(Iterable<T> iterable);

    /**
     * 根据id集合删除元素
     */
    void deleteAllByIdIn(Iterable<ID> iterable);

    boolean exists(T t);

    boolean existsById(ID id);

    long count();

    long count(T t);

    List<T> findAll();

    /**
     * 如果找到的不是一个是两个，会报错
     */
    Optional<T> findOne(T t);

    /**
     * new Sort(Sort.Direction.ASC,"id");
     */
    List<T> findAll(Sort sort);

    List<T> findAll(T t);

    Page<T> findAll(T t, PageRequestInfo pageRequest);

    Page<T> findAll(PageRequestInfo pageRequest);

    List<T> findAll(T t, Sort sort);

    /**
     * 根据id集合查找结果
     */
    List<T> findAllById(Iterable<ID> iterable);

    Optional<T> findById(ID id);
}
