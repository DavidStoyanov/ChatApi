package org.myapp.chat_api.core.service;

import org.modelmapper.ModelMapper;
import org.myapp.chat_api.models.dto.chat.ChatDto;
import org.myapp.chat_api.models.dto.chat.CreateChatDto;
import org.myapp.chat_api.models.dto.chat.UpdateChatDto;
import org.myapp.chat_api.models.entity.User;
import org.myapp.chat_api.models.entity.chat.Chat;
import org.myapp.chat_api.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {
    private final UserService userService;

    private final ModelMapper modelMapper;
    private final ChatRepository chatRepository;


    @Autowired
    public ChatServiceImpl(UserService userService, ModelMapper modelMapper, ChatRepository chatRepository) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.chatRepository = chatRepository;
    }

    @Override
    @Transactional
    public List<ChatDto> getAll() {
        List<Chat> chats = this.chatRepository.findAll();
        ChatDto[] chatsDtoArr = this.modelMapper.map(chats, ChatDto[].class);
        return List.of(chatsDtoArr);
    }

    @Transactional //checknote1
    @Override
    public ChatDto getById(String id) throws Exception {
        Chat chat = this.chatRepository.findByConversationId(id)
            .orElseThrow(() -> new Exception("Chat not found!"));
        return this.modelMapper.map(chat, ChatDto.class);
    }

    @Override
    public ChatDto create(CreateChatDto chatDto) {
        Chat chat = this.modelMapper.map(chatDto, Chat.class);

        User creator = this.userService.getReferenceById(chatDto.getCreator());
        chat.setCreator(creator);

        //chat.setParticipants(new HashSet<>());
        chat.getParticipants().add(creator);

        this.chatRepository.save(chat);
        //this.addParticipantToChat(chat, creator);

        return this.modelMapper.map(chat, ChatDto.class);

    }

    @Transactional
    protected void addParticipantToChat(Chat chat, User creator) {
        chat.getParticipants().add(creator);
        this.chatRepository.save(chat);
    }

    @Transactional
    @Override
    public ChatDto updateById(String id, UpdateChatDto chatDto) throws Exception {
        Chat chat = this.chatRepository.findByConversationId(id)
                .orElseThrow(() -> new Exception("Chat not found!"));

        this.modelMapper.map(chatDto, chat);

        if (chatDto.getDisable() && chat.getDisabledOn() == null) {
            chat.setDisabledOn(LocalDateTime.now());
        }

        this.chatRepository.save(chat);
        return this.modelMapper.map(chat, ChatDto.class);
    }

    @Override
    public Optional<Chat> getByConversationId(String conversationId) {
        return this.chatRepository.findByConversationId(conversationId);
    }
}
