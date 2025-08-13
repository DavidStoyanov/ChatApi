package org.myapp.chat_api.models.dto.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDto {
    private String username;
    private String email;
    private String password;
    private String rePassword;
}
