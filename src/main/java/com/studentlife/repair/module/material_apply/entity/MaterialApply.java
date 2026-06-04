package com.studentlife.repair.module.material_apply.entity;

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
 * @description: 维修材料申请实体类
 */
@Data
@TableName("material_apply")
public class MaterialApply implements Serializable {

    private static final long serialVersionUID=1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 申请单号 */
    private String applyNo;
    /** 关联报修单 */
    private Long orderId;
    /** 申请人(维修人员) */
    private Long workerUserId;
    /** 材料 */
    private Long materialId;
    /** 申请数量 */
    private Integer applyQty;
    /** 申请原因 */
    private String applyReason;
    /** 申请状态 */
    private Integer applyStatus;
    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime auditTime;
    /** 审核意见 */
    private String auditRemark;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /** 报修单号 */
    @TableField(exist = false)
    private String orderNo;
    /** 真实姓名 */
    @TableField(exist = false)
    private String realName;
    /** 材料名称 */
    @TableField(exist = false)
    private String materialName;
    /** 材料图片 */
    @TableField(exist = false)
    private String materialImg;

    // 范围查询字段
    /** 审核时间 开始 */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime auditTimeStart;
    /** 审核时间 结束 */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime auditTimeEnd;

}
