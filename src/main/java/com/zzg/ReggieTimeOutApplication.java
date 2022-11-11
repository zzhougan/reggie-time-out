package com.zzg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@SpringBootApplication
@ServletComponentScan
public class ReggieTimeOutApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReggieTimeOutApplication.class, args);
    }

}
