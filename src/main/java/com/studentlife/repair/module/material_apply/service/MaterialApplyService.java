package com.studentlife.repair.module.material_apply.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.studentlife.repair.module.material_apply.entity.MaterialApply;
import com.studentlife.repair.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 维修材料申请Service接口
 */
public interface MaterialApplyService extends IService<MaterialApply> {

    /**
     * 分页查询
     */
    IPage<MaterialApply> pageQuery(Integer current, Integer size, MaterialApply entity);

    /**
     * 列表查询（不分页）
     */
    List<MaterialApply> listQuery(MaterialApply entity);

    /**
     * 根据ID查询
     */
    MaterialApply getById(Long id);

    /**
     * 下拉数据：sys_user
     */
    List<SelectVO> listSysUser();

    /**
     * 下拉数据：repair_order
     */
    List<SelectVO> listRepairOrder();

    /**
     * 下拉数据：material
     */
    List<SelectVO> listMaterial();

}
