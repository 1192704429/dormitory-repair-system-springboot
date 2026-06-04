package com.studentlife.repair.module.repair_record.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.studentlife.repair.module.repair_record.entity.RepairRecord;
import com.studentlife.repair.module.repair_record.mapper.RepairRecordMapper;
import com.studentlife.repair.module.repair_record.service.RepairRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studentlife.repair.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 报修记录Service实现类
 */
@Service
public class RepairRecordServiceImpl extends ServiceImpl<RepairRecordMapper, RepairRecord> implements RepairRecordService {

    @Autowired
    private RepairRecordMapper repairRecordMapper;

    @Override
    public IPage<RepairRecord> pageQuery(Integer current, Integer size, RepairRecord entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<RepairRecord> records = repairRecordMapper.pageQuery(entity, offset, limit);
        Long total = repairRecordMapper.countQuery(entity);

        Page<RepairRecord> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<RepairRecord> listQuery(RepairRecord entity) {
        return repairRecordMapper.listQuery(entity);
    }

    @Override
    public RepairRecord getById(Long id) {
        return repairRecordMapper.getById(id);
    }

    /**
     * 下拉数据：sys_user
     */
    @Override
    public List<SelectVO> listSysUser() {
        return repairRecordMapper.listSysUser();
    }

    /**
     * 下拉数据：repair_order
     */
    @Override
    public List<SelectVO> listRepairOrder() {
        return repairRecordMapper.listRepairOrder();
    }

}
