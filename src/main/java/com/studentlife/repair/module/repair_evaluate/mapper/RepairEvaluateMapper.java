package com.studentlife.repair.module.repair_evaluate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.studentlife.repair.module.repair_evaluate.entity.RepairEvaluate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.studentlife.repair.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 报修评价Mapper接口
 */
@Mapper
public interface RepairEvaluateMapper extends BaseMapper<RepairEvaluate> {

    /**
     * 分页查询
     */
    List<RepairEvaluate> pageQuery(@Param("entity") RepairEvaluate entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") RepairEvaluate entity);

    /**
     * 列表查询（不分页）
     */
    List<RepairEvaluate> listQuery(@Param("entity") RepairEvaluate entity);

    /**
     * 根据ID查询
     */
    RepairEvaluate getById(@Param("id") Long id);

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

}
