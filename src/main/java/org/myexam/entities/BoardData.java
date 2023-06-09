package org.myexam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity @Data @Builder
@AllArgsConstructor @NoArgsConstructor
@Table(indexes = {
        @Index(name = "idx_boarddata_category", columnList = "category DESC"),
        @Index(name = "idx_boarddata_createAt", columnList = "createdAt DESC")
})
public class BoardData extends BaseEntity {

    @Id @GeneratedValue
    private Long id;    // 게시글 번호

    @ManyToOne(fetch = FetchType.LAZY)  // 매번 가져오지 않고 필요할 때만 조회 할 수 있게함
    @JoinColumn(name = "bId")
    private Board board;        // private String bId -> private Board board 변경함.(주입이 안되는 예외를 해결)

    @Column(length = 65, nullable = false)
    private String gid = UUID.randomUUID().toString();  // 그룹 Id 파일 올릴때 필요함 (없으면 기본으로 랜덤생성)

    @Column(length = 40, nullable = false)
    private String poster;      // 작성자

    @Column(length = 65)
    private String guestPw;     // 비회원 비밀번호

    @Column(length = 60)
    private String category;    // 게시판 분류

    @Column(nullable = false)
    private String subject;     // 제목

    @Lob
    @Column(nullable = false)
    private String content;     // 내용

    private int hit;            // 조회수

    @Column(length = 125)
    private String ua;          // User-Agent : 브라우저 정보

    @Column(length = 20)
    private String ip;          // 작성자 IP 주소

    private int commentCnt;     // 댓글 수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userNo")
    private Member member;      // 작성 회원
}
