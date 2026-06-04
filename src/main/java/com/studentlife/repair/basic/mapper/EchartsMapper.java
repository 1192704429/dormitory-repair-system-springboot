package com.studentlife.repair.basic.mapper;

import com.studentlife.repair.vo.EchartsDataVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EchartsMapper {

    /**
     * 用户角色分布统计
     */
    List<EchartsDataVO> countUserByRole(@Param("status") Integer status);

    /**
     * 报修单故障类型分布
     */
    List<EchartsDataVO> countRepairOrderByFaultType(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("auditStatus") Integer auditStatus);

    /**
     * 报修单状态趋势统计
     */
    List<EchartsDataVO> countRepairOrderByStatusTrend(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 楼栋宿舍类型分布
     */
    List<EchartsDataVO> countBuildingByRoomType(@Param("status") Integer status);

    /**
     * 维修材料类型分布
     */
    List<EchartsDataVO> countMaterialByType(@Param("status") Integer status);

    /**
     * 材料申请状态统计
     */
    List<EchartsDataVO> countMaterialApplyByStatus(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 报修评价分数分布
     */
    List<EchartsDataVO> countRepairEvaluateByScore(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 系统通知类型与级别分布
     */
    List<EchartsDataVO> countNotifyByTypeAndLevel(@Param("level") Integer level);

    /**
     * 学生性别分布统计
     */
    List<EchartsDataVO> countStudentByGender(@Param("status") Integer status);

    /**
     * 各楼栋报修单数量统计
     */
    List<EchartsDataVO> countRepairOrderByBuilding(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("orderStatus") Integer orderStatus);

}
