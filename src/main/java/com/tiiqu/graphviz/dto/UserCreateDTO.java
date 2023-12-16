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
@ApiModel("UserCreateDTO")
public class UserCreateDTO {

    @ApiModelProperty("name")
    private String username;

    @ApiModelProperty("password")
    private String password;

    @ApiModelProperty("email")
    private String email;

}