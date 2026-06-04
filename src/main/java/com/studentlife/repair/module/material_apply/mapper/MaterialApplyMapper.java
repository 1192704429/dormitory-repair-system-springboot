package com.studentlife.repair.module.material_apply.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.studentlife.repair.module.material_apply.entity.MaterialApply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.studentlife.repair.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 维修材料申请Mapper接口
 */
@Mapper
public interface MaterialApplyMapper extends BaseMapper<MaterialApply> {

    /**
     * 分页查询
     */
    List<MaterialApply> pageQuery(@Param("entity") MaterialApply entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") MaterialApply entity);

    /**
     * 列表查询（不分页）
     */
    List<MaterialApply> listQuery(@Param("entity") MaterialApply entity);

    /**
     * 根据ID查询
     */
    MaterialApply getById(@Param("id") Long id);

    /**
     * 下拉选项查询
     */
    /**
     * 下拉数据：sys_user
     */
    @Select("select real_name as label, id as value from sys_user")
    List<SelectVO> listSysUser();

    /**
     * 下拉数据：repair_order
     */
    @Select("select order_no as label, id as value from repair_order")
    List<SelectVO> listRepairOrder();

    /**
     * 下拉数据：material
     */
    @Select("select material_name as label, id as value from material")
    List<SelectVO> listMaterial();

}
