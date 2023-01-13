package com.blog.demo.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = "sys", name = "board")
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Entity별 id use
    @Column(name = "code", updatable = false, unique = true, nullable = false)
    private Integer code;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    private LocalDateTime reg_datetime;

    @Column(nullable = false)
    private String boardcol;
}
