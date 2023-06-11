package org.myexam.models.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.myexam.commons.MemberUtil;
import org.myexam.controllers.boards.BoardForm;
import org.myexam.entities.Board;
import org.myexam.entities.BoardData;
import org.myexam.models.board.config.BoardConfigInfoService;
import org.myexam.repositories.BoardDataRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardDataSaveService {

    private final BoardValidator validator;
    private final MemberUtil memberUtil;
    private final BoardConfigInfoService configInfoService;
    private final BoardDataRepository repository;
    private final HttpServletRequest request;   // IP를 가져올 수 있다
    private final PasswordEncoder passwordEncoder;  // 비회원 비밀번호 인코더

    public void save(BoardForm boardForm) {
        validator.check(boardForm);

        // 게시글 저장 처리 - 추가, 수정 (BoardForm Long id; 가 있으면 수정, 없으면 추가)
        /**
         * 1. 게시판 설정 - 글 작성, 수정 권한 체크
         *              - 수정 -> 본인이 작성한 글 체크
         * 2. 게시글 저장, 수정
         * 3. 회원 정보 - 게시글 등록시에만 저장
         */
        Long id = boardForm.getId();
        Board board = configInfoService.get(boardForm.getBId(), id == null ? "write":"update");

        BoardData boardData = null;
        if (id == null) {   // 게시글 추가
            String ip = request.getRemoteAddr();    // 사용자쪽 IP
            String ua = request.getHeader("User-Agent");    // 헤더에서 유저 에디터부분을 확인 가능
            boardData = BoardData.builder()
                    .gid(boardForm.getGid())
                    .board(board)
                    .category(boardForm.getCategory())
                    .poster(boardForm.getPoster())
                    .subject(boardForm.getSubject())
                    .content(boardForm.getContent())
                    .ip(ip)
                    .ua(ua)
                    .build();

            if (memberUtil.isLogin()) { // 로그인 시 - 회원 데이터
                boardData.setMember(memberUtil.getEntity());
            } else {    // 비회원 비밀번호
                boardData.setGuestPw(passwordEncoder.encode(boardForm.getGuestPw()));   // 비회원 비밀번호 인코더
            }
        } else {    // 게시글 수정

        }
        boardData = repository.saveAndFlush(boardData);
        boardForm.setId(boardData.getId());
    }
}
