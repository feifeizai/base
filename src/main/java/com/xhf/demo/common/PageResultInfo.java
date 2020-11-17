package com.xhf.demo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.List;

/**
 * @author 谢红飞
 * date 2020-3-28 19:43
 */
@Data
@ApiModel(description = "分页返回结果")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResultInfo<T> {

    @ApiModelProperty(value = "当前页面", example = "1")
    private int page = 1;

    @ApiModelProperty(value = "分页大小", example = "20")
    private int size = 20;

    @ApiModelProperty(value = "排序方向,从小到大ASC, 从大到小DESC", example = "ASC")
    private Sort.Direction direction;

    @ApiModelProperty(value = "按哪个字段排序", example = "age")
    private String property;

    @ApiModelProperty(value = "总记录数", example = "121")
    private int total;

    @ApiModelProperty(value = "总页数", example = "121")
    private int totalPages;

    @ApiModelProperty(value = "上一页", example = "0")
    private int lastPage;

    @ApiModelProperty(value = "下一页", example = "2")
    private int nextPage;

    @ApiModelProperty(value = "是否是第一页", example = "true")
    private boolean isFirstPage;

    @ApiModelProperty(value = "是否是最后一页", example = "true")
    private boolean isLastPage;

    @ApiModelProperty(value = "数据集合")
    private List<T> list;

    public PageResultInfo(Page<T> pageInfo) {
        this.page = pageInfo.getPageable().getPageNumber() + 1;
        this.size = pageInfo.getPageable().getPageSize();
        Sort sort = pageInfo.getPageable().getSort();
        if (!Sort.unsorted().equals(sort)) {
            Iterator<Sort.Order> iterator = sort.iterator();
            Sort.Order order = iterator.next();
            this.direction = order.getDirection();
            this.property = order.getProperty();
        }
        this.total = pageInfo.getNumberOfElements();
        this.totalPages = pageInfo.getTotalPages();
        this.isFirstPage = page == 1;
        this.isLastPage = page == totalPages;
        this.lastPage = page == 1 ? 1 : page - 1;
        this.nextPage = isLastPage ? page : page + 1;
        this.list = pageInfo.getContent();
    }

    public boolean getIsFirstPage() {
        return isFirstPage;
    }

    public boolean getIsLastPage() {
        return isLastPage;
    }

}
