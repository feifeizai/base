package com.xhf.demo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author 谢红飞
 * @Title:
 * @Description:
 * @date 2020-3-26 22:28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "分页请求参数")
public class PageRequestInfo {

    @ApiModelProperty(value = "当前页面", example = "1")
    @NotNull(message = "分页不允许为空")
    @Min(value = 1, message = "分页最小值1")
    private Integer page = 1;

    @ApiModelProperty(value = "分页大小", example = "20")
    @NotNull(message = "分页大小不允许为空")
    @Min(value = 10, message = "分页大小最小值10")
    @Max(value = 100, message = "分页大小最大是100")
    private Integer size = 20;

    @ApiModelProperty(value = "排序方向,从小到大ASC, 从大到小DESC", example = "ASC", allowableValues = "asc,desc")
    private Sort.Direction direction;

    @ApiModelProperty(value = "按哪个字段排序", example = "age")
    private String property;

}

