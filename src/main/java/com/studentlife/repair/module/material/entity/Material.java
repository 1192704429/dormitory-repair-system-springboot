package com.studentlife.repair.module.material.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * @author wuming
 * @description: 维修材料实体类
 */
@Data
@TableName("material")
public class Material implements Serializable {

    private static final long serialVersionUID=1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 材料名称 */
    private String materialName;
    /** 材料编码 */
    private String materialCode;
    /** 材料类型 */
    private Integer materialType;
    /** 规格型号 */
    private String spec;
    /** 计量单位 */
    private String unit;
    /** 当前库存 */
    private Integer stockQty;
    /** 安全库存 */
    private Integer safeStockQty;
    /** 参考单价 */
    private BigDecimal unitPrice;
    /** 状态 */
    private Integer status;
    /** 材料图片 */
    private String img;
    /** 备注 */
    private String remark;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;


}
