package org.myexam.controllers.admins;

import lombok.Data;

/**
 * 게시판 설정 검색
 */
@Data
public class BoardSearch {
    private int page = 1;   // 기본값을 1로 설정
    private int limit = 20; // 1개 페이지당 출력 갯수

    private String sopt;    // 검색 조건
    private String skey;    // 검색키워드
}
