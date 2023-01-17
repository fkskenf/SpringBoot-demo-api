package com.blog.demo.repository;

import com.blog.demo.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface boardRepository extends JpaRepository<Board, Integer> { // entity, key type
}
