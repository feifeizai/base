package com.xhf.demo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author 谢红飞
 * @Title:
 * @Description:
 * @date 2020-3-27 22:13
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataT<T> {

    /**
     * 返回数据为list时使用
     */
    private List<T> list;

    /**
     * 返回数据为单条数据时使用
     */
    private T detail;
}
