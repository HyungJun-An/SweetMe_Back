// ChatRepository.java
package com.sweetme.back.chat.repository;

import com.sweetme.back.chat.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByStudyId(Long studyId);
}