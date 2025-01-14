package com.sweetme.back.auth.domain;

import com.sweetme.back.board.domain.Board;
import com.sweetme.back.common.domain.BaseEntity;
import com.sweetme.back.studygroup.domain.Study;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
public class User extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "login_type", nullable = false)
    private LoginType loginType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status = UserStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role = UserRole.ROLE_USER;

    private String password;

    public enum LoginType {
        EMAIL, KAKAO, NAVER
    }

    public enum UserStatus {
        ACTIVE, INACTIVE
    }

    public enum UserRole {
        ROLE_USER, ROLE_ADMIN
    }

    @ManyToMany(mappedBy = "userStudyLikes")
    private List<Study> studyLikes = new ArrayList<>();

    @ManyToMany(mappedBy = "userBoardLikes")
    private List<Board> boardLikes = new ArrayList<>();
}