package com.sweetme.back.auth.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.sweetme.back.auth.domain.User.*;

@Getter
@Setter
@ToString
public class UserDTO extends User {

    private String email;
    private String password;
    private String nickname;
    private LoginType loginType;
    private UserStatus status;
    private UserRole role;

    public UserDTO(String email, String password, String nickname,
                    LoginType loginType, UserStatus status, UserRole role) {
        super(email, password, Collections.singleton(new SimpleGrantedAuthority(role.name())));

        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.loginType = loginType;
        this.status = status;
        this.role = role;
    }

    // JWT 생성 시 사용
    public Map<String, Object> getClaims() {

        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("email", email);
        dataMap.put("password", password);
        dataMap.put("nickname", nickname);
        dataMap.put("loginType", loginType);
        dataMap.put("status", status);
        dataMap.put("role", role);

        return dataMap;
    }
}
