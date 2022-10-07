package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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

    @GetMapping("/callback")
    public String callbackGet(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request) {
        log.info("============GET request start============");
        for (Map.Entry<String, List<String>> entry : httpHeaders.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            for (String value : values) {
                log.info("header key: {}, value: {}", key, value);
            }
        }
        log.info("requestURI(): {}", request.getRequestURI());
        log.info("============GET request end============");

        return "GET ok";
    }

    @PostMapping("/callback")
    public String callbackPost(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request) {
        log.info("============POST request start============");
        for (Map.Entry<String, List<String>> entry : httpHeaders.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            for (String value : values) {
                log.info("header key: {}, value: {}", key, value);
            }
        }
        log.info("requestURI(): {}", request.getRequestURI());
        log.info("body(): {}", extractBodyFromHttpServletRequest(request));
        log.info("============POST request end============");

        return "POST ok";
    }

    private String extractBodyFromHttpServletRequest(HttpServletRequest request) {
        Scanner s = null;
        try {
            s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
        } catch (IOException e) {
            log.error("{}", e.getStackTrace());
        }
        return s.hasNext() ? s.next() : "";
    }
}
