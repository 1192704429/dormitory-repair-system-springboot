package com.studentlife.repair.module.material.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.studentlife.repair.module.material.entity.Material;

import java.util.List;

/**
 * @author wuming
 * @description: 维修材料Service接口
 */
public interface MaterialService extends IService<Material> {

    /**
     * 分页查询
     */
    IPage<Material> pageQuery(Integer current, Integer size, Material entity);

    /**
     * 列表查询（不分页）
     */
    List<Material> listQuery(Material entity);

    /**
     * 根据ID查询
     */
    Material getById(Long id);

}
