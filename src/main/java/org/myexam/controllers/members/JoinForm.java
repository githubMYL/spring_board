package org.myexam.controllers.members;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO 클래스
 */

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class JoinForm {

    @NotBlank
    @Size(min = 6, max = 20)
    private String userId;      // 회원 아이디

    @NotBlank
    @Size(min = 8)
    private String userPw;      // 회원 비밀번호

    @NotBlank
    private String userPwRe;    // 회원 비밀번호확인

    @NotBlank
    private String userNm;      // 회원 이름

    @NotBlank @Email
    private String email;       // 회원 이메일

    private String mobile;      // 회원 핸드폰번호

    private boolean[] agrees;   // 회원 약관 동의

}
