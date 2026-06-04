package com.studentlife.repair.module.student_profile.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wuming
 * @description: 学生档案实体类
 */
@Data
@TableName("student_profile")
public class StudentProfile implements Serializable {

    private static final long serialVersionUID=1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 登录账号 */
    private String username;
    /** 登录密码 */
    private String password;
    /** 真实姓名 */
    private String realName;
    /** 性别 */
    private Integer gender;
    /** 手机号 */
    private String phone;
    /** 邮箱 */
    private String email;
    /** 头像地址 */
    private String avatar;
    /** 学号 */
    private String studentNo;
    /** 学院 */
    private String college;
    /** 专业 */
    private String major;
    /** 年级 */
    private String grade;
    /** 班级 */
    private String className;
    /** 证件号 */
    private String idCard;
    /** 状态 */
    private Integer status;
    /** 宿舍 */
    private Long roomId;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /** 宿舍号 */
    @TableField(exist = false)
    private String roomNo;
    /** 楼栋名称 */
    @TableField(exist = false)
    private String buildingName;


}
