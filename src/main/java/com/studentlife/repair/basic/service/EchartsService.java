package com.studentlife.repair.basic.service;

import com.studentlife.repair.vo.EchartsDataVO;

import java.util.List;

public interface EchartsService {

    /**
     * 用户角色分布统计
     */
    List<EchartsDataVO> countUserByRole(Integer status);

    /**
     * 报修单故障类型分布
     */
    List<EchartsDataVO> countRepairOrderByFaultType(String startTime, String endTime, Integer auditStatus);

    /**
     * 报修单状态趋势统计
     */
    List<EchartsDataVO> countRepairOrderByStatusTrend(String startTime, String endTime);

    /**
     * 楼栋宿舍类型分布
     */
    List<EchartsDataVO> countBuildingByRoomType(Integer status);

    /**
     * 维修材料类型分布
     */
    List<EchartsDataVO> countMaterialByType(Integer status);

    /**
     * 材料申请状态统计
     */
    List<EchartsDataVO> countMaterialApplyByStatus(String startTime, String endTime);

    /**
     * 报修评价分数分布
     */
    List<EchartsDataVO> countRepairEvaluateByScore(String startTime, String endTime);

    /**
     * 系统通知类型与级别分布
     */
    List<EchartsDataVO> countNotifyByTypeAndLevel(Integer level);

    /**
     * 学生性别分布统计
     */
    List<EchartsDataVO> countStudentByGender(Integer status);

    /**
     * 各楼栋报修单数量统计
     */
    List<EchartsDataVO> countRepairOrderByBuilding(String startTime, String endTime, Integer orderStatus);

}
