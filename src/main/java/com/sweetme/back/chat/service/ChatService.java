package com.sweetme.back.chat.service;

import com.sweetme.back.chat.domain.Chat;
import com.sweetme.back.chat.dto.ChatResponseDTO;
import com.sweetme.back.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService {

    private final ChatRepository chatRepository;

    public List<ChatResponseDTO> getChatsByStudyId(Long studyId) {
        List<Chat> chats = chatRepository.findByStudyId(studyId);
        return chats.stream()
                .map(ChatResponseDTO::new)
                .collect(Collectors.toList());
    }
}