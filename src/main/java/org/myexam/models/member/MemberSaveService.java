package org.myexam.models.member;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.myexam.controllers.members.JoinForm;
import org.myexam.entities.Member;
import org.myexam.repositories.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
/**
 *  회원 정보 추가, 수정
 *  - 수정시 비밀번호는 값이 있을 때만 수정
 */
@Service
@RequiredArgsConstructor
public class MemberSaveService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public void save(JoinForm joinForm) {

        Member member = new ModelMapper().map(joinForm, Member.class);
        member.setUserPw(passwordEncoder.encode(joinForm.getUserPw()));

        memberRepository.saveAndFlush(member);

    }


}
