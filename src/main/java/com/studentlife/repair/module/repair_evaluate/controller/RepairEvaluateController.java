package com.studentlife.repair.module.repair_evaluate.controller;

import cn.hutool.core.util.StrUtil;
import com.studentlife.repair.common.Result;
import com.studentlife.repair.module.repair_evaluate.entity.RepairEvaluate;
import com.studentlife.repair.module.repair_evaluate.service.RepairEvaluateService;
import com.studentlife.repair.util.JwtUtil;
import com.studentlife.repair.vo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import com.studentlife.repair.vo.SelectVO;

/**
 * @author wuming
 * @description: 报修评价Controller
 */
@RestController
@RequestMapping("/repair_evaluate")
public class RepairEvaluateController {

    @Autowired
    private RepairEvaluateService repairEvaluateService;

    @Autowired
    private JwtUtil<LoginUser> jwtUtil;

    /**
     * 分页查询报修评价列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) RepairEvaluate entity) {
        if (entity == null) {
            entity = new RepairEvaluate();
        }
        return Result.success(repairEvaluateService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有报修评价列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) RepairEvaluate entity, HttpServletRequest request) {
        if (entity == null) {
            entity = new RepairEvaluate();
        }
        // 从token中获取学生ID，确保学生只能查看自己的评价
        String authorization = request.getHeader("Authorization");
        if (StrUtil.isNotEmpty(authorization)) {
            try {
                // 去掉 "Bearer " 前缀（如果存在）
                String token = authorization;
                if (authorization.startsWith("Bearer ")) {
                    token = authorization.substring(7);
                }
                boolean verifiedToken = jwtUtil.verifyToken(token);
                if (verifiedToken) {
                    LoginUser loginUser = jwtUtil.parseToken(token, LoginUser.class);
                    // 如果是学生角色，只查询自己的评价
                    if ("student".equals(loginUser.getRole())) {
                        entity.setStudentUserId(loginUser.getId());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<RepairEvaluate> list = repairEvaluateService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询报修评价
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        RepairEvaluate repairEvaluate = repairEvaluateService.getById(id);
        if (repairEvaluate == null) {
            return Result.error("报修评价不存在");
        }
        return Result.success(repairEvaluate);
    }

    /**
     * 新增报修评价
     */
    @PostMapping
    public Result save(@RequestBody RepairEvaluate repairEvaluate, HttpServletRequest request) {
        // 从token中获取学生ID并自动设置
        String authorization = request.getHeader("Authorization");
        if (StrUtil.isNotEmpty(authorization)) {
            try {
                // 去掉 "Bearer " 前缀（如果存在）
                String token = authorization;
                if (authorization.startsWith("Bearer ")) {
                    token = authorization.substring(7);
                }
                boolean verifiedToken = jwtUtil.verifyToken(token);
                if (verifiedToken) {
                    LoginUser loginUser = jwtUtil.parseToken(token, LoginUser.class);
                    // 如果是学生角色，自动设置studentUserId
                    if ("student".equals(loginUser.getRole()) && repairEvaluate.getStudentUserId() == null) {
                        repairEvaluate.setStudentUserId(loginUser.getId());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        repairEvaluate.setCreateTime(LocalDateTime.now());
        boolean success = repairEvaluateService.save(repairEvaluate);
        if (success) {
            return Result.success("新增成功", repairEvaluate);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新报修评价
     */
    @PutMapping
    public Result update(@RequestBody RepairEvaluate repairEvaluate) {
        boolean success = repairEvaluateService.updateById(repairEvaluate);
        if (success) {
            return Result.success("更新成功", repairEvaluate);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除报修评价
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = repairEvaluateService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除报修评价
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = repairEvaluateService.removeByIds(ids);
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
        List<SelectVO> list = repairEvaluateService.listSysUser();
        return Result.success(list);
    }

    /**
     * 下拉数据接口：repair_order
     */
    @GetMapping("/select/listRepairOrder")
    public Result listRepairOrder() {
        List<SelectVO> list = repairEvaluateService.listRepairOrder();
        return Result.success(list);
    }

}
