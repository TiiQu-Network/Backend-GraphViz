package com.tiiqu.graphviz.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel("UserQueryDTO")
public class UserQueryDTO {
    @ApiModelProperty("id")
    Integer id;

    @ApiModelProperty("name")
    private String username;

    @ApiModelProperty("email")
    private String email;

    @ApiModelProperty("create time")
    private Date created;
}