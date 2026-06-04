package com.studentlife.repair.module.sys_notify.controller;

import com.studentlife.repair.common.Result;
import com.studentlife.repair.module.sys_notify.entity.SysNotify;
import com.studentlife.repair.module.sys_notify.service.SysNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author wuming
 * @description: 系统通知Controller
 */
@RestController
@RequestMapping("/sys_notify")
public class SysNotifyController {

    @Autowired
    private SysNotifyService sysNotifyService;

    /**
     * 分页查询系统通知列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) SysNotify entity) {
        if (entity == null) {
            entity = new SysNotify();
        }
        return Result.success(sysNotifyService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有系统通知列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) SysNotify entity) {
        if (entity == null) {
            entity = new SysNotify();
        }
        List<SysNotify> list = sysNotifyService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询系统通知
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        SysNotify sysNotify = sysNotifyService.getById(id);
        if (sysNotify == null) {
            return Result.error("系统通知不存在");
        }
        return Result.success(sysNotify);
    }

    /**
     * 新增系统通知
     */
    @PostMapping
    public Result save(@RequestBody SysNotify sysNotify) {
        sysNotify.setCreateTime(LocalDateTime.now());
        boolean success = sysNotifyService.save(sysNotify);
        if (success) {
            return Result.success("新增成功", sysNotify);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新系统通知
     */
    @PutMapping
    public Result update(@RequestBody SysNotify sysNotify) {
        boolean success = sysNotifyService.updateById(sysNotify);
        if (success) {
            return Result.success("更新成功", sysNotify);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除系统通知
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = sysNotifyService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除系统通知
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = sysNotifyService.removeByIds(ids);
        if (success) {
            return Result.success("批量删除成功");
        }
        return Result.error("批量删除失败");
    }

}
