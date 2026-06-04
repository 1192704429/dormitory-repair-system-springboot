package com.studentlife.repair.module.material.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.studentlife.repair.module.material.entity.Material;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuming
 * @description: 维修材料Mapper接口
 */
@Mapper
public interface MaterialMapper extends BaseMapper<Material> {

    /**
     * 分页查询
     */
    List<Material> pageQuery(@Param("entity") Material entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") Material entity);

    /**
     * 列表查询（不分页）
     */
    List<Material> listQuery(@Param("entity") Material entity);

    /**
     * 根据ID查询
     */
    Material getById(@Param("id") Long id);

}
