package com.studentlife.repair.module.material_apply.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.studentlife.repair.module.material_apply.entity.MaterialApply;
import com.studentlife.repair.module.material_apply.mapper.MaterialApplyMapper;
import com.studentlife.repair.module.material_apply.service.MaterialApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studentlife.repair.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 维修材料申请Service实现类
 */
@Service
public class MaterialApplyServiceImpl extends ServiceImpl<MaterialApplyMapper, MaterialApply> implements MaterialApplyService {

    @Autowired
    private MaterialApplyMapper materialApplyMapper;

    @Override
    public IPage<MaterialApply> pageQuery(Integer current, Integer size, MaterialApply entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<MaterialApply> records = materialApplyMapper.pageQuery(entity, offset, limit);
        Long total = materialApplyMapper.countQuery(entity);

        Page<MaterialApply> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<MaterialApply> listQuery(MaterialApply entity) {
        return materialApplyMapper.listQuery(entity);
    }

    @Override
    public MaterialApply getById(Long id) {
        return materialApplyMapper.getById(id);
    }

    /**
     * 下拉数据：sys_user
     */
    @Override
    public List<SelectVO> listSysUser() {
        return materialApplyMapper.listSysUser();
    }

    /**
     * 下拉数据：repair_order
     */
    @Override
    public List<SelectVO> listRepairOrder() {
        return materialApplyMapper.listRepairOrder();
    }

    /**
     * 下拉数据：material
     */
    @Override
    public List<SelectVO> listMaterial() {
        return materialApplyMapper.listMaterial();
    }

}
