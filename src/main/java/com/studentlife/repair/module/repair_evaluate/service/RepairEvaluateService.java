package com.studentlife.repair.module.repair_evaluate.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.studentlife.repair.module.repair_evaluate.entity.RepairEvaluate;
import com.studentlife.repair.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 报修评价Service接口
 */
public interface RepairEvaluateService extends IService<RepairEvaluate> {

    /**
     * 分页查询
     */
    IPage<RepairEvaluate> pageQuery(Integer current, Integer size, RepairEvaluate entity);

    /**
     * 列表查询（不分页）
     */
    List<RepairEvaluate> listQuery(RepairEvaluate entity);

    /**
     * 根据ID查询
     */
    RepairEvaluate getById(Long id);

    /**
     * 下拉数据：sys_user
     */
    List<SelectVO> listSysUser();

    /**
     * 下拉数据：repair_order
     */
    List<SelectVO> listRepairOrder();

}
