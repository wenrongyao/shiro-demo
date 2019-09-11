package com.honor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created by rongyaowen
 * on 2019/9/4.
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.honor.mapper"})
public class ShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class, args);
    }
}
