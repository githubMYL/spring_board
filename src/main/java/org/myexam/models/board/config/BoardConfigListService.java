package org.myexam.models.board.config;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.myexam.controllers.admins.BoardSearch;
import org.myexam.entities.Board;
import org.myexam.entities.QBoard;
import org.myexam.repositories.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.Sort.Order.desc;

/**
 * 게시판 설정 목록
 */
@Service
@RequiredArgsConstructor
public class BoardConfigListService {
    private final BoardRepository boardRepository;

    public Page<Board> gets(BoardSearch boardSearch) {
        QBoard board = QBoard.board;

        BooleanBuilder andBuilder = new BooleanBuilder();

        int page = boardSearch.getPage();
        int limit = boardSearch.getLimit();
        page = page < 1 ? 1 : page;
        limit = limit <1 ? 20 : limit;

        /** 검색 조건 처리 S */
        String sopt = boardSearch.getSopt();
        String skey = boardSearch.getSkey();
        if (sopt != null && !sopt.isBlank() && skey != null && !skey.isBlank()) {
            skey = skey.trim(); // 공백제거
            sopt = sopt.trim(); // 공백제거

            if (sopt.equals("all")) { // 통합 검색 - bId, bName
                BooleanBuilder orBuilder = new BooleanBuilder();
                orBuilder.or(board.bId.contains(skey))
                        .or(board.bName.contains(skey));
                andBuilder.and(orBuilder);  // 둘중에 하나만 포함이 되어 있어도 나오게 됨

            } else if (sopt.equals("bId")) {    // 게시판 아이디 bId
                andBuilder.and(board.bId.contains(skey));

            } else if (sopt.equals("bName")) {  // 게시판명 bName
                andBuilder.and(board.bName.contains(skey));

            }
        }
        /** 검색 조건 처리 E */

        Pageable pageable = PageRequest.of(page-1, limit, Sort.by(desc("createdAt")));
        Page<Board> data = boardRepository.findAll(andBuilder, pageable);

        return data;

    }
}
