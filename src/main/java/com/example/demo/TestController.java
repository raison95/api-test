package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class TestController {

    @PostMapping("/test")
    public ResponseEntity<String> test(@RequestParam Integer status, @RequestHeader HttpHeaders httpHeaders, @RequestBody String body) {
        log.info("============request start============");
        for (Map.Entry<String, List<String>> entry : httpHeaders.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            for (String value : values) {
                log.info("header key: {}, value: {}", key, value);
            }
        }
        log.info("body: {}", body);
        log.info("============request end============");
        log.info("httpStatus to be responded: {}", HttpStatus.valueOf(status));

        return new ResponseEntity<>(HttpStatus.valueOf(status));
    }
}
