package com.studentlife.repair.module.student_profile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.studentlife.repair.module.student_profile.entity.StudentProfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.studentlife.repair.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 学生档案Mapper接口
 */
@Mapper
public interface StudentProfileMapper extends BaseMapper<StudentProfile> {

    /**
     * 分页查询
     */
    List<StudentProfile> pageQuery(@Param("entity") StudentProfile entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") StudentProfile entity);

    /**
     * 列表查询（不分页）
     */
    List<StudentProfile> listQuery(@Param("entity") StudentProfile entity);

    /**
     * 根据ID查询
     */
    StudentProfile getById(@Param("id") Long id);

    /**
     * 下拉选项查询
     */
    /**
     * 下拉数据：dorm_room
     */
    @Select("select room_no as label, id as value from dorm_room")
    List<SelectVO> listDormRoom();

}
