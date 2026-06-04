package com.studentlife.repair.module.repair_evaluate.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.studentlife.repair.module.repair_evaluate.entity.RepairEvaluate;
import com.studentlife.repair.module.repair_evaluate.mapper.RepairEvaluateMapper;
import com.studentlife.repair.module.repair_evaluate.service.RepairEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studentlife.repair.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 报修评价Service实现类
 */
@Service
public class RepairEvaluateServiceImpl extends ServiceImpl<RepairEvaluateMapper, RepairEvaluate> implements RepairEvaluateService {

    @Autowired
    private RepairEvaluateMapper repairEvaluateMapper;

    @Override
    public IPage<RepairEvaluate> pageQuery(Integer current, Integer size, RepairEvaluate entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<RepairEvaluate> records = repairEvaluateMapper.pageQuery(entity, offset, limit);
        Long total = repairEvaluateMapper.countQuery(entity);

        Page<RepairEvaluate> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<RepairEvaluate> listQuery(RepairEvaluate entity) {
        return repairEvaluateMapper.listQuery(entity);
    }

    @Override
    public RepairEvaluate getById(Long id) {
        return repairEvaluateMapper.getById(id);
    }

    /**
     * 下拉数据：sys_user
     */
    @Override
    public List<SelectVO> listSysUser() {
        return repairEvaluateMapper.listSysUser();
    }

    /**
     * 下拉数据：repair_order
     */
    @Override
    public List<SelectVO> listRepairOrder() {
        return repairEvaluateMapper.listRepairOrder();
    }

}
