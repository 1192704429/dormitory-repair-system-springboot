package com.studentlife.repair.module.repair_order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.studentlife.repair.module.repair_order.entity.RepairOrder;
import com.studentlife.repair.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 报修单Service接口
 */
public interface RepairOrderService extends IService<RepairOrder> {

    /**
     * 分页查询
     */
    IPage<RepairOrder> pageQuery(Integer current, Integer size, RepairOrder entity);

    /**
     * 列表查询（不分页）
     */
    List<RepairOrder> listQuery(RepairOrder entity);

    /**
     * 根据ID查询
     */
    RepairOrder getById(Long id);

    /**
     * 下拉数据：dorm_building
     */
    List<SelectVO> listDormBuilding();

    /**
     * 下拉数据：dorm_room
     */
    List<SelectVO> listDormRoom();

    /**
     * 下拉数据：sys_user
     */
    List<SelectVO> listSysUser();

}
