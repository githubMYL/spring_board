package org.myexam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class BoardData extends BaseEntity {

    @Id @GeneratedValue
    private Long id;    // 게시글 번호

    @ManyToOne(fetch = FetchType.LAZY)  // 매번 가져오지 않고 필요할 때만 조회 할 수 있게함
    @JoinColumn(name = "bId")
    private Board board;

    private String gid;         // 그룹 Id 파일 올릴때 필요함

    private String poster;      // 작성자

    private String guestPw;     // 비회원 비밀번호

    private String category;    // 게시판 분류

    private String subject;     // 제목

    private String content;     // 내용

    private int hit;            // 조회수

    private String ua;          // User-Agent : 브라우저 정보

    private String ip;          // 작성자 IP 주소

    private int commentCnt;     // 댓글 수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userNo")
    private Member member;      // 작성 회원
}
