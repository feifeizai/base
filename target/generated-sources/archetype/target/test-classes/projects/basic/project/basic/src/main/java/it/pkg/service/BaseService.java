package it.pkg.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService <T,ID extends Serializable>{


    T save(T t);

    List<T> saveOrUpdateAll(Iterable<T> iterable);

    T update(T t);

    void deleteById(ID id);

    void delete(T t);

    /**
     * 删除集合中元素对应的记录
     * @param iterable
     */
    void deleteInBatch(Iterable<T> iterable);

    /**
     * 根据id集合删除元素
     * @param iterable
     */
    void deleteAllByIdIn(Iterable<ID> iterable);

    boolean exists(T t);

    boolean existsById(ID id);

    long count();

    long count(T t);

    List<T> findAll();

    /**
     * 如果找到的不是一个是两个，会报错
     * @param t
     * @return
     */
    Optional findOne(T t);

    /**
     * new Sort(Sort.Direction.ASC,"id");
     * @param sort
     * @return
     */
    List<T> findAll(Sort sort);

    List<T> findAll(T t);

    Page<T> findAll(T t, Integer page, Integer size);

    Page<T> findAll(Integer page, Integer size);

    List<T> findAll(T t, Sort sort);

    /**
     * 根据id集合查找结果
     * @param iterable
     * @return
     */
    List<T> findAllById(Iterable<ID> iterable);

    Optional<T> findById(ID id);
}
