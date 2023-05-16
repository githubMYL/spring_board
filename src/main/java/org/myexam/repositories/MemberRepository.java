package org.myexam.repositories;

import org.myexam.entities.Member;
import org.myexam.entities.QMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberRepository extends JpaRepository<Member, Long>,
        QuerydslPredicateExecutor<Member> {

    // 아이디로 회원 조회
    Member findByUserId(String userId);

    /** 아이디로 회원 존재 유무 체크 S */
    default boolean exists (String userId) {
        QMember member = QMember.member;

        return exists(member.userId.eq(userId));

    }
    /** 아이디로 회원 존재 유무 체크 E */
}
