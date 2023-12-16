package com.tiiqu.graphviz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * author : ALEXLIU
 * mail : ninphone@gmail.com
 * created : 12/13/2023, Wednesday
 **/
@Data
@ApiModel("UpdateGraphDTO")
public class UpdateGraphDTO {

    @ApiModelProperty("source")
    private String source;

    @ApiModelProperty("description")
    private String description;
    
}