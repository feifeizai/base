package com.xhf.demo.controller;

import com.xhf.demo.common.DataT;
import com.xhf.demo.common.PageRequestInfo;
import com.xhf.demo.common.PageResultInfo;
import com.xhf.demo.service.BaseService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@Slf4j
public class BaseController<T, ID extends Serializable> {

    @Autowired
    BaseService<T, ID> baseService;

    @ApiOperation(value = "按类型查询接口")
    @GetMapping("/findType")
    public PageResultInfo<T> findType(@ModelAttribute T t, @ModelAttribute PageRequestInfo pageRequest) {
        return new PageResultInfo<>(baseService.findAll(t, pageRequest));
    }

    @ApiOperation(value = "查询接口", httpMethod = "GET")
    @GetMapping("/find")
    public PageResultInfo<T> find(@ModelAttribute PageRequestInfo pageRequest) {
        return new PageResultInfo<>(baseService.findAll(pageRequest));
    }

    @ApiOperation(value = "查询接口", httpMethod = "GET")
    @GetMapping("/list")
    public DataT<T> findAll() {
        List<T> list = baseService.findAll();
        return DataT.<T>builder().list(list).build();
    }

    @ApiOperation(value = "根据id查询接口")
    @GetMapping("/{id}")
    public DataT<T> find(@PathVariable("id") ID id) {
        T t = baseService.findById(id).orElse(null);
        return DataT.<T>builder().detail(t).build();
    }

    @ApiOperation(value = "更新接口")
    @PostMapping
    public DataT<T> add(@RequestBody T t) {
        T detail = baseService.save(t);
        return DataT.<T>builder().detail(detail).build();
    }

    @ApiOperation(value = "更新接口")
    @PutMapping
    public DataT<T> update(@RequestBody T t) {
        T detail = baseService.updateSelective(t);
        return DataT.<T>builder().detail(detail).build();
    }

    @ApiOperation(value = "更新接口")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") ID id) {
        baseService.deleteById(id);
    }

}
