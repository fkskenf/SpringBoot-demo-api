package com.blog.demo.controller.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class LogController {

    private final Logger logger = LoggerFactory.getLogger("LogController의 로그");

    @GetMapping("/log")
    public ResponseEntity<?> getLog() {
        // @Slf4j의 LoggerFactory 사용
        logger.debug("DEBUG");
        logger.info("INFO");

        // @Slf4j 사용
        log.debug("DEBUG");
        log.info("INFO");
        return ResponseEntity.ok().body("");
    }
}

