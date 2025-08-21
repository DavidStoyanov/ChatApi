package org.myapp.chat_api.web.controllers;

import org.modelmapper.ModelMapper;
import org.myapp.chat_api.core.service.ChatService;
import org.myapp.chat_api.models.dto.chat.ChatDto;
import org.myapp.chat_api.models.dto.chat.CreateChatDto;
import org.myapp.chat_api.models.dto.chat.UpdateChatDto;
import org.myapp.chat_api.models.dto.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {
    private final ModelMapper modelMapper;
    private final ChatService chatService;

    @Autowired
    public ChatController(ModelMapper modelMapper, ChatService chatService) {
        this.modelMapper = modelMapper;
        this.chatService = chatService;
    }

    @GetMapping()
    public ApiResponse<List<ChatDto>> getAllChats() {
        ApiResponse<List<ChatDto>> response = new ApiResponse<>();
        List<ChatDto> allChats;

        try {
            allChats = this.chatService.getAll();
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Chats not found!");
            return response;
        }

        response.setSuccess(true);
        response.setMessage("Chats found!");
        response.setData(allChats);

        return response;
    }

    //todo remove
    @GetMapping("{id}/user-specific")
    public ApiResponse<List<ChatDto>> getAllChats(@PathVariable Long id) {
        ApiResponse<List<ChatDto>> response = new ApiResponse<>();
        List<ChatDto> allChats;

        try {
            allChats = this.chatService.getAllByUser(id);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Chats not found!");
            return response;
        }

        response.setSuccess(true);
        response.setMessage("Chats found!");
        response.setData(allChats);

        return response;
    }

    @GetMapping("/{id}")
    public ApiResponse<ChatDto> getChat(@PathVariable String id) {
        ApiResponse<ChatDto> response = new ApiResponse<>();
        ChatDto chatDto;

        try {
            chatDto = this.chatService.getById(id);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Chats not found!");
            return response;
        }

        response.setSuccess(true);
        response.setMessage("Chats found!");
        response.setData(chatDto);

        return response;
    }

    @PostMapping()
    public ApiResponse<ChatDto> createChat(@RequestBody CreateChatDto createChatDto) {
        ApiResponse<ChatDto> response = new ApiResponse<>();
        ChatDto chatDto;

        try {
            chatDto = this.chatService.create(createChatDto);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Chat create failed!");
            return response;
        }

        response.setSuccess(true);
        response.setMessage("Chat created!");
        response.setData(chatDto);

        return response;
    }

    @PatchMapping("/{id}")
    public ApiResponse<ChatDto> updateChat(@PathVariable String id, @RequestBody UpdateChatDto updateChatDto) {
        ApiResponse<ChatDto> response = new ApiResponse<>();
        ChatDto chatDto;

        try {
            chatDto = this.chatService.updateById(id, updateChatDto);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Chat update failed!");
            return response;
        }

        response.setSuccess(true);
        response.setMessage("Chat updated!");
        response.setData(chatDto);

        return response;
    }

}
