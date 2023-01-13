package com.blog.demo.controller;

import com.blog.demo.domain.board;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.demo.repository.boardQueryRepository;

@RequiredArgsConstructor
@RestController
//@RequestMapping(value = "/finsvc/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class HellowController {
    private final boardQueryRepository boardQueryRepository;

    @GetMapping("/insert1")
    public ResponseEntity<?> get() {

        System.out.println("api test");
        board board2 = board.builder()
                .code(1)
                .title("title")
                .content("content")
                .writer("")
                .build();
        boardQueryRepository.save(board2);
        return ResponseEntity.ok().body("");
    }


}