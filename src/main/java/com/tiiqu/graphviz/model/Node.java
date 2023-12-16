package com.tiiqu.graphviz.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * author : ALEXLIU
 * mail : ninphone@gmail.com
 * created : 12/12/2023, Tuesday
 **/
@Data
@ApiModel("node")
@TableName(value = "nodes")
public class Node {

    @ApiModelProperty("id")
    int id;

    @ApiModelProperty("label")
    private String label;

    @ApiModelProperty("url")
    private String url;

    @ApiModelProperty("x")
    private Float x;

    @ApiModelProperty("y")
    private Float y;
}