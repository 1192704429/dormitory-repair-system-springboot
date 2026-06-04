package com.studentlife.repair.module.repair_record.controller;

import com.studentlife.repair.common.Result;
import com.studentlife.repair.module.repair_record.entity.RepairRecord;
import com.studentlife.repair.module.repair_record.service.RepairRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import com.studentlife.repair.vo.SelectVO;

/**
 * @author wuming
 * @description: 报修记录Controller
 */
@RestController
@RequestMapping("/repair_record")
public class RepairRecordController {

    @Autowired
    private RepairRecordService repairRecordService;

    /**
     * 分页查询报修记录列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) RepairRecord entity) {
        if (entity == null) {
            entity = new RepairRecord();
        }
        return Result.success(repairRecordService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有报修记录列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) RepairRecord entity) {
        if (entity == null) {
            entity = new RepairRecord();
        }
        List<RepairRecord> list = repairRecordService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询报修记录
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        RepairRecord repairRecord = repairRecordService.getById(id);
        if (repairRecord == null) {
            return Result.error("报修记录不存在");
        }
        return Result.success(repairRecord);
    }

    /**
     * 新增报修记录
     */
    @PostMapping
    public Result save(@RequestBody RepairRecord repairRecord) {
        repairRecord.setCreateTime(LocalDateTime.now());
        boolean success = repairRecordService.save(repairRecord);
        if (success) {
            return Result.success("新增成功", repairRecord);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新报修记录
     */
    @PutMapping
    public Result update(@RequestBody RepairRecord repairRecord) {
        boolean success = repairRecordService.updateById(repairRecord);
        if (success) {
            return Result.success("更新成功", repairRecord);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除报修记录
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = repairRecordService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除报修记录
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = repairRecordService.removeByIds(ids);
        if (success) {
            return Result.success("批量删除成功");
        }
        return Result.error("批量删除失败");
    }

    /**
     * 下拉数据接口：sys_user
     */
    @GetMapping("/select/listSysUser")
    public Result listSysUser() {
        List<SelectVO> list = repairRecordService.listSysUser();
        return Result.success(list);
    }

    /**
     * 下拉数据接口：repair_order
     */
    @GetMapping("/select/listRepairOrder")
    public Result listRepairOrder() {
        List<SelectVO> list = repairRecordService.listRepairOrder();
        return Result.success(list);
    }

}
