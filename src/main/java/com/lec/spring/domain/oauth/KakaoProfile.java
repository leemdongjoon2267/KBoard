
package com.lec.spring.domain.oauth;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
public class KakaoProfile {

    public Long id;
    @JsonProperty("connected_at")
    public String connectedAt;
    public Properties properties;
    @JsonProperty("kakao_account")
    public KakaoAccount kakaoAccount;

    @Data
    public static class KakaoAccount {

        @JsonProperty("profile_nickname_needs_agreement")
        public Boolean profileNicknameNeedsAgreement;
        @JsonProperty("profile_image_needs_agreement")
        public Boolean profileImageNeedsAgreement;
        public Profile profile;

        @Data
        public static class Profile {

            public String nickname;
            @JsonProperty("thumbnail_image_url")
            public String thumbnailImageUrl;
            @JsonProperty("profile_image_url")
            public String profileImageUrl;
            @JsonProperty("is_default_image")
            public Boolean isDefaultImage;
            @JsonProperty("is_default_nickname")
            public Boolean isDefaultNickname;

        } // end Profile

    } // end KakaoAccount

    @Data
    public static class Properties {

        public String nickname;
        @JsonProperty("profile_image")
        public String profileImage;
        @JsonProperty("thumbnail_image")
        public String thumbnailImage;

    } // end Properties

} // end KakaoProfile









