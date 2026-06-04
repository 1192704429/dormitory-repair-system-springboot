package com.studentlife.repair.basic.service.impl;

import com.studentlife.repair.basic.mapper.EchartsMapper;
import com.studentlife.repair.basic.service.EchartsService;
import com.studentlife.repair.vo.EchartsDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EchartsServiceImpl implements EchartsService {

    @Autowired
    private EchartsMapper echartsMapper;

    /**
     * 用户角色分布统计
     */
    @Override
    public List<EchartsDataVO> countUserByRole(Integer status) {
        return echartsMapper.countUserByRole(status);
    }

    /**
     * 报修单故障类型分布
     */
    @Override
    public List<EchartsDataVO> countRepairOrderByFaultType(String startTime, String endTime, Integer auditStatus) {
        return echartsMapper.countRepairOrderByFaultType(startTime, endTime, auditStatus);
    }

    /**
     * 报修单状态趋势统计
     */
    @Override
    public List<EchartsDataVO> countRepairOrderByStatusTrend(String startTime, String endTime) {
        return echartsMapper.countRepairOrderByStatusTrend(startTime, endTime);
    }

    /**
     * 楼栋宿舍类型分布
     */
    @Override
    public List<EchartsDataVO> countBuildingByRoomType(Integer status) {
        return echartsMapper.countBuildingByRoomType(status);
    }

    /**
     * 维修材料类型分布
     */
    @Override
    public List<EchartsDataVO> countMaterialByType(Integer status) {
        return echartsMapper.countMaterialByType(status);
    }

    /**
     * 材料申请状态统计
     */
    @Override
    public List<EchartsDataVO> countMaterialApplyByStatus(String startTime, String endTime) {
        return echartsMapper.countMaterialApplyByStatus(startTime, endTime);
    }

    /**
     * 报修评价分数分布
     */
    @Override
    public List<EchartsDataVO> countRepairEvaluateByScore(String startTime, String endTime) {
        return echartsMapper.countRepairEvaluateByScore(startTime, endTime);
    }

    /**
     * 系统通知类型与级别分布
     */
    @Override
    public List<EchartsDataVO> countNotifyByTypeAndLevel(Integer level) {
        return echartsMapper.countNotifyByTypeAndLevel(level);
    }

    /**
     * 学生性别分布统计
     */
    @Override
    public List<EchartsDataVO> countStudentByGender(Integer status) {
        return echartsMapper.countStudentByGender(status);
    }

    /**
     * 各楼栋报修单数量统计
     */
    @Override
    public List<EchartsDataVO> countRepairOrderByBuilding(String startTime, String endTime, Integer orderStatus) {
        return echartsMapper.countRepairOrderByBuilding(startTime, endTime, orderStatus);
    }

}
