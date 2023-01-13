package com.blog.demo.repository;

import com.blog.demo.domain.board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface boardRepository extends JpaRepository<board, Long> {
}
