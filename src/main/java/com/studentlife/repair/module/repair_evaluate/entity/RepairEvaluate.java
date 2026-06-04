package com.studentlife.repair.module.repair_evaluate.entity;

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
 * @description: 报修评价实体类
 */
@Data
@TableName("repair_evaluate")
public class RepairEvaluate implements Serializable {

    private static final long serialVersionUID=1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 关联报修单 */
    private Long orderId;
    /** 评价学生 */
    private Long studentUserId;
    /** 评分(1-5) */
    private Integer score;
    /** 服务态度评分(1-5) */
    private Integer attitudeScore;
    /** 处理速度评分(1-5) */
    private Integer speedScore;
    /** 评价内容 */
    private String content;
    /** 评价图片 */
    private String images;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /** 报修单号 */
    @TableField(exist = false)
    private String orderNo;
    /** 真实姓名 */
    @TableField(exist = false)
    private String realName;


}
