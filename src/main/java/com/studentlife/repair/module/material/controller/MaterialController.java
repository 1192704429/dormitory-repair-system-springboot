package com.studentlife.repair.module.material.controller;

import com.studentlife.repair.common.Result;
import com.studentlife.repair.module.material.entity.Material;
import com.studentlife.repair.module.material.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author wuming
 * @description: 维修材料Controller
 */
@RestController
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    /**
     * 分页查询维修材料列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) Material entity) {
        if (entity == null) {
            entity = new Material();
        }
        return Result.success(materialService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有维修材料列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) Material entity) {
        if (entity == null) {
            entity = new Material();
        }
        List<Material> list = materialService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询维修材料
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        Material material = materialService.getById(id);
        if (material == null) {
            return Result.error("维修材料不存在");
        }
        return Result.success(material);
    }

    /**
     * 新增维修材料
     */
    @PostMapping
    public Result save(@RequestBody Material material) {
        material.setCreateTime(LocalDateTime.now());
        boolean success = materialService.save(material);
        if (success) {
            return Result.success("新增成功", material);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新维修材料
     */
    @PutMapping
    public Result update(@RequestBody Material material) {
        boolean success = materialService.updateById(material);
        if (success) {
            return Result.success("更新成功", material);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除维修材料
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = materialService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除维修材料
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = materialService.removeByIds(ids);
        if (success) {
            return Result.success("批量删除成功");
        }
        return Result.error("批量删除失败");
    }

}
