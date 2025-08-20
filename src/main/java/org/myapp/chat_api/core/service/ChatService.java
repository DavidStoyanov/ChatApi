package org.myapp.chat_api.core.service;

import org.myapp.chat_api.models.dto.chat.ChatDto;
import org.myapp.chat_api.models.dto.chat.CreateChatDto;
import org.myapp.chat_api.models.dto.chat.UpdateChatDto;

import java.util.List;

public interface ChatService {
    List<ChatDto> getAll();
    ChatDto getById(String id) throws Exception;
    ChatDto create(CreateChatDto chatDto);
    ChatDto updateById(String id, UpdateChatDto chatDto) throws Exception;
}
