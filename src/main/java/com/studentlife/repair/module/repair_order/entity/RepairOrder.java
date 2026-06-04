package com.studentlife.repair.module.repair_order.entity;

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
 * @description: 报修单实体类
 */
@Data
@TableName("repair_order")
public class RepairOrder implements Serializable {

    private static final long serialVersionUID=1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 报修单号 */
    private String orderNo;
    /** 报修学生 */
    private Long studentUserId;
    /** 所在楼栋 */
    private Long buildingId;
    /** 所在宿舍 */
    private Long roomId;
    /** 故障类型 */
    private Integer faultType;
    /** 问题标题 */
    private String faultTitle;
    /** 问题描述 */
    private String faultDesc;
    /** 报修图片 */
    private String images;
    /** 审核状态 */
    private Integer auditStatus;
    /** 审核宿管 */
    private Long auditUserId;
    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime auditTime;
    /** 审核意见 */
    private String auditRemark;
    /** 派工状态 */
    private Integer assignStatus;
    /** 维修人员 */
    private Long workerUserId;
    /** 派工时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime assignTime;
    /** 工单状态 */
    private Integer orderStatus;
    /** 完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finishTime;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /** 真实姓名 */
    @TableField(exist = false)
    private String realName;
    /** 楼栋名称 */
    @TableField(exist = false)
    private String buildingName;
    /** 宿舍号 */
    @TableField(exist = false)
    private String roomNo;
    /** 真实姓名 */
    @TableField(exist = false)
    private String realName2;
    /** 真实姓名 */
    @TableField(exist = false)
    private String realName3;

    // 范围查询字段
    /** 审核时间 开始 */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime auditTimeStart;
    /** 审核时间 结束 */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime auditTimeEnd;
    /** 派工时间 开始 */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime assignTimeStart;
    /** 派工时间 结束 */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime assignTimeEnd;
    /** 完成时间 开始 */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finishTimeStart;
    /** 完成时间 结束 */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finishTimeEnd;
    /** 当前用户ID（用于数据过滤） */
    @TableField(exist = false)
    private Long currentUserId;
    /** 当前用户角色（用于数据过滤） */
    @TableField(exist = false)
    private String currentUserRole;

}
