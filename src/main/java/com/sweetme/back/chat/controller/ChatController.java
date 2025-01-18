package com.sweetme.back.chat.controller;

import com.sweetme.back.chat.dto.ChatResponseDTO;
import com.sweetme.back.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/studies")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/{studyId}/chat")
    public ResponseEntity<List<ChatResponseDTO>> getChatsByStudyId(
            @PathVariable Long studyId) {

        List<ChatResponseDTO> chats = chatService.getChatsByStudyId(studyId);
        return ResponseEntity.ok(chats);
    }
}
