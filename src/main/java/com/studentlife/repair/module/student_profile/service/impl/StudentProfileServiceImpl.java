package com.studentlife.repair.module.student_profile.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.studentlife.repair.module.student_profile.entity.StudentProfile;
import com.studentlife.repair.module.student_profile.mapper.StudentProfileMapper;
import com.studentlife.repair.module.student_profile.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studentlife.repair.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 学生档案Service实现类
 */
@Service
public class StudentProfileServiceImpl extends ServiceImpl<StudentProfileMapper, StudentProfile> implements StudentProfileService {

    @Autowired
    private StudentProfileMapper studentProfileMapper;

    @Override
    public IPage<StudentProfile> pageQuery(Integer current, Integer size, StudentProfile entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<StudentProfile> records = studentProfileMapper.pageQuery(entity, offset, limit);
        Long total = studentProfileMapper.countQuery(entity);

        Page<StudentProfile> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<StudentProfile> listQuery(StudentProfile entity) {
        return studentProfileMapper.listQuery(entity);
    }

    @Override
    public StudentProfile getById(Long id) {
        return studentProfileMapper.getById(id);
    }

    /**
     * 下拉数据：dorm_room
     */
    @Override
    public List<SelectVO> listDormRoom() {
        return studentProfileMapper.listDormRoom();
    }

}
