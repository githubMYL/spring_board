package org.myexam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.myexam.commons.constants.Role;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Board extends BaseMemberEntity {

    @Id
    @Column(length = 30)
    private String bId;         // 게시판 아이디

    @Column(length = 60, nullable = false)
    private String bName;       // 게시판명

    @Column(name = "isUse")     // 오라클은 상관 없으나 Mysql 에서는 예약어라 문제발생
    private boolean use;        // 게시판 사용 여부

    private int rowsOfPage = 20;     // 1 페이지당 게시글 수

    private boolean showViewList;   // 게시슬 하단 목록 노출

    private String category;        // 게시판 분류

    // 목록 접근 권한
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Role listAccessRole = Role.All;

    // 글 보기 접근 권한
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Role ViewAccessRole = Role.All;

    // 글 쓰기 접근 권한
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Role writeAccessRole = Role.All;

    // 답글 접근 권한
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Role replyAccessRole = Role.All;

    // 댓글 접근 권한
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Role commentAccessRole = Role.All;

    // 에디터 사용 여부
    private boolean useEditor;

    //  파일 첨부 사용 여부
    private boolean useAttachFile;

    // 이미지 첨부 사용 여부
    private boolean useAttachImage;

    // 글 작성 후 이동
    @Column(length = 10, nullable = false)
    private String locationAfterWriting = "view";

    // 답글 사용 여부
    private boolean useReply;

    // 댓글 사용 여부
    private boolean useComment;

    // 게시판 스킨
    @Column(length = 20, nullable = false)
    private String skin = "default";
}
