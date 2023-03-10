package com.blog.demo.repository;

import com.blog.demo.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class boardQueryRepository {

    private final boardRepository boardRepository;

    public Board save(Board board) {
        return boardRepository.save(board);
    }

    public Optional<Board> findById(int code) {
        return boardRepository.findById(code);
    }

    public void delete(Board board) {
        boardRepository.delete(board);
    }

    public Optional<Board> findByCodeAndTitle(int code, String title) {
        return boardRepository.findByCodeAndTitle(code, title);
    }

    public List<Board> findByCodeOrTitle(int code, String title) {
        return boardRepository.findByCodeOrTitle(code, title);
    }
}
