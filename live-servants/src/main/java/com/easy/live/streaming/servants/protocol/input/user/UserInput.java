package com.easy.live.streaming.servants.protocol.input.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:用户入参
 * @Author: zhangliangfu
 * @Create on: 2019-07-10 19:07
 */

@Data
@ApiModel(description = "用户参数")
public class UserInput {
    @ApiModelProperty(name = "密码", dataType = "string")
    private String password;
    @ApiModelProperty(name = "用户头衔", dataType = "string", example = "直播区第一coder")
    private String title;
    @ApiModelProperty(name = "暂未启用")
    private String avatar;
    @ApiModelProperty(name = "用户ID", dataType = "int")
    private Integer id;
    @ApiModelProperty(name = "用户名称",  dataType = "string", example = "沉默的coder")
    private String name;
    @ApiModelProperty(name = "暂未启用", dataType = "string")
    private String email;
    @ApiModelProperty(name = "暂未启用", dataType = "string")
    private String phone;
    @ApiModelProperty(name = "暂未启用", dataType = "string")
    private boolean useFlag;
    @ApiModelProperty(name = "前端无用", dataType = "string")
    private String sessionId;
}
