package com.studentlife.repair.module.sys_notify.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.studentlife.repair.module.sys_notify.entity.SysNotify;

import java.util.List;

/**
 * @author wuming
 * @description: 系统通知Service接口
 */
public interface SysNotifyService extends IService<SysNotify> {

    /**
     * 分页查询
     */
    IPage<SysNotify> pageQuery(Integer current, Integer size, SysNotify entity);

    /**
     * 列表查询（不分页）
     */
    List<SysNotify> listQuery(SysNotify entity);

    /**
     * 根据ID查询
     */
    SysNotify getById(Long id);

}
