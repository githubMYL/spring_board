package org.myexam.commons.validators;

public interface MobileValidator {
    default boolean mobileNumCheck(String mobile) {
        /**
         * 010-34810-2101
         * 010_34810_2101
         * 010 34810 2101
         *
         * 1. 형식의 통일화 - 숫자가 아닌 문자가 전부 제거 -> 숫자
         * 2. 패턴 생성 체크
         */
        mobile = mobile.replaceAll("\\D", "");
        String pattern = "^01[016789]\\d{3,4}\\d{4}$";

        return mobile.matches(pattern);
    }
}
