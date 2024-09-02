package org.tinytrail.project;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.tinytrail.project.dao.mapper")
public class TinyTrailApplication {
    public static void main(String[] args) {
        SpringApplication.run(TinyTrailApplication.class, args);
    }
}
