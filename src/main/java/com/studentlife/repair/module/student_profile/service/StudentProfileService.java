package com.studentlife.repair.module.student_profile.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.studentlife.repair.module.student_profile.entity.StudentProfile;
import com.studentlife.repair.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 学生档案Service接口
 */
public interface StudentProfileService extends IService<StudentProfile> {

    /**
     * 分页查询
     */
    IPage<StudentProfile> pageQuery(Integer current, Integer size, StudentProfile entity);

    /**
     * 列表查询（不分页）
     */
    List<StudentProfile> listQuery(StudentProfile entity);

    /**
     * 根据ID查询
     */
    StudentProfile getById(Long id);

    /**
     * 下拉数据：dorm_room
     */
    List<SelectVO> listDormRoom();

}
