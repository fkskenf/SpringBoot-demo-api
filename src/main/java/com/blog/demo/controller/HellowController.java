package com.blog.demo.controller;

import com.blog.demo.domain.board;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.demo.repository.boardQueryRepository;

@RequiredArgsConstructor
@RestController
public class HellowController {
    private final boardQueryRepository boardQueryRepository;

    @GetMapping("/insert")
    public ResponseEntity<?> get() {
        board board2 = board.builder()
                .title("title")
                .content("content")
                .writer("")
                .build();
        boardQueryRepository.save(board2);
        return ResponseEntity.ok().body("");
    }
}