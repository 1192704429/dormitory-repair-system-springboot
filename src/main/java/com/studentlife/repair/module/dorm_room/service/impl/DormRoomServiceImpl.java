package com.studentlife.repair.module.dorm_room.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.studentlife.repair.module.dorm_room.entity.DormRoom;
import com.studentlife.repair.module.dorm_room.mapper.DormRoomMapper;
import com.studentlife.repair.module.dorm_room.service.DormRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studentlife.repair.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 宿舍信息Service实现类
 */
@Service
public class DormRoomServiceImpl extends ServiceImpl<DormRoomMapper, DormRoom> implements DormRoomService {

    @Autowired
    private DormRoomMapper dormRoomMapper;

    @Override
    public IPage<DormRoom> pageQuery(Integer current, Integer size, DormRoom entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<DormRoom> records = dormRoomMapper.pageQuery(entity, offset, limit);
        Long total = dormRoomMapper.countQuery(entity);

        Page<DormRoom> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<DormRoom> listQuery(DormRoom entity) {
        return dormRoomMapper.listQuery(entity);
    }

    @Override
    public DormRoom getById(Long id) {
        return dormRoomMapper.getById(id);
    }

    /**
     * 下拉数据：dorm_building
     */
    @Override
    public List<SelectVO> listDormBuilding() {
        return dormRoomMapper.listDormBuilding();
    }

}
