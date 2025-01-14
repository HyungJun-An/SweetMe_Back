package com.sweetme.back.member.domain;

import com.sweetme.back.auth.domain.User;
import com.sweetme.back.common.domain.BaseEntity;
import com.sweetme.back.studygroup.domain.Study;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_member")
@Getter
@Setter
public class Member extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id", nullable = false)
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus status = MemberStatus.PENDING;

    public enum MemberStatus {
        PENDING, ACCEPTED, REJECTED
    }
}