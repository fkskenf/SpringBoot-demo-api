package com.blog.demo.controller.test;

import com.blog.demo.domain.Board;
import com.blog.demo.repository.boardQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class AsyncController {

    private final Logger logger = LoggerFactory.getLogger("LogController의 로그");

    @GetMapping("/test")
    public ResponseEntity<?> getTest() {

        return ResponseEntity.ok().body("");
    }
}
