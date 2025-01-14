package com.sweetme.back.common.domain;

import com.sweetme.back.auth.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_notifications")
@Getter
@Setter
public class Notification {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    // 알림 수신자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 알림 유형
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    // 알림 메시지
    @Column(nullable = false)
    private String message;

    // 알림 클릭시 이동할 URL
    @Column(nullable = false)
    private String url;

    // 읽음 여부
    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;

    // 알림 발생일시
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public enum NotificationType {
        NEW_MEMBER_REQUEST,  // 새 멤버 요청
        COMMENT_REPLY,       // 댓글 알림
        TASK_REMINDER,       // 태스크 알림
        SYSTEM              // 시스템 알림
    }
}
