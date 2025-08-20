package org.myapp.chat_api.web.controllers;

import org.myapp.chat_api.core.service.MessageService;
import org.myapp.chat_api.models.dto.message.CreateMessageDto;
import org.myapp.chat_api.models.dto.message.MessageDto;
import org.myapp.chat_api.models.dto.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping()
    public ApiResponse<List<MessageDto>> getAllMessages() {
        ApiResponse<List<MessageDto>> response = new ApiResponse<>();
        List<MessageDto> allMessages;

        try {
            allMessages = this.messageService.getAll();
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Messages not found!");
            return response;
        }

        response.setSuccess(true);
        response.setMessage("Messages found!");
        response.setData(allMessages);

        return response;
    }

    //todo remove
    @GetMapping("/{id}/room-specific")
    public ApiResponse<List<MessageDto>> getAllMessagesForRoom(@PathVariable String id) {
        ApiResponse<List<MessageDto>> response = new ApiResponse<>();
        List<MessageDto> allMessages;

        try {
            allMessages = this.messageService.getAllByRoom(id);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Messages not found!");
            return response;
        }

        response.setSuccess(true);
        response.setMessage("Messages found!");
        response.setData(allMessages);

        return response;
    }

    @GetMapping("/{id}")
    public ApiResponse<MessageDto> getMessage(@PathVariable Long id) {
        ApiResponse<MessageDto> response = new ApiResponse<>();
        MessageDto messageDto;

        try {
            messageDto = this.messageService.getById(id);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Messages not found!");
            return response;
        }

        response.setSuccess(true);
        response.setMessage("Messages found!");
        response.setData(messageDto);

        return response;
    }

    @PostMapping()
    public ApiResponse<MessageDto> createMessage(@RequestBody CreateMessageDto createMessageDto) {
        ApiResponse<MessageDto> response = new ApiResponse<>();
        MessageDto messageDto;

        try {
            messageDto = this.messageService.create(createMessageDto);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Message create failed!");
            return response;
        }

        response.setSuccess(true);
        response.setMessage("Message created!");
        response.setData(messageDto);

        return response;
    }

}
