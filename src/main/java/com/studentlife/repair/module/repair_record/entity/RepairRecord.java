package com.studentlife.repair.module.repair_record.entity;

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
 * @description: 报修记录实体类
 */
@Data
@TableName("repair_record")
public class RepairRecord implements Serializable {

    private static final long serialVersionUID=1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 所属报修单 */
    private Long orderId;
    /** 维修人员 */
    private Long workerUserId;
    /** 维修说明 */
    private String workDesc;
    /** 维修前图片 */
    private String beforeImg;
    /** 维修后图片 */
    private String afterImg;
    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime workStartTime;
    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime workEndTime;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /** 报修单号 */
    @TableField(exist = false)
    private String orderNo;
    /** 真实姓名 */
    @TableField(exist = false)
    private String realName;

    // 范围查询字段
    /** 开始时间 开始 */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime workStartTimeStart;
    /** 开始时间 结束 */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime workStartTimeEnd;
    /** 结束时间 开始 */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime workEndTimeStart;
    /** 结束时间 结束 */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime workEndTimeEnd;

}
