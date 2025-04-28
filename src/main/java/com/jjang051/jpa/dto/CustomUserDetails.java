package com.jjang051.jpa.dto;

import com.jjang051.jpa.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
//@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails, OAuth2User{

    private final Member loggedMember;
    private Map<String, Object> oAuth2UserInfo;


    public CustomUserDetails(Member loggedMember) {
        this.loggedMember = loggedMember;
    }
    public CustomUserDetails(Member loggedMember,Map<String, Object> oAuth2UserInfo) {
        this.loggedMember = loggedMember;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add((GrantedAuthority) () -> loggedMember.getRoles());
        return authorities;
    }



    // 직접 회원가입 한 사람의 정보
    @Override
    public String getPassword() {
        return loggedMember.getUserPW();
    }


    @Override
    public String getUsername() {
        return loggedMember.getUserID();
    }



    // 소셜 로그인을 통해 가입된 사람의 정보
    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2UserInfo;
    }

    @Override
    public String getName() {
        return oAuth2UserInfo.get("name").toString();
    }
}
