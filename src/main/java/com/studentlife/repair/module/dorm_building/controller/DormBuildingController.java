package com.studentlife.repair.module.dorm_building.controller;

import com.studentlife.repair.common.Result;
import com.studentlife.repair.module.dorm_building.entity.DormBuilding;
import com.studentlife.repair.module.dorm_building.service.DormBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import com.studentlife.repair.vo.SelectVO;

/**
 * @author wuming
 * @description: 楼栋信息Controller
 */
@RestController
@RequestMapping("/dorm_building")
public class DormBuildingController {

    @Autowired
    private DormBuildingService dormBuildingService;

    /**
     * 分页查询楼栋信息列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) DormBuilding entity) {
        if (entity == null) {
            entity = new DormBuilding();
        }
        return Result.success(dormBuildingService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有楼栋信息列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) DormBuilding entity) {
        if (entity == null) {
            entity = new DormBuilding();
        }
        List<DormBuilding> list = dormBuildingService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询楼栋信息
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        DormBuilding dormBuilding = dormBuildingService.getById(id);
        if (dormBuilding == null) {
            return Result.error("楼栋信息不存在");
        }
        return Result.success(dormBuilding);
    }

    /**
     * 新增楼栋信息
     */
    @PostMapping
    public Result save(@RequestBody DormBuilding dormBuilding) {
        dormBuilding.setCreateTime(LocalDateTime.now());
        boolean success = dormBuildingService.save(dormBuilding);
        if (success) {
            return Result.success("新增成功", dormBuilding);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新楼栋信息
     */
    @PutMapping
    public Result update(@RequestBody DormBuilding dormBuilding) {
        boolean success = dormBuildingService.updateById(dormBuilding);
        if (success) {
            return Result.success("更新成功", dormBuilding);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除楼栋信息
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = dormBuildingService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除楼栋信息
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = dormBuildingService.removeByIds(ids);
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
        List<SelectVO> list = dormBuildingService.listSysUser();
        return Result.success(list);
    }

}
