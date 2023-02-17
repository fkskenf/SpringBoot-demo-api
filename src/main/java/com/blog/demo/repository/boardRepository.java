package com.blog.demo.repository;

import com.blog.demo.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface boardRepository extends JpaRepository<Board, Integer> {
    Optional<Board> findByCodeAndTitle(int code, String title);

    List<Board> findByCodeOrTitle(int code, String title);
}
