package com.studentlife.repair.module.sys_notify.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.studentlife.repair.module.sys_notify.entity.SysNotify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuming
 * @description: 系统通知Mapper接口
 */
@Mapper
public interface SysNotifyMapper extends BaseMapper<SysNotify> {

    /**
     * 分页查询
     */
    List<SysNotify> pageQuery(@Param("entity") SysNotify entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") SysNotify entity);

    /**
     * 列表查询（不分页）
     */
    List<SysNotify> listQuery(@Param("entity") SysNotify entity);

    /**
     * 根据ID查询
     */
    SysNotify getById(@Param("id") Long id);

}
