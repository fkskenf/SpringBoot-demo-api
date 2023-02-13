package com.blog.demo.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = "sys", name = "board") // 테이블과 mapping
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 파라미터가 없는 기본 생성자를 생성
@AllArgsConstructor(access = AccessLevel.PRIVATE) //  어노테이션은 모든 필드 값을 파라미터로 받는 생성자
// @RequiredArgsConstructor // final이나 @NonNull인 필드 값만 파라미터로 받는 생성자
public class Board {

    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 데이터베이스에 위임 (IDENTITY 전략에서는 바로 INSERT)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE) // DB에 있는 시퀸스 값을 가져와서 넣음 (SEQUENCE 전략은 한 번에 모아서 INSERT 가능)
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
