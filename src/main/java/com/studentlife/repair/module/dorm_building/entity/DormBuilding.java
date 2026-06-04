package com.studentlife.repair.module.dorm_building.entity;

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
 * @description: 楼栋信息实体类
 */
@Data
@TableName("dorm_building")
public class DormBuilding implements Serializable {

    private static final long serialVersionUID=1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 楼栋名称 */
    private String buildingName;
    /** 楼栋编号 */
    private String buildingNo;
    /** 楼栋地址 */
    private String address;
    /** 楼层数 */
    private Integer floors;
    /** 宿舍类型 */
    private Integer roomType;
    /** 每层宿舍数 */
    private Integer roomsPerFloor;
    /** 绑定宿管 */
    private Long managerUserId;
    /** 状态 */
    private Integer status;
    /** 楼栋说明 */
    private String description;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /** 真实姓名 */
    @TableField(exist = false)
    private String realName;


}
