package org.myexam.controllers.members;

import lombok.RequiredArgsConstructor;
import org.myexam.commons.validators.MobileValidator;
import org.myexam.repositories.MemberRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator, MobileValidator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return JoinForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        /**
         * 1. 아이디 중복 여부
         * 2. 비밀번호 복잡성 체크 (알파벳(대문자, 소문자), 숫자, 특수문자))
         * 3. 비밀번호와 비밀번호 확인 일치 확인
         * 4. 휴대전화번호(선택) - 입력된 경우 형식 체크
         * 5. 휴대전화번호가 입력된 경우 숫자만 추출해서 다시 커맨드 객체에 저장
         * 6. 필수 약관 동의 체크
         */

        JoinForm joinForm = (JoinForm) target;
        String userId = joinForm.getUserId();
        String userPw = joinForm.getUserPw();
        String userPwRe = joinForm.getUserPwRe();
        String mobile = joinForm.getMobile();
        boolean[] agrees = joinForm.getAgrees();    // 필수 약관

        /** 1. 아이디 중복 여부 S */
        if (userId != null && !userId.isBlank() && memberRepository.exists(userId)) {
            errors.rejectValue("userId", "Validation.duplicate.userId");
        }
        /** 1. 아이디 중복 여부 E */

        /** 2. 비밀번호 복잡성 체크 (알파벳(대문자, 소문자), 숫자, 특수문자)) S */

        /** 2. 비밀번호 복잡성 체크 (알파벳(대문자, 소문자), 숫자, 특수문자)) E */

        /** 3. 비밀번호와 비밀번호 확인 일치 S */
        if (userPw != null && !userPw.isBlank()
                && userPwRe != null && !userPwRe.isBlank() && !userPw.equals(userPwRe)) {
            errors.rejectValue("userPwRe", "Validation.incorrect.userPwRe");
        }
        /** 3. 비밀번호와 비밀번호 확인 일치 E */

        /** 4. 휴대전화번호(선택) - 입력된 경우 형식 체크 S
         *  5. 휴대전화번호가 입력된 경우 숫자만 추출해서 다시 커맨드 객체에 저장 S
         */
        if (mobile != null && !mobile.isBlank()) {
            if (!mobileNumCheck(mobile)){
                errors.rejectValue("mobile", "Validation");
            }
            mobile = mobile.replaceAll("\\D", "");
            joinForm.setMobile(mobile);
        }
        /** 4. 휴대전화번호(선택) - 입력된 경우 형식 체크 E
         *  5. 휴대전화번호가 입력된 경우 숫자만 추출해서 다시 커맨드 객체에 저장 E
         */

        /** 6. 필수 약관 동의 체크 S */

        if (agrees != null && agrees.length > 0) {
            for (boolean agree : agrees){
                if (!agree) {
                    errors.reject("Validation.joinForm.agree");
                    break;
                }
            }
        }
        /** 6. 필수 약관 동의 체크 E */
    }
}
