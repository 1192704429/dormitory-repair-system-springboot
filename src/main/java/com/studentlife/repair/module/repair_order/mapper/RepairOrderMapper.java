package com.studentlife.repair.module.repair_order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.studentlife.repair.module.repair_order.entity.RepairOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.studentlife.repair.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 报修单Mapper接口
 */
@Mapper
public interface RepairOrderMapper extends BaseMapper<RepairOrder> {

    /**
     * 分页查询
     */
    List<RepairOrder> pageQuery(@Param("entity") RepairOrder entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") RepairOrder entity);

    /**
     * 列表查询（不分页）
     */
    List<RepairOrder> listQuery(@Param("entity") RepairOrder entity);

    /**
     * 根据ID查询
     */
    RepairOrder getById(@Param("id") Long id);

    /**
     * 下拉选项查询
     */
    /**
     * 下拉数据：dorm_building
     */
    @Select("select building_name as label, id as value from dorm_building")
    List<SelectVO> listDormBuilding();

    /**
     * 下拉数据：dorm_room
     */
    @Select("select room_no as label, id as value from dorm_room")
    List<SelectVO> listDormRoom();

    /**
     * 下拉数据：sys_user
     */
    @Select("select real_name as label, id as value from sys_user")
    List<SelectVO> listSysUser();

}
