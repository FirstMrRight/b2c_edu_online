package com.online.edu.eduservice.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel(value = "Course查询对象", description = "课程查询对象封装")
public class Querycourse implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "课程名称,模糊查询")
    private String title;

    @ApiModelProperty(value = "课程价格,模糊查询")
    private String price;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;//使用String类型，前端传递过来的数据不需要转格式

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;

}
