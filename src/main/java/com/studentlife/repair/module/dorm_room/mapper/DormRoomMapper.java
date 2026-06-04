package com.studentlife.repair.module.dorm_room.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.studentlife.repair.module.dorm_room.entity.DormRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.studentlife.repair.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 宿舍信息Mapper接口
 */
@Mapper
public interface DormRoomMapper extends BaseMapper<DormRoom> {

    /**
     * 分页查询
     */
    List<DormRoom> pageQuery(@Param("entity") DormRoom entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") DormRoom entity);

    /**
     * 列表查询（不分页）
     */
    List<DormRoom> listQuery(@Param("entity") DormRoom entity);

    /**
     * 根据ID查询
     */
    DormRoom getById(@Param("id") Long id);

    /**
     * 下拉选项查询
     */
    /**
     * 下拉数据：dorm_building
     */
    @Select("select building_name as label, id as value from dorm_building")
    List<SelectVO> listDormBuilding();

}
