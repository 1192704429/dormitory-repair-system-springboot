package com.studentlife.repair.module.dorm_room.entity;

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
 * @description: 宿舍信息实体类
 */
@Data
@TableName("dorm_room")
public class DormRoom implements Serializable {

    private static final long serialVersionUID=1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 所属楼栋 */
    private Long buildingId;
    /** 宿舍号 */
    private String roomNo;
    /** 所在楼层 */
    private Integer floorNo;
    /** 床位数 */
    private Integer bedCount;
    /** 已入住人数 */
    private Integer occupiedCount;
    /** 状态 */
    private Integer status;
    /** 备注 */
    private String remark;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /** 楼栋名称 */
    @TableField(exist = false)
    private String buildingName;


}
