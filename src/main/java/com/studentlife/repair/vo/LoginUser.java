package com.studentlife.repair.vo;

import lombok.Data;

/**
 * @author wuming
 * @description: 登录用户信息
 */
@Data
public class LoginUser {

    private Long id;

    private String name;

    private String avatar;

    private String introduction;

    private String role;
}
