package org.myapp.chat_api.web.controllers;

import org.modelmapper.ModelMapper;
import org.myapp.chat_api.core.service.UserService;
import org.myapp.chat_api.models.dto.auth.UserRegisterDto;
import org.myapp.chat_api.models.dto.auth.UserRegisterServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/users")
public class UserController {

    private final ModelMapper mapper;

    private final UserService userService;

    @Autowired
    public UserController(ModelMapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody final UserRegisterDto userDto) {
        if (!userDto.getPassword().equals(userDto.getRePassword())) {
            return "{status: 400 Bad Request}";
        }

        UserRegisterServiceDto userServiceDto = this.mapper.map(userDto, UserRegisterServiceDto.class);

        String response = this.userService.register(userServiceDto);

        return "{status: 200 OK}";
    }
}
