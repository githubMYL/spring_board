package org.myexam.tests;

import org.hibernate.mapping.Join;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.myexam.controllers.boards.BoardForm;
import org.myexam.controllers.members.JoinForm;
import org.myexam.entities.Board;
import org.myexam.entities.Member;
import org.myexam.models.board.BoardDataSaveService;
import org.myexam.models.board.config.BoardConfigInfoService;
import org.myexam.models.board.config.BoardConfigSaveService;
import org.myexam.models.member.MemberSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@DisplayName("게시글 등록, 수정 테스트")
public class BoardSaveTests {

    @Autowired
    private BoardDataSaveService saveService;

    @Autowired
    private BoardConfigSaveService configSaveService;

    @Autowired
    private BoardConfigInfoService configInfoService;

    @Autowired
    private MemberSaveService memberSaveService;

    private Board board;

    private JoinForm joinForm;

    @BeforeEach
    @Transactional
    void init() {
        // 게시판 설정 추가
        org.myexam.controllers.admins.BoardForm boardForm = new org.myexam.controllers.admins.BoardForm();
        boardForm.setBId("freetalk");
        board.setBName("자유게시판");
        configSaveService.save(boardForm);
        board = configInfoService.get(boardForm.getBId(), true);

        // 회원 가입 추가
        joinForm = JoinForm.builder()
                .userId("user01")
                .userPw("aA!123456")
                .userPwRe("aA!123456")
                .email("test01@test.org")
                .userNm("사용자01")
                .mobile("01000000000")
                .agrees(new boolean[]{true})
                .build();
        memberSaveService.save(joinForm);
    }

    private BoardForm getGuestBoardForm() {
        return BoardForm.builder()
                .bId(board.getBId())
                .guestPw("12345678")
                .poster("비회원")
                .subject("테스트 제목!")
                .content("내용 테스트!")
                .category(board.getCategory() == null ? null : board.getCategories()[0])
                .build();
    }

    //@WithMockUser(username = "user01", password = "aA!123456") // Mock 을 이용해서 로그인한 유저로 인정하고 테스트 진행
    private BoardForm getMemberBoardForm() {
        return BoardForm.builder()
                .bId(board.getBId())
                .poster(joinForm.getUserNm())
                .subject("제목!")
                .content("내용!")
                .category(board.getCategory() == null ? null : board.getCategories()[0])
                .build();
    }
    @Test
    @DisplayName("게시글 등록(비회원) 성공시 예외 없음")
    void registerGuestSuccessTest() {
        assertDoesNotThrow(() -> {
            saveService.save(getGuestBoardForm());
        });
    }
}
