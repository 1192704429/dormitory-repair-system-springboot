package com.studentlife.repair.module.dorm_room.controller;

import com.studentlife.repair.common.Result;
import com.studentlife.repair.module.dorm_room.entity.DormRoom;
import com.studentlife.repair.module.dorm_room.service.DormRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import com.studentlife.repair.vo.SelectVO;

/**
 * @author wuming
 * @description: 宿舍信息Controller
 */
@RestController
@RequestMapping("/dorm_room")
public class DormRoomController {

    @Autowired
    private DormRoomService dormRoomService;

    /**
     * 分页查询宿舍信息列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) DormRoom entity) {
        if (entity == null) {
            entity = new DormRoom();
        }
        return Result.success(dormRoomService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有宿舍信息列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) DormRoom entity) {
        if (entity == null) {
            entity = new DormRoom();
        }
        List<DormRoom> list = dormRoomService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询宿舍信息
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        DormRoom dormRoom = dormRoomService.getById(id);
        if (dormRoom == null) {
            return Result.error("宿舍信息不存在");
        }
        return Result.success(dormRoom);
    }

    /**
     * 新增宿舍信息
     */
    @PostMapping
    public Result save(@RequestBody DormRoom dormRoom) {
        dormRoom.setCreateTime(LocalDateTime.now());
        boolean success = dormRoomService.save(dormRoom);
        if (success) {
            return Result.success("新增成功", dormRoom);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新宿舍信息
     */
    @PutMapping
    public Result update(@RequestBody DormRoom dormRoom) {
        boolean success = dormRoomService.updateById(dormRoom);
        if (success) {
            return Result.success("更新成功", dormRoom);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除宿舍信息
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = dormRoomService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除宿舍信息
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = dormRoomService.removeByIds(ids);
        if (success) {
            return Result.success("批量删除成功");
        }
        return Result.error("批量删除失败");
    }

    /**
     * 下拉数据接口：dorm_building
     */
    @GetMapping("/select/listDormBuilding")
    public Result listDormBuilding() {
        List<SelectVO> list = dormRoomService.listDormBuilding();
        return Result.success(list);
    }

}
