package com.oyd.mall.tiny.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * OSS上传成功后的回调参数
 */
@Data
public class OssCallbackParam {

    @ApiModelProperty("请求的回调地址")
    private String callbackUrl;
    @ApiModelProperty("回调时传入request中的参数")
    private String callbackBody;
    @ApiModelProperty("回调时传入参数的格式，比如表单提交形式")
    private String callbackBodyType;
}
