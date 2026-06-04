package com.studentlife.repair;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wuming
 * @description: 宿舍报修系统服务端启动入口
 */
@SpringBootApplication
@Slf4j
@MapperScan("com.studentlife.repair.**.mapper")
@EnableScheduling
public class DormMaintenanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DormMaintenanceApplication.class, args);
        log.info("JAVA后端服务启动成功！！！！！！");
    }

}
