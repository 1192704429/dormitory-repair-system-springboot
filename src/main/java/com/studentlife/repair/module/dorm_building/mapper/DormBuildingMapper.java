package com.studentlife.repair.module.dorm_building.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.studentlife.repair.module.dorm_building.entity.DormBuilding;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.studentlife.repair.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 楼栋信息Mapper接口
 */
@Mapper
public interface DormBuildingMapper extends BaseMapper<DormBuilding> {

    /**
     * 分页查询
     */
    List<DormBuilding> pageQuery(@Param("entity") DormBuilding entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") DormBuilding entity);

    /**
     * 列表查询（不分页）
     */
    List<DormBuilding> listQuery(@Param("entity") DormBuilding entity);

    /**
     * 根据ID查询
     */
    DormBuilding getById(@Param("id") Long id);

    /**
     * 下拉选项查询
     */
    /**
     * 下拉数据：sys_user
     */
    @Select("select real_name as label, id as value from sys_user")
    List<SelectVO> listSysUser();

}
