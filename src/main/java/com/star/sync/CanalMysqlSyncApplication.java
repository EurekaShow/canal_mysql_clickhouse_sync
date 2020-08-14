package com.star.sync;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author eureka
 * @version 1.0
 * @since 2020-08-14
 */
@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
@MapperScan("com.star.sync.dao")
public class CanalMysqlSyncApplication {
    public static void main(String[] args) {
        SpringApplication.run(CanalMysqlSyncApplication.class, args);
    }
}