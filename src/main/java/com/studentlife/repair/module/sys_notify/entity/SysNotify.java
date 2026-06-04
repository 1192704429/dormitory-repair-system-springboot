package com.studentlife.repair.module.sys_notify.entity;

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
 * @description: 系统通知实体类
 */
@Data
@TableName("sys_notify")
public class SysNotify implements Serializable {

    private static final long serialVersionUID=1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 通知类型 */
    private Integer notifyType;
    /** 通知标题 */
    private String title;
    /** 通知封面图片 */
    private String images;
    /** 通知内容 */
    private String content;
    /** 通知级别 */
    private Integer level;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;


}
