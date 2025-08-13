package org.myapp.chat_api.models.dto.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterServiceDto {
    private String username;
    private String email;
    private String password;
}
