package org.myapp.chat_api.core.service;

import org.myapp.chat_api.models.dto.auth.UserDto;
import org.myapp.chat_api.models.dto.auth.UserLoginServiceDto;
import org.myapp.chat_api.models.dto.auth.UserRegisterServiceDto;
import org.myapp.chat_api.models.dto.user.UserProfileDto;
import org.myapp.chat_api.models.entity.User;

public interface UserService {
    UserDto login(UserLoginServiceDto dto) throws Exception;
    UserDto register(UserRegisterServiceDto dto) throws Exception;
    UserProfileDto getProfile(Long id) throws Exception;
    UserProfileDto updateProfile(Long id, UserProfileDto dto) throws Exception;

    User getReferenceById(Long id);
}
