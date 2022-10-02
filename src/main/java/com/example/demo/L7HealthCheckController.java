package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class L7HealthCheckController {

    @GetMapping("/l7check")
    public String l7HealthCheck() {
        log.info("l7check ok");
        String password = "1234"; // test sonarqube detect this line
        log.info("password is {}", password);
        return "ok";
    }
}
