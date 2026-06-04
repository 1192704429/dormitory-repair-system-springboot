package com.studentlife.repair.module.student_profile.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.studentlife.repair.common.Result;
import com.studentlife.repair.module.student_profile.entity.StudentProfile;
import com.studentlife.repair.module.student_profile.service.StudentProfileService;
import com.studentlife.repair.util.JwtUtil;
import com.studentlife.repair.vo.LoginVO;
import com.studentlife.repair.vo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.studentlife.repair.vo.SelectVO;

/**
 * @author wuming
 * @description: 学生档案Controller
 */
@RestController
@RequestMapping("/student_profile")
public class StudentProfileController {

    static final Map<String, Long> TOKEN_CACHE_MAP = new HashMap<>();

    @Autowired
    private StudentProfileService studentProfileService;

    @Autowired
    private JwtUtil<LoginUser> jwtUtil;

    @Value("${spring.application.name}")
    private String secret;

    /**
     * 分页查询学生档案列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) StudentProfile entity) {
        if (entity == null) {
            entity = new StudentProfile();
        }
        return Result.success(studentProfileService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有学生档案列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) StudentProfile entity) {
        if (entity == null) {
            entity = new StudentProfile();
        }
        List<StudentProfile> list = studentProfileService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询学生档案
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        StudentProfile studentProfile = studentProfileService.getById(id);
        if (studentProfile == null) {
            return Result.error("学生档案不存在");
        }
        return Result.success(studentProfile);
    }

    /**
     * 新增学生档案
     */
    @PostMapping
    public Result save(@RequestBody StudentProfile studentProfile) {
        studentProfile.setCreateTime(LocalDateTime.now());
        boolean success = studentProfileService.save(studentProfile);
        if (success) {
            return Result.success("新增成功", studentProfile);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新学生档案
     */
    @PutMapping
    public Result update(@RequestBody StudentProfile studentProfile) {
        boolean success = studentProfileService.updateById(studentProfile);
        if (success) {
            return Result.success("更新成功", studentProfile);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除学生档案
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = studentProfileService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除学生档案
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = studentProfileService.removeByIds(ids);
        if (success) {
            return Result.success("批量删除成功");
        }
        return Result.error("批量删除失败");
    }

    /**
     * 下拉数据接口：dorm_room
     */
    @GetMapping("/select/listDormRoom")
    public Result listDormRoom() {
        List<SelectVO> list = studentProfileService.listDormRoom();
        return Result.success(list);
    }

    /**
     * 学生登录接口
     *
     * @param loginVO 登录信息
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginVO loginVO) {
        // 参数校验
        if (StrUtil.isEmpty(loginVO.getUsername())) {
            return Result.error("登录用户名不能为空");
        }
        if (StrUtil.isEmpty(loginVO.getPassword())) {
            return Result.error("登录密码不能为空");
        }

        // 查询学生信息
        LambdaQueryWrapper<StudentProfile> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StudentProfile::getUsername, loginVO.getUsername());
        StudentProfile studentProfile = studentProfileService.getOne(queryWrapper);

        // 校验学生信息
        if (studentProfile == null) {
            return Result.error("学生账号不存在");
        }

        // 校验学生状态（如果status字段表示状态，0或null表示禁用）
        if (studentProfile.getStatus() != null && studentProfile.getStatus() == 0) {
            return Result.error("账号已被禁用");
        }

        // 校验密码
        if (!loginVO.getPassword().equals(studentProfile.getPassword())) {
            return Result.error("密码错误");
        }

        // 获取完整的学生信息（包含宿舍和楼栋信息）
        StudentProfile fullStudentProfile = studentProfileService.getById(studentProfile.getId());

        // 组装登录用户信息
        LoginUser loginUser = new LoginUser();
        loginUser.setId(studentProfile.getId());
        loginUser.setName(studentProfile.getRealName());
        loginUser.setAvatar(StrUtil.isNotEmpty(studentProfile.getAvatar()) 
            ? studentProfile.getAvatar() 
            : "https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2022-05-20/1ef4592a-ff86-424a-845a-260a58a550a4_logo.jpg");
        loginUser.setIntroduction("学生用户");
        loginUser.setRole("student");

        // 生成JWT token
        String token = jwtUtil.createToken(loginUser);
        
        // 设置 token 到缓存中
        TOKEN_CACHE_MAP.put(token, System.currentTimeMillis());

        // 组装返回数据（包含token和用户信息）
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        
        // 用户信息
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", studentProfile.getId());
        userInfo.put("username", studentProfile.getUsername());
        userInfo.put("realName", studentProfile.getRealName());
        userInfo.put("avatar", loginUser.getAvatar());
        userInfo.put("studentNo", studentProfile.getStudentNo());
        userInfo.put("college", studentProfile.getCollege());
        userInfo.put("major", studentProfile.getMajor());
        userInfo.put("grade", studentProfile.getGrade());
        userInfo.put("className", studentProfile.getClassName());
        userInfo.put("roomId", studentProfile.getRoomId());
        // 添加宿舍和楼栋信息
        if (fullStudentProfile != null) {
            userInfo.put("roomNo", fullStudentProfile.getRoomNo());
            userInfo.put("buildingName", fullStudentProfile.getBuildingName());
            // 如果宿舍信息存在，也添加buildingId
            if (fullStudentProfile.getRoomId() != null) {
                // 可以通过roomId查询buildingId，但为了简化，这里先不添加
                // 如果需要buildingId，可以查询dorm_room表
            }
        }
        result.put("userInfo", userInfo);

        return Result.success(result);
    }

    /**
     * SHA-256 加密方法
     *
     * @param input 原始字符串
     * @return 加密后的字符串
     */
    private String sha256(String input) {
        try {
            String newInput = secret + input;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(newInput.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
