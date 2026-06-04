package com.studentlife.repair.module.repair_record.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.studentlife.repair.module.repair_record.entity.RepairRecord;
import com.studentlife.repair.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 报修记录Service接口
 */
public interface RepairRecordService extends IService<RepairRecord> {

    /**
     * 分页查询
     */
    IPage<RepairRecord> pageQuery(Integer current, Integer size, RepairRecord entity);

    /**
     * 列表查询（不分页）
     */
    List<RepairRecord> listQuery(RepairRecord entity);

    /**
     * 根据ID查询
     */
    RepairRecord getById(Long id);

    /**
     * 下拉数据：sys_user
     */
    List<SelectVO> listSysUser();

    /**
     * 下拉数据：repair_order
     */
    List<SelectVO> listRepairOrder();

}
