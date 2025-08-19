package org.myapp.chat_api.core.service;

import org.modelmapper.ModelMapper;
import org.myapp.chat_api.models.dto.auth.UserDto;
import org.myapp.chat_api.models.dto.auth.UserLoginServiceDto;
import org.myapp.chat_api.models.dto.auth.UserRegisterServiceDto;
import org.myapp.chat_api.models.dto.user.UserProfileDto;
import org.myapp.chat_api.models.entity.User;
import org.myapp.chat_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ModelMapper mapper, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto login(UserLoginServiceDto dto) throws Exception {
        User user = this.userRepository
                .findOneByEmail(dto.getEmail())
                .orElseThrow(() -> new Exception("User not Found"));

        if (!this.passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new Exception("Invalid password");
        }

        return this.mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto register(UserRegisterServiceDto dto) throws Exception {
        Optional<User> existingUser = this.userRepository.findOneByEmail(dto.getEmail());

        if (existingUser.isPresent()) {
            throw new Exception("User with email already exists");
        }

        String hashedPassword = this.passwordEncoder.encode(dto.getPassword());

        User user = this.mapper.map(dto, User.class);
        user.setPassword(hashedPassword);

        this.userRepository.save(user);

        return this.mapper.map(user, UserDto.class);
    }

    @Override
    public UserProfileDto getProfile(Long id) throws Exception {
        //TODO: Security: get Auth Header and check user

        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found!"));

        UserProfileDto profileDto = this.mapper.map(user, UserProfileDto.class);

        return profileDto;
    }

    @Override
    public UserProfileDto updateProfile(Long id, UserProfileDto dto) throws Exception {
        //TODO: Security: get Auth Header and check user

        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found!"));

        this.mapper.map(dto, user);

        this.userRepository.save(user);

        return this.mapper.map(user, UserProfileDto.class);
    }

}
