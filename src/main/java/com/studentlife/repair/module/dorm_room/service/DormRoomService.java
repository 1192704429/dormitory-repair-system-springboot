package com.studentlife.repair.module.dorm_room.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.studentlife.repair.module.dorm_room.entity.DormRoom;
import com.studentlife.repair.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 宿舍信息Service接口
 */
public interface DormRoomService extends IService<DormRoom> {

    /**
     * 分页查询
     */
    IPage<DormRoom> pageQuery(Integer current, Integer size, DormRoom entity);

    /**
     * 列表查询（不分页）
     */
    List<DormRoom> listQuery(DormRoom entity);

    /**
     * 根据ID查询
     */
    DormRoom getById(Long id);

    /**
     * 下拉数据：dorm_building
     */
    List<SelectVO> listDormBuilding();

}
