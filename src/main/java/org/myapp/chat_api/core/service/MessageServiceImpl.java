package org.myapp.chat_api.core.service;

import org.modelmapper.ModelMapper;
import org.myapp.chat_api.models.dto.message.CreateMessageDto;
import org.myapp.chat_api.models.dto.message.MessageDto;
import org.myapp.chat_api.models.entity.User;
import org.myapp.chat_api.models.entity.chat.Chat;
import org.myapp.chat_api.models.entity.chat.Message;
import org.myapp.chat_api.repository.ChatRepository;
import org.myapp.chat_api.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    private final UserService userService;
    private final ChatService chatService;

    //todo: remove
    private final ChatRepository chatRepository;

    private final ModelMapper modelMapper;
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(
            UserService userService, ChatService chatService, ChatRepository chatRepository,
            ModelMapper modelMapper,
            MessageRepository messageRepository
    ) {
        this.userService = userService;
        this.chatService = chatService;
        this.chatRepository = chatRepository;
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

    //todo: remove
    @Transactional
    @Override
    public List<MessageDto> getAllByRoom(String roomId) throws Exception {
        //List<Message> messages = this.messageRepository.findAll();

        Chat chat = this.chatRepository.findByConversationId(roomId)
                .orElseThrow(() -> new Exception("chat not found"));

        List<Message> messages = chat.getMessages();
        //Long chatId = chat.getId();
        //messages = messages.stream().filter(x -> x.getId().equals(chatId)).toList();

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
    public MessageDto create(CreateMessageDto createMessageDto) throws Exception {
//        Message message = this.modelMapper.map(createMessageDto, Message.class);
        Message message = new Message();
        message.setContent(createMessageDto.getContent());

        User sender = this.userService.getReferenceById(createMessageDto.getSender());
        message.setSender(sender);

        Chat chat = this.chatService
                .getByConversationId(createMessageDto.getConversationId())
                .orElseThrow(() -> new Exception("Chat not found!"));

        chat.getMessages().add(message);

        this.messageRepository.save(message);

        return this.modelMapper.map(message, MessageDto.class);
    }

    //public

}
