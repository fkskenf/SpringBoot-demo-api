package com.blog.demo.repository;

import com.blog.demo.domain.board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class boardQueryRepository {

    private final boardRepository boardRepository;
    public board save(board board) {
        return boardRepository.save(board);
    }
}
