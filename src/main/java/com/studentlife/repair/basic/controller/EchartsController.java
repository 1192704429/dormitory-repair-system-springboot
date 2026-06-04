package com.studentlife.repair.basic.controller;

import com.studentlife.repair.basic.service.EchartsService;
import com.studentlife.repair.vo.EchartsDataVO;
import com.studentlife.repair.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 图表可视化接口
 */
@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Autowired
    private EchartsService echartsService;

    /**
     * 用户角色分布统计
     */
    @PostMapping("/countUserByRole")
    public Result countUserByRole(@RequestBody Map<String, Object> params) {
        // 从 RequestBody 中提取参数
        Integer status = Optional.ofNullable(params.get("status"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .map(Integer::valueOf)
                .orElse(null);
        List<EchartsDataVO> data = echartsService.countUserByRole(status);
        return Result.success(data);
    }

    /**
     * 报修单故障类型分布
     */
    @PostMapping("/countRepairOrderByFaultType")
    public Result countRepairOrderByFaultType(@RequestBody Map<String, Object> params) {
        // 从 RequestBody 中提取参数
        String startTime = Optional.ofNullable(params.get("startTime"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        String endTime = Optional.ofNullable(params.get("endTime"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        Integer auditStatus = Optional.ofNullable(params.get("auditStatus"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .map(Integer::valueOf)
                .orElse(null);
        List<EchartsDataVO> data = echartsService.countRepairOrderByFaultType(startTime, endTime, auditStatus);
        return Result.success(data);
    }

    /**
     * 报修单状态趋势统计
     */
    @PostMapping("/countRepairOrderByStatusTrend")
    public Result countRepairOrderByStatusTrend(@RequestBody Map<String, Object> params) {
        // 从 RequestBody 中提取参数
        String startTime = Optional.ofNullable(params.get("startTime"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        String endTime = Optional.ofNullable(params.get("endTime"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        List<EchartsDataVO> data = echartsService.countRepairOrderByStatusTrend(startTime, endTime);
        return Result.success(data);
    }

    /**
     * 楼栋宿舍类型分布
     */
    @PostMapping("/countBuildingByRoomType")
    public Result countBuildingByRoomType(@RequestBody Map<String, Object> params) {
        // 从 RequestBody 中提取参数
        Integer status = Optional.ofNullable(params.get("status"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .map(Integer::valueOf)
                .orElse(null);
        List<EchartsDataVO> data = echartsService.countBuildingByRoomType(status);
        return Result.success(data);
    }

    /**
     * 维修材料类型分布
     */
    @PostMapping("/countMaterialByType")
    public Result countMaterialByType(@RequestBody Map<String, Object> params) {
        // 从 RequestBody 中提取参数
        Integer status = Optional.ofNullable(params.get("status"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .map(Integer::valueOf)
                .orElse(null);
        List<EchartsDataVO> data = echartsService.countMaterialByType(status);
        return Result.success(data);
    }

    /**
     * 材料申请状态统计
     */
    @PostMapping("/countMaterialApplyByStatus")
    public Result countMaterialApplyByStatus(@RequestBody Map<String, Object> params) {
        // 从 RequestBody 中提取参数
        String startTime = Optional.ofNullable(params.get("startTime"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        String endTime = Optional.ofNullable(params.get("endTime"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        List<EchartsDataVO> data = echartsService.countMaterialApplyByStatus(startTime, endTime);
        return Result.success(data);
    }

    /**
     * 报修评价分数分布
     */
    @PostMapping("/countRepairEvaluateByScore")
    public Result countRepairEvaluateByScore(@RequestBody Map<String, Object> params) {
        // 从 RequestBody 中提取参数
        String startTime = Optional.ofNullable(params.get("startTime"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        String endTime = Optional.ofNullable(params.get("endTime"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        List<EchartsDataVO> data = echartsService.countRepairEvaluateByScore(startTime, endTime);
        return Result.success(data);
    }

    /**
     * 系统通知类型与级别分布
     */
    @PostMapping("/countNotifyByTypeAndLevel")
    public Result countNotifyByTypeAndLevel(@RequestBody Map<String, Object> params) {
        // 从 RequestBody 中提取参数
        Integer level = Optional.ofNullable(params.get("level"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .map(Integer::valueOf)
                .orElse(null);
        List<EchartsDataVO> data = echartsService.countNotifyByTypeAndLevel(level);
        return Result.success(data);
    }

    /**
     * 学生性别分布统计
     */
    @PostMapping("/countStudentByGender")
    public Result countStudentByGender(@RequestBody Map<String, Object> params) {
        // 从 RequestBody 中提取参数
        Integer status = Optional.ofNullable(params.get("status"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .map(Integer::valueOf)
                .orElse(null);
        List<EchartsDataVO> data = echartsService.countStudentByGender(status);
        return Result.success(data);
    }

    /**
     * 各楼栋报修单数量统计
     */
    @PostMapping("/countRepairOrderByBuilding")
    public Result countRepairOrderByBuilding(@RequestBody Map<String, Object> params) {
        // 从 RequestBody 中提取参数
        String startTime = Optional.ofNullable(params.get("startTime"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        String endTime = Optional.ofNullable(params.get("endTime"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        Integer orderStatus = Optional.ofNullable(params.get("orderStatus"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .map(Integer::valueOf)
                .orElse(null);
        List<EchartsDataVO> data = echartsService.countRepairOrderByBuilding(startTime, endTime, orderStatus);
        return Result.success(data);
    }

}
