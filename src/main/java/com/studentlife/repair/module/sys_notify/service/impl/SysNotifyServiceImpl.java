package com.studentlife.repair.module.sys_notify.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.studentlife.repair.module.sys_notify.entity.SysNotify;
import com.studentlife.repair.module.sys_notify.mapper.SysNotifyMapper;
import com.studentlife.repair.module.sys_notify.service.SysNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuming
 * @description: 系统通知Service实现类
 */
@Service
public class SysNotifyServiceImpl extends ServiceImpl<SysNotifyMapper, SysNotify> implements SysNotifyService {

    @Autowired
    private SysNotifyMapper sysNotifyMapper;

    @Override
    public IPage<SysNotify> pageQuery(Integer current, Integer size, SysNotify entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<SysNotify> records = sysNotifyMapper.pageQuery(entity, offset, limit);
        Long total = sysNotifyMapper.countQuery(entity);

        Page<SysNotify> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<SysNotify> listQuery(SysNotify entity) {
        return sysNotifyMapper.listQuery(entity);
    }

    @Override
    public SysNotify getById(Long id) {
        return sysNotifyMapper.getById(id);
    }

}
