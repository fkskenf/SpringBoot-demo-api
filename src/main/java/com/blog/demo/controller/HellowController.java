package com.blog.demo.controller;

import com.blog.demo.domain.board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.demo.repository.boardQueryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@RequiredArgsConstructor
@RestController
public class HellowController {

    private final Logger logger = LoggerFactory.getLogger("HellowController의 로그");

    private final boardQueryRepository boardQueryRepository;

    @GetMapping("/insert")
    public ResponseEntity<?> get(@Value("${spring.config.activate.on-profile}") String test) {

        logger.debug(test);
        logger.debug(System.getProperty("spring.jpa.database"));
        logger.debug(System.getProperty("log.config.path"));
        logger.debug("DEBUG");
        logger.info("INFO");
        board board2 = board.builder()
                .title("title")
                .content("content")
                .writer("")
                .build();
        boardQueryRepository.save(board2);
        return ResponseEntity.ok().body("");
    }
}