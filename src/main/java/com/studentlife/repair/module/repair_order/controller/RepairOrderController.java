package com.studentlife.repair.module.repair_order.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.studentlife.repair.common.Result;
import com.studentlife.repair.module.repair_order.entity.RepairOrder;
import com.studentlife.repair.module.repair_order.service.RepairOrderService;
import com.studentlife.repair.module.student_profile.entity.StudentProfile;
import com.studentlife.repair.module.student_profile.service.StudentProfileService;
import com.studentlife.repair.module.dorm_room.entity.DormRoom;
import com.studentlife.repair.module.dorm_room.service.DormRoomService;
import com.studentlife.repair.module.sys_user.entity.SysUser;
import com.studentlife.repair.module.sys_user.service.SysUserService;
import com.studentlife.repair.util.JwtUtil;
import com.studentlife.repair.vo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import com.studentlife.repair.vo.SelectVO;

/**
 * @author wuming
 * @description: 报修单Controller
 */
@RestController
@RequestMapping("/repair_order")
public class RepairOrderController {

    @Autowired
    private RepairOrderService repairOrderService;

    @Autowired
    private JwtUtil<LoginUser> jwtUtil;

    @Autowired
    private StudentProfileService studentProfileService;

    @Autowired
    private DormRoomService dormRoomService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 分页查询报修单列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) RepairOrder entity,
                           HttpServletRequest request) {
        if (entity == null) {
            entity = new RepairOrder();
        }
        // 获取当前登录用户信息
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
                    // 将用户ID和角色传递给Service，用于数据过滤
                    entity.setCurrentUserId(loginUser.getId());
                    entity.setCurrentUserRole(loginUser.getRole());
                }
            } catch (Exception e) {
                // token解析失败，忽略
                e.printStackTrace();
            }
        }
        return Result.success(repairOrderService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有报修单列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) RepairOrder entity, HttpServletRequest request) {
        if (entity == null) {
            entity = new RepairOrder();
        }
        // 维修人员只能看到分配给自己的工单
        String authorization = request.getHeader("Authorization");
        if (StrUtil.isNotEmpty(authorization)) {
            try {
                String token = authorization.startsWith("Bearer ") ? authorization.substring(7) : authorization;
                if (jwtUtil.verifyToken(token)) {
                    LoginUser loginUser = jwtUtil.parseToken(token, LoginUser.class);
                    if ("repair_worker".equals(loginUser.getRole())) {
                        entity.setCurrentUserId(loginUser.getId());
                        entity.setCurrentUserRole("repair_worker");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<RepairOrder> list = repairOrderService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询报修单
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id, HttpServletRequest request) {
        RepairOrder repairOrder = repairOrderService.getById(id);
        if (repairOrder == null) {
            return Result.error("报修单不存在");
        }
        
        // 权限验证：学生只能查看自己提交的报修单
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
                    // 如果是学生角色，验证是否为该学生提交的报修单
                    if ("student".equals(loginUser.getRole())) {
                        if (repairOrder.getStudentUserId() == null || 
                            !repairOrder.getStudentUserId().equals(loginUser.getId())) {
                            return Result.error("无权查看此报修单");
                        }
                    }
                }
            } catch (Exception e) {
                // token解析失败，如果是学生角色，拒绝访问
                e.printStackTrace();
            }
        }
        
        return Result.success(repairOrder);
    }

    /**
     * 新增报修单
     */
    @PostMapping
    public Result save(@RequestBody RepairOrder repairOrder, HttpServletRequest request) {
        Long studentUserId = null;
        
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
                    if ("student".equals(loginUser.getRole()) && repairOrder.getStudentUserId() == null) {
                        repairOrder.setStudentUserId(loginUser.getId());
                        studentUserId = loginUser.getId();
                    } else if (repairOrder.getStudentUserId() != null) {
                        studentUserId = repairOrder.getStudentUserId();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (repairOrder.getStudentUserId() != null) {
            studentUserId = repairOrder.getStudentUserId();
        }
        
        // 如果buildingId或roomId为空，从学生档案中自动获取
        if (studentUserId != null && (repairOrder.getBuildingId() == null || repairOrder.getRoomId() == null)) {
            try {
                StudentProfile studentProfile = studentProfileService.getById(studentUserId);
                if (studentProfile != null && studentProfile.getRoomId() != null) {
                    // 如果roomId为空，使用学生的roomId
                    if (repairOrder.getRoomId() == null) {
                        repairOrder.setRoomId(studentProfile.getRoomId());
                    }
                    
                    // 如果buildingId为空，从roomId查询获取buildingId
                    if (repairOrder.getBuildingId() == null && repairOrder.getRoomId() != null) {
                        DormRoom dormRoom = dormRoomService.getById(repairOrder.getRoomId());
                        if (dormRoom != null && dormRoom.getBuildingId() != null) {
                            repairOrder.setBuildingId(dormRoom.getBuildingId());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        // 自动生成订单号（如果为空）
        if (StrUtil.isEmpty(repairOrder.getOrderNo())) {
            // 格式：REPAIR + 年月日时分秒 + 4位随机数
            String orderNo = "REPAIR" + 
                LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) +
                String.format("%04d", (int)(Math.random() * 10000));
            repairOrder.setOrderNo(orderNo);
        }
        
        // 设置默认状态
        if (repairOrder.getAuditStatus() == null) {
            repairOrder.setAuditStatus(0); // 待审核
        }
        if (repairOrder.getAssignStatus() == null) {
            repairOrder.setAssignStatus(0); // 未派工
        }
        if (repairOrder.getOrderStatus() == null) {
            repairOrder.setOrderStatus(0); // 初始状态
        }
        
        repairOrder.setCreateTime(LocalDateTime.now());
        boolean success = repairOrderService.save(repairOrder);
        if (success) {
            return Result.success("新增成功", repairOrder);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新报修单
     */
    @PutMapping
    public Result update(@RequestBody RepairOrder repairOrder) {
        // 如果审核状态变为通过（1），自动派工并更新工单状态
        if (repairOrder.getAuditStatus() != null && repairOrder.getAuditStatus() == 1) {
            // 查询原始数据，判断审核状态是否从其他状态变为通过
            RepairOrder originalOrder = repairOrderService.getById(repairOrder.getId());
            if (originalOrder != null && originalOrder.getAuditStatus() != null && originalOrder.getAuditStatus() != 1) {
                // 审核状态从非通过变为通过，自动派工
                repairOrder.setAssignStatus(1); // 已派工
                repairOrder.setAssignTime(LocalDateTime.now()); // 派工时间
                // 如果工单状态为空或为初始状态，设置为待接收
                if (repairOrder.getOrderStatus() == null || repairOrder.getOrderStatus() == 0) {
                    repairOrder.setOrderStatus(1); // 待接收
                }
                // 随机分配一个维修人员（worker_user_id 为空时）
                if (repairOrder.getWorkerUserId() == null) {
                    LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
                    wrapper.eq(SysUser::getRoleKey, "repair_worker").eq(SysUser::getStatus, 1);
                    List<SysUser> workers = sysUserService.list(wrapper);
                    if (workers != null && !workers.isEmpty()) {
                        SysUser randomWorker = workers.get(new Random().nextInt(workers.size()));
                        repairOrder.setWorkerUserId(randomWorker.getId());
                    }
                }
            }
        }
        
        boolean success = repairOrderService.updateById(repairOrder);
        if (success) {
            return Result.success("更新成功", repairOrder);
        }
        return Result.error("更新失败");
    }

    /**
     * 学生取消报修（仅待审核状态可取消）
     */
    @PostMapping("/cancel/{id}")
    public Result cancel(@PathVariable Long id, HttpServletRequest request) {
        // 验证token并获取学生ID
        String authorization = request.getHeader("Authorization");
        if (StrUtil.isEmpty(authorization)) {
            return Result.error("请先登录");
        }
        String token = authorization.startsWith("Bearer ") ? authorization.substring(7) : authorization;
        try {
            if (!jwtUtil.verifyToken(token)) {
                return Result.error("登录已过期，请重新登录");
            }
            LoginUser loginUser = jwtUtil.parseToken(token, LoginUser.class);
            if (!"student".equals(loginUser.getRole())) {
                return Result.error("仅学生可取消报修");
            }
            
            RepairOrder order = repairOrderService.getById(id);
            if (order == null) {
                return Result.error("报修单不存在");
            }
            if (!order.getStudentUserId().equals(loginUser.getId())) {
                return Result.error("无权取消此报修单");
            }
            if (order.getAuditStatus() == null || order.getAuditStatus() != 0) {
                return Result.error("仅待审核状态的报修单可取消");
            }
            
            // 仅更新审核状态为已取消(3)
            UpdateWrapper<RepairOrder> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", id).set("audit_status", 3);
            boolean success = repairOrderService.update(updateWrapper);
            if (success) {
                return Result.success("取消成功");
            }
            return Result.error("取消失败");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("操作失败：" + e.getMessage());
        }
    }

    /**
     * 维修人员更新工单状态（接收/维修中/已完成）
     */
    @PostMapping("/updateStatus/{id}")
    public Result updateStatus(@PathVariable Long id, @RequestParam Integer orderStatus, HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (StrUtil.isEmpty(authorization)) {
            return Result.error("请先登录");
        }
        String token = authorization.startsWith("Bearer ") ? authorization.substring(7) : authorization;
        try {
            if (!jwtUtil.verifyToken(token)) {
                return Result.error("登录已过期，请重新登录");
            }
            LoginUser loginUser = jwtUtil.parseToken(token, LoginUser.class);
            if (!"repair_worker".equals(loginUser.getRole())) {
                return Result.error("仅维修人员可操作");
            }
            
            RepairOrder order = repairOrderService.getById(id);
            if (order == null) {
                return Result.error("报修单不存在");
            }
            if (order.getWorkerUserId() == null || !order.getWorkerUserId().equals(loginUser.getId())) {
                return Result.error("无权操作此报修单");
            }
            
            // 校验状态流转：1->2, 2->3, 3->4
            Integer currentStatus = order.getOrderStatus();
            if (orderStatus == 2 && (currentStatus == null || currentStatus != 1)) {
                return Result.error("仅待接收状态可点击接收");
            }
            if (orderStatus == 3 && (currentStatus == null || currentStatus != 2)) {
                return Result.error("仅已接收状态可点击维修中");
            }
            if (orderStatus == 4 && (currentStatus == null || currentStatus != 3)) {
                return Result.error("仅维修中状态可点击已完成");
            }
            
            UpdateWrapper<RepairOrder> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", id).set("order_status", orderStatus);
            if (orderStatus == 4) {
                updateWrapper.set("finish_time", LocalDateTime.now());
            }
            boolean success = repairOrderService.update(updateWrapper);
            if (success) {
                return Result.success("操作成功");
            }
            return Result.error("操作失败");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("操作失败：" + e.getMessage());
        }
    }

    /**
     * 根据ID删除报修单
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = repairOrderService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除报修单
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = repairOrderService.removeByIds(ids);
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
        List<SelectVO> list = repairOrderService.listDormBuilding();
        return Result.success(list);
    }

    /**
     * 下拉数据接口：dorm_room
     */
    @GetMapping("/select/listDormRoom")
    public Result listDormRoom() {
        List<SelectVO> list = repairOrderService.listDormRoom();
        return Result.success(list);
    }

    /**
     * 下拉数据接口：sys_user
     */
    @GetMapping("/select/listSysUser")
    public Result listSysUser() {
        List<SelectVO> list = repairOrderService.listSysUser();
        return Result.success(list);
    }

}
