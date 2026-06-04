package com.studentlife.repair.module.material_apply.controller;

import cn.hutool.core.util.StrUtil;
import com.studentlife.repair.common.Result;
import com.studentlife.repair.module.material_apply.entity.MaterialApply;
import com.studentlife.repair.module.material_apply.service.MaterialApplyService;
import com.studentlife.repair.util.JwtUtil;
import com.studentlife.repair.vo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import com.studentlife.repair.vo.SelectVO;

/**
 * @author wuming
 * @description: 维修材料申请Controller
 */
@RestController
@RequestMapping("/material_apply")
public class MaterialApplyController {

    @Autowired
    private MaterialApplyService materialApplyService;

    @Autowired
    private JwtUtil<LoginUser> jwtUtil;

    /**
     * 分页查询维修材料申请列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) MaterialApply entity) {
        if (entity == null) {
            entity = new MaterialApply();
        }
        return Result.success(materialApplyService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有维修材料申请列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) MaterialApply entity) {
        if (entity == null) {
            entity = new MaterialApply();
        }
        List<MaterialApply> list = materialApplyService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询维修材料申请
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        MaterialApply materialApply = materialApplyService.getById(id);
        if (materialApply == null) {
            return Result.error("维修材料申请不存在");
        }
        return Result.success(materialApply);
    }

    /**
     * 新增维修材料申请
     */
    @PostMapping
    public Result save(@RequestBody MaterialApply materialApply, HttpServletRequest request) {
        // 从token获取维修人员ID
        String authorization = request.getHeader("Authorization");
        if (StrUtil.isNotEmpty(authorization)) {
            try {
                String token = authorization.startsWith("Bearer ") ? authorization.substring(7) : authorization;
                if (jwtUtil.verifyToken(token)) {
                    LoginUser loginUser = jwtUtil.parseToken(token, LoginUser.class);
                    if ("repair_worker".equals(loginUser.getRole()) && materialApply.getWorkerUserId() == null) {
                        materialApply.setWorkerUserId(loginUser.getId());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        // 自动生成申请单号
        if (StrUtil.isEmpty(materialApply.getApplyNo())) {
            materialApply.setApplyNo("MAT" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                + String.format("%04d", new Random().nextInt(10000)));
        }
        
        materialApply.setApplyStatus(0); // 待审核
        materialApply.setCreateTime(LocalDateTime.now());
        boolean success = materialApplyService.save(materialApply);
        if (success) {
            return Result.success("新增成功", materialApply);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新维修材料申请
     */
    @PutMapping
    public Result update(@RequestBody MaterialApply materialApply) {
        // 如果审核状态不为空且不为待审核状态（通过或驳回），则自动填充审核时间
        // 使用服务器时间确保时间准确
        if (materialApply.getApplyStatus() != null && materialApply.getApplyStatus() != 0) {
            materialApply.setAuditTime(LocalDateTime.now());
        }
        boolean success = materialApplyService.updateById(materialApply);
        if (success) {
            return Result.success("更新成功", materialApply);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除维修材料申请
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = materialApplyService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除维修材料申请
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = materialApplyService.removeByIds(ids);
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
        List<SelectVO> list = materialApplyService.listSysUser();
        return Result.success(list);
    }

    /**
     * 下拉数据接口：repair_order
     */
    @GetMapping("/select/listRepairOrder")
    public Result listRepairOrder() {
        List<SelectVO> list = materialApplyService.listRepairOrder();
        return Result.success(list);
    }

    /**
     * 下拉数据接口：material
     */
    @GetMapping("/select/listMaterial")
    public Result listMaterial() {
        List<SelectVO> list = materialApplyService.listMaterial();
        return Result.success(list);
    }

}
