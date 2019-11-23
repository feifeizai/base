#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import ${package}.repository.BaseRepository;
import ${package}.service.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
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

public class BaseServiceImpl <T,ID extends Serializable> implements BaseService<T, ID> {

    private Class clazz;

    public BaseServiceImpl() {
        ParameterizedType type = (ParameterizedType) (this.getClass().getGenericSuperclass());
        clazz = (Class)type.getActualTypeArguments()[0];
    }

    @Autowired
    BaseRepository<T, ID> ${artifactId}Repository;

    @Override
    @Transactional
    public T save(T t){
        return ${artifactId}Repository.save(t);
    }

    @Override
    @Transactional
    public List<T> saveOrUpdateAll(Iterable<T> iterable) {
        return ${artifactId}Repository.saveAll(iterable);
    }

    /**
     * 保存的时候要求忽略为null的更新
     * @param t
     * @return
     */
    @Override
    @Transactional
    public T update(T t) {
        //Class所有的属性
        Field[] fields = clazz.getDeclaredFields();
        String fieldname=null;
        ID id = null;
        for (Field field : fields) {
            //获取对应的注解
            Id annotation = field.getAnnotation(Id.class);
            if(annotation!=null){
                //如果注解不为空，说明是主键对应的属性
                fieldname = field.getName();
                System.out.println("主键字段是fieldname:"+fieldname);
                id = (ID) getFieldValueByName(fieldname,t);
                break;
            }
        }
        Optional<T> option = findById(id);
        if(option.isPresent()){
            T source = option.get();
            copyNullProperties(source,t);
        }
        return ${artifactId}Repository.saveAndFlush(t);
    }

    /** 
      * 根据属性名获取属性值 
      * */
    protected Object getFieldValueByName(String fieldName,Object o){
        try{
            String firstLetter=fieldName.substring(0,1).toUpperCase();
            //方法getId()
            String getter="get"+firstLetter+fieldName.substring(1);
            Method method=o.getClass().getMethod(getter,new Class[]{});
            Object value=method.invoke(o,new Object[]{});
            return value;
        }catch(Exception e){
            return null;
        }
    }

    /**
     * 将目标源中不为空的字段过滤，将数据库中查出的数据源复制到提交的目标源中
     *
     * @param source 用id从数据库中查出来的数据源
     * @param target 提交的实体，目标源
     * 第三个参数是需要忽略的字段, 即target中那些不为空的字段
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
            Object value = srcBean.getPropertyValue(p.getName());
            if (value != null) noEmptyName.add(p.getName());
        }
        String[] result = new String[noEmptyName.size()];
        return noEmptyName.toArray(result);
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        ${artifactId}Repository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(T t) {
        ${artifactId}Repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteInBatch(Iterable<T> iterable){
        ${artifactId}Repository.deleteInBatch(iterable);
    }
    @Override
    @Transactional
    public void deleteAllByIdIn(Iterable<ID> iterable){
        ${artifactId}Repository.deleteAllByIdIn(iterable);
    }
    @Override
    public boolean exists(T t) {
        Example<T> example = Example.of(t);
        return ${artifactId}Repository.exists(example);
    }
    @Override
    public boolean existsById(ID id) {
        return ${artifactId}Repository.existsById(id);
    }

    @Override
    public long count() {
        return ${artifactId}Repository.count();
    }

    @Override
    public long count(T t) {
        Example<T> example = Example.of(t);
        return ${artifactId}Repository.count(example);
    }

    @Override
    public List<T> findAll() {
        return ${artifactId}Repository.findAll();
    }
    @Override
    public Optional findOne(T t) {
        Example<T> example = Example.of(t);
        return ${artifactId}Repository.findOne(example);
    }

    @Override
    public List<T> findAll(Sort sort) {
        return ${artifactId}Repository.findAll(sort);
    }

    @Override
    public List<T> findAll(T t) {
        Example<T> example = Example.of(t);
        return ${artifactId}Repository.findAll(example);
    }

    @Override
    public Page<T> findAll(T t, Integer page,Integer size) {
        Example<T> example = Example.of(t);
        Pageable pageable = new PageRequest(page,size);
        return ${artifactId}Repository.findAll(example,pageable);
    }

    @Override
    public Page<T> findAll(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page,size);
        return ${artifactId}Repository.findAll(pageable);
    }

    @Override
    public List<T> findAll(T t, Sort sort) {
        Example<T> example = Example.of(t);
        return ${artifactId}Repository.findAll(example,sort);
    }

    @Override
    public List<T> findAllById(Iterable<ID> iterable){
        return ${artifactId}Repository.findAllById(iterable);
    }

    @Override
    public Optional<T> findById(ID id){
        return ${artifactId}Repository.findById(id);
    }

}
