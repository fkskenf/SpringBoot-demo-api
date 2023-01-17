package com.blog.demo.controller.test;

import com.blog.demo.domain.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.blog.demo.repository.boardQueryRepository;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class JpaController {
    private final boardQueryRepository boardQueryRepository;

    @GetMapping("/jpa/save")
    public ResponseEntity<?> saveJpa() {
        Board board2 = Board.builder() // pk insert는 Entity에서 @GeneratedValue 적용
                .title("title")
                .content("content")
                .writer("")
                .build();
        boardQueryRepository.save(board2);

        return ResponseEntity.ok().body("");
    }

    @GetMapping("/jpa/findById")
    public ResponseEntity<?> findByIdJpa(@RequestBody Board board) {
        int code = board.getCode();
        Board resultBoard = boardQueryRepository.findById(code).orElse(null);

        return ResponseEntity.ok().body(resultBoard);
    }

    @DeleteMapping("/jpa/delete")
    public ResponseEntity<?> deleteJpa(@RequestBody Board board) {
        boardQueryRepository.delete(board);

        return ResponseEntity.ok().body(board);
    }
}