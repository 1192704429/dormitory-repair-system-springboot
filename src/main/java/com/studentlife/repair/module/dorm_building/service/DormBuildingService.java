package com.studentlife.repair.module.dorm_building.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.studentlife.repair.module.dorm_building.entity.DormBuilding;
import com.studentlife.repair.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 楼栋信息Service接口
 */
public interface DormBuildingService extends IService<DormBuilding> {

    /**
     * 分页查询
     */
    IPage<DormBuilding> pageQuery(Integer current, Integer size, DormBuilding entity);

    /**
     * 列表查询（不分页）
     */
    List<DormBuilding> listQuery(DormBuilding entity);

    /**
     * 根据ID查询
     */
    DormBuilding getById(Long id);

    /**
     * 下拉数据：sys_user
     */
    List<SelectVO> listSysUser();

}
