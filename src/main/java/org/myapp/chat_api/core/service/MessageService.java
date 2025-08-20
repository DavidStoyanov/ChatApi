package org.myapp.chat_api.core.service;

import org.myapp.chat_api.models.dto.message.CreateMessageDto;
import org.myapp.chat_api.models.dto.message.MessageDto;

import java.util.List;

public interface MessageService {

    List<MessageDto> getAll();

    List<MessageDto> getAllByRoom(String roomId) throws Exception;

    MessageDto getById(Long id) throws Exception;

    MessageDto create(CreateMessageDto createMessageDto) throws Exception;
}
