package com.studentlife.repair.module.dorm_building.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.studentlife.repair.module.dorm_building.entity.DormBuilding;
import com.studentlife.repair.module.dorm_building.mapper.DormBuildingMapper;
import com.studentlife.repair.module.dorm_building.service.DormBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studentlife.repair.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 楼栋信息Service实现类
 */
@Service
public class DormBuildingServiceImpl extends ServiceImpl<DormBuildingMapper, DormBuilding> implements DormBuildingService {

    @Autowired
    private DormBuildingMapper dormBuildingMapper;

    @Override
    public IPage<DormBuilding> pageQuery(Integer current, Integer size, DormBuilding entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<DormBuilding> records = dormBuildingMapper.pageQuery(entity, offset, limit);
        Long total = dormBuildingMapper.countQuery(entity);

        Page<DormBuilding> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<DormBuilding> listQuery(DormBuilding entity) {
        return dormBuildingMapper.listQuery(entity);
    }

    @Override
    public DormBuilding getById(Long id) {
        return dormBuildingMapper.getById(id);
    }

    /**
     * 下拉数据：sys_user
     */
    @Override
    public List<SelectVO> listSysUser() {
        return dormBuildingMapper.listSysUser();
    }

}
