package com.jjang051.jpa.service;

import com.jjang051.jpa.dto.CustomUserDetails;
import com.jjang051.jpa.entity.Member;
import com.jjang051.jpa.repository.MemberRepository;
import com.jjang051.jpa.social.GoogleUserInfo;
import com.jjang051.jpa.social.KakaoUserInfo;
import com.jjang051.jpa.social.NaveruserInfo;
import com.jjang051.jpa.social.SocialUserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuth2DetailsService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> oAuth2UserInfo = oAuth2User.getAttributes();
        String provider = userRequest.getClientRegistration().getRegistrationId();

//        log.info("oAuth2User: {}", oAuth2UserInfo);

        SocialUserInfo socialUserInfo = null;
        Member returnMember = null;

        if (provider.equals("kakao")){
            socialUserInfo = new KakaoUserInfo(oAuth2UserInfo);
        } else if (provider.equals("google")){
            socialUserInfo = new GoogleUserInfo(oAuth2UserInfo);
        } else if (provider.equals("naver")) {
            socialUserInfo = new NaveruserInfo(oAuth2UserInfo);
        }
//        log.info("socialUserInfo: {}", socialUserInfo);
        Optional<Member> foundMember = memberRepository.findByUserID(socialUserInfo.getProviderId());

        if (foundMember.isPresent()) {
            returnMember = foundMember.get();
//        log.info("returnMember: {}", returnMember);
        } else {
            Member member = Member.builder()
                    .userID(socialUserInfo.getProviderId())
                    .userPW(bCryptPasswordEncoder.encode(UUID.randomUUID().toString()))
                    .userName(socialUserInfo.getName())
                    .userEmail(socialUserInfo.getEmail())
                    .roles("ROLE_MEMBER")
                    .build();
            returnMember = memberRepository.save(member);
        }

        return new CustomUserDetails(returnMember, oAuth2UserInfo);
    }
}
