package com.jjang051.jpa.service;

import com.jjang051.jpa.dto.MemberDto;
import com.jjang051.jpa.entity.Member;
import com.jjang051.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    // field injection 필드주입방식
//    @Autowired
//    MemberDao memberDao;

    // 생성자 주입방식 final을 붙여 상수(불변)로 만든다
    private final MemberRepository memberRepository;
    //    public MemberService(MemberDao memberDao) {
//        this.memberDao = memberDao;
//     }

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Value("${file.upload}")
    private String upload;

    public int signUp(MemberDto memberDto) {

        String rawUserPw = memberDto.getUserPW();
        String encodedUserPW = bCryptPasswordEncoder.encode(rawUserPw);
//        log.info("rawUserPw : {} , encodedUserPW : {}" , rawUserPw,encodedUserPW   );
        memberDto.setUserPW(bCryptPasswordEncoder.encode(memberDto.getUserPW()));
        memberDto.setRoles("ROLE_MEMBER");

        Member member = MemberDto.toEntity(memberDto);

        Member returnMember = memberRepository.save(member);
        if (returnMember != null) {
            return 1;
        }else {
            return 0;
        }
//        return memberRepository.save(member);
    }
}
