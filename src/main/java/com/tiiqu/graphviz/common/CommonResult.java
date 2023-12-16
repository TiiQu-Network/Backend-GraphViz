package com.tiiqu.graphviz.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * author : ALEXLIU
 * mail : ninphone@gmail.com
 * created : 12/12/2023, Tuesday
 **/
@Data
@ApiModel("API common data")
public class CommonResult<T>{

    @ApiModelProperty("code,0 success,non 0 error")
    private Integer code;

    /*
     return message
     */
    @ApiModelProperty("message")
    private String msg;
    /*
     data
     */
    @ApiModelProperty("data")
    private T data;

    public CommonResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    /*
    success
     */
    public static <T> CommonResult<T> success(T data){
        return new CommonResult<>(201,"success",data);
    }

    public static <T> CommonResult<T> success(Integer code,String msg){
        return new CommonResult<>(code,msg,null);
    }
    /*
    error
     */
    public static <T> CommonResult<T> error(Integer code,String msg){
        return new CommonResult<>(code,msg,null);
    }


}
