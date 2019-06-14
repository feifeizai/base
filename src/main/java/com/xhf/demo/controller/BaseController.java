package com.xhf.demo.controller;

import com.xhf.demo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class BaseController<T,ID extends Serializable> {

    @Autowired
    BaseService<T,ID> baseService;

    @PostMapping("/findType")
    public List<T> findType(@RequestBody T t){
       return baseService.findAll(t);
    }

    @GetMapping("/find")
    public List<T> find(){
        return baseService.findAll();
    }

    @GetMapping("/find/{id}")
    public T find(@PathVariable("id") ID id){
        Optional<T> optional = baseService.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @PostMapping("/update")
    public T update(@RequestBody T t){
        return baseService.update(t);
    }

}
