package org.myapp.chat_api.core.service;

import org.myapp.chat_api.models.dto.auth.UserRegisterServiceDto;

public interface UserService {
    String register(UserRegisterServiceDto dto);
}
