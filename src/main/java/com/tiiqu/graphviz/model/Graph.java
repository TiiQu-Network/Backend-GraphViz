package com.tiiqu.graphviz.model;

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
@ApiModel("graph")
public class Graph {

    @ApiModelProperty("id")
    int graphId;

    @ApiModelProperty("name")
    private String graphName;

    @ApiModelProperty("update time")
    private Date time;

}