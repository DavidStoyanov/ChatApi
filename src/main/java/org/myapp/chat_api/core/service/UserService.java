package org.myapp.chat_api.core.service;

import org.myapp.chat_api.models.dto.auth.UserDto;
import org.myapp.chat_api.models.dto.auth.UserLoginServiceDto;
import org.myapp.chat_api.models.dto.auth.UserRegisterServiceDto;

public interface UserService {
    UserDto login(UserLoginServiceDto dto) throws Exception;
    UserDto register(UserRegisterServiceDto dto) throws Exception;
}
