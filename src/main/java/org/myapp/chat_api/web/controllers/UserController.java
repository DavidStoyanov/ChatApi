package org.myapp.chat_api.web.controllers;

import org.modelmapper.ModelMapper;
import org.myapp.chat_api.core.service.UserService;
import org.myapp.chat_api.models.dto.auth.*;
import org.myapp.chat_api.models.dto.response.ApiResponse;
import org.myapp.chat_api.models.dto.user.UserProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final ModelMapper mapper;
    private final UserService userService;

    @Autowired
    public UserController(ModelMapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ApiResponse<UserDto> login(@RequestBody final UserLoginDto userDto) {
        UserLoginServiceDto userServiceDto = this.mapper.map(userDto, UserLoginServiceDto.class);

        ApiResponse<UserDto> response = new ApiResponse<>();
        UserDto user;

        try {
            user = this.userService.login(userServiceDto);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Invalid credentials");
            return response;
        }

        response.setSuccess(true);
        response.setMessage("Login successful");
        response.setData(user);

        return response;
    }

    @PostMapping("/register")
    public ApiResponse<UserDto> registerUser(@RequestBody final UserRegisterDto userDto) {
        UserRegisterServiceDto userServiceDto = this.mapper.map(userDto, UserRegisterServiceDto.class);

        ApiResponse<UserDto> response = new ApiResponse<>();
        UserDto user;

        try {
            user = this.userService.register(userServiceDto);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Invalid credentials!");
            return response;
        }

        response.setSuccess(true);
        response.setMessage("Register successful!");
        response.setData(user);

        return response;
    }

    @PostMapping("/logout")
    public ApiResponse<UserDto> logoutUser() {
        return new ApiResponse<>(
                true, "Logout successfully!", null, null
        );
    }

    @GetMapping("/{id}/profile")
    public ApiResponse<UserProfileDto> getProfileUser(@PathVariable final Long id) {
        ApiResponse<UserProfileDto> response = new ApiResponse<>();
        UserProfileDto profileDto;

        try {
            profileDto = this.userService.getProfile(id);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Profile not found!");
            return response;
        }

        response.setSuccess(true);
        response.setMessage("Profile found.");
        response.setData(profileDto);

        return response;
    }

    @PutMapping("/{id}/profile")
    public ApiResponse<UserProfileDto> updateProfileUser(@PathVariable final Long id,
                                                         @RequestBody final UserProfileDto userProfileDto) {
        ApiResponse<UserProfileDto> response = new ApiResponse<>();
        UserProfileDto profileDto;

        try {
            profileDto = this.userService.updateProfile(id, userProfileDto);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Update profile failed!");
            return response;
        }

        response.setSuccess(true);
        response.setMessage("Update profile successful.");
        response.setData(profileDto);

        return response;
    }
}
