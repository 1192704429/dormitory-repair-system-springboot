package com.studentlife.repair.module.repair_order.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.studentlife.repair.module.repair_order.entity.RepairOrder;
import com.studentlife.repair.module.repair_order.mapper.RepairOrderMapper;
import com.studentlife.repair.module.repair_order.service.RepairOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studentlife.repair.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 报修单Service实现类
 */
@Service
public class RepairOrderServiceImpl extends ServiceImpl<RepairOrderMapper, RepairOrder> implements RepairOrderService {

    @Autowired
    private RepairOrderMapper repairOrderMapper;

    @Override
    public IPage<RepairOrder> pageQuery(Integer current, Integer size, RepairOrder entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<RepairOrder> records = repairOrderMapper.pageQuery(entity, offset, limit);
        Long total = repairOrderMapper.countQuery(entity);

        Page<RepairOrder> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<RepairOrder> listQuery(RepairOrder entity) {
        return repairOrderMapper.listQuery(entity);
    }

    @Override
    public RepairOrder getById(Long id) {
        return repairOrderMapper.getById(id);
    }

    /**
     * 下拉数据：dorm_building
     */
    @Override
    public List<SelectVO> listDormBuilding() {
        return repairOrderMapper.listDormBuilding();
    }

    /**
     * 下拉数据：dorm_room
     */
    @Override
    public List<SelectVO> listDormRoom() {
        return repairOrderMapper.listDormRoom();
    }

    /**
     * 下拉数据：sys_user
     */
    @Override
    public List<SelectVO> listSysUser() {
        return repairOrderMapper.listSysUser();
    }

}
