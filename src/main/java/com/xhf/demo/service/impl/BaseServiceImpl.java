package com.xhf.demo.service.impl;

import com.xhf.demo.common.PageRequestInfo;
import com.xhf.demo.repository.BaseRepository;
import com.xhf.demo.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    private Class clazz;

    public BaseServiceImpl() {
        ParameterizedType type = (ParameterizedType) (this.getClass().getGenericSuperclass());
        clazz = (Class) type.getActualTypeArguments()[0];
    }

    @Autowired
    BaseRepository<T, ID> baseRepository;

    @Override
    @Transactional
    public T save(T t) {
        return baseRepository.save(t);
    }

    @Override
    @Transactional
    public List<T> saveOrUpdateAll(Iterable<T> iterate) {
        return baseRepository.saveAll(iterate);
    }

    /**
     * 保存的时候要求忽略为null的更新
     */
    @Override
    @Transactional
    public T update(T t) {
        //Class所有的属性
        Field[] fields = clazz.getDeclaredFields();
        String fieldName = null;
        ID id = null;
        for (Field field : fields) {
            //获取对应的注解
            Id annotation = field.getAnnotation(Id.class);
            if (annotation != null) {
                //如果注解不为空，说明是主键对应的属性
                fieldName = field.getName();
                System.out.println("主键字段是fieldName:" + fieldName);
                id = (ID) getFieldValueByName(fieldName, t);
                break;
            }
        }
        Optional<T> option = findById(id);
        if (option.isPresent()) {
            T source = option.get();
            copyNullProperties(source, t);
        }
        return baseRepository.saveAndFlush(t);
    }

    /**
     *  
     *  * 根据属性名获取属性值 
     *  * 
     */
    protected Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            //方法getId()
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将目标源中不为空的字段过滤，将数据库中查出的数据源复制到提交的目标源中
     *
     * @param source 用id从数据库中查出来的数据源
     * @param target 提交的实体，目标源
     *               第三个参数是需要忽略的字段, 即target中那些不为空的字段
     */
    protected void copyNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNoNullProperties(target));
    }

    /**
     * @param target 目标源数据
     * @return 将目标源中不为空的字段取出
     */
    protected String[] getNoNullProperties(Object target) {
        BeanWrapper srcBean = new BeanWrapperImpl(target);
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        Set<String> noEmptyName = new HashSet<>();
        for (PropertyDescriptor p : pds) {
            noEmptyName.add(p.getName());
        }
        String[] result = new String[noEmptyName.size()];
        return noEmptyName.toArray(result);
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        baseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(T t) {
        baseRepository.delete(t);
    }

    @Override
    @Transactional
    public void deleteInBatch(Iterable<T> iterate) {
        baseRepository.deleteInBatch(iterate);
    }

    @Override
    @Transactional
    public void deleteAllByIdIn(Iterable<ID> iterate) {
        baseRepository.deleteAllByIdIn(iterate);
    }

    @Override
    public boolean exists(T t) {
        Example<T> example = Example.of(t);
        return baseRepository.exists(example);
    }

    @Override
    public boolean existsById(ID id) {
        return baseRepository.existsById(id);
    }

    @Override
    public long count() {
        return baseRepository.count();
    }

    @Override
    public long count(T t) {
        Example<T> example = Example.of(t);
        return baseRepository.count(example);
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public Optional findOne(T t) {
        Example<T> example = Example.of(t);
        return baseRepository.findOne(example);
    }

    @Override
    public List<T> findAll(Sort sort) {
        return baseRepository.findAll(sort);
    }

    @Override
    public List<T> findAll(T t) {
        Example<T> example = Example.of(t);
        return baseRepository.findAll(example);
    }

    @Override
    public Page<T> findAll(T t, PageRequestInfo pageRequest) {
        PageRequest request = transPageRequest(pageRequest);
        Example<T> example = Example.of(t);
        return baseRepository.findAll(example, request);
    }

    @Override
    public Page<T> findAll(PageRequestInfo pageRequest) {
        PageRequest request = transPageRequest(pageRequest);
        return baseRepository.findAll(request);
    }

    @Override
    public List<T> findAll(T t, Sort sort) {
        Example<T> example = Example.of(t);
        return baseRepository.findAll(example, sort);
    }

    @Override
    public List<T> findAllById(Iterable<ID> iterate) {
        return baseRepository.findAllById(iterate);
    }

    @Override
    public Optional<T> findById(ID id) {
        return baseRepository.findById(id);
    }

    protected PageRequest transPageRequest(PageRequestInfo pageRequest) {
        Sort.Direction direction = pageRequest.getDirection();
        String property = pageRequest.getProperty();
        if (StringUtils.isNotBlank(property) && direction != null) {
            return PageRequest.of(pageRequest.getPage() - 1, pageRequest.getSize(), direction, property);
        } else {
            return PageRequest.of(pageRequest.getPage() - 1, pageRequest.getSize());
        }
    }
}
