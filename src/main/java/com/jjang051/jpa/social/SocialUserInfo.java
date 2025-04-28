package com.jjang051.jpa.social;

public interface SocialUserInfo {
    public String getProvider();
    public String getProviderId();

    public String getEmail();
    public String getName();
}
