package com.studentlife.repair.module.material.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.studentlife.repair.module.material.entity.Material;
import com.studentlife.repair.module.material.mapper.MaterialMapper;
import com.studentlife.repair.module.material.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuming
 * @description: 维修材料Service实现类
 */
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public IPage<Material> pageQuery(Integer current, Integer size, Material entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<Material> records = materialMapper.pageQuery(entity, offset, limit);
        Long total = materialMapper.countQuery(entity);

        Page<Material> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<Material> listQuery(Material entity) {
        return materialMapper.listQuery(entity);
    }

    @Override
    public Material getById(Long id) {
        return materialMapper.getById(id);
    }

}
