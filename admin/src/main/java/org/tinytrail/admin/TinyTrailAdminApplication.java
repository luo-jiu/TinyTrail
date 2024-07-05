package org.tinytrail.admin;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.tinytrail.admin.dao.mapper")
public class TinyTrailAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(TinyTrailAdminApplication.class, args);
    }
}
