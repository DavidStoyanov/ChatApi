package org.myapp.chat_api.core.service;

import org.myapp.chat_api.models.dto.auth.UserRegisterServiceDto;
import org.myapp.chat_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String register(UserRegisterServiceDto dto) {
        return "";
    }
}
