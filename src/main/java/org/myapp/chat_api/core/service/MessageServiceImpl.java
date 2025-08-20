package org.myapp.chat_api.core.service;

import org.modelmapper.ModelMapper;
import org.myapp.chat_api.models.dto.message.CreateMessageDto;
import org.myapp.chat_api.models.dto.message.MessageDto;
import org.myapp.chat_api.models.entity.User;
import org.myapp.chat_api.models.entity.chat.Message;
import org.myapp.chat_api.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final UserService userService;

    private final ModelMapper modelMapper;
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(
            UserService userService,
            ModelMapper modelMapper,
            MessageRepository messageRepository
    ) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.messageRepository = messageRepository;
    }

    @Transactional
    @Override
    public List<MessageDto> getAll() {
        List<Message> messages = this.messageRepository.findAll();
        MessageDto[] messageDtoArr = this.modelMapper.map(messages, MessageDto[].class);
        return List.of(messageDtoArr);
    }

    @Transactional
    @Override
    public MessageDto getById(Long id) throws Exception {
        Message message = this.messageRepository.findById(id)
                .orElseThrow(() -> new Exception("Message not found!"));
        return this.modelMapper.map(message, MessageDto.class);
    }

    @Transactional
    @Override
    public MessageDto create(CreateMessageDto createMessageDto) {
        Message message = this.modelMapper.map(createMessageDto, Message.class);

        User sender = this.userService.getReferenceById(createMessageDto.getSender());
        message.setSender(sender);

        this.messageRepository.save(message);

        return this.modelMapper.map(message, MessageDto.class);
    }
}
