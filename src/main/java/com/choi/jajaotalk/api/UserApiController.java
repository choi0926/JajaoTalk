package com.choi.jajaotalk.api;

import com.choi.jajaotalk.domain.User;
import com.choi.jajaotalk.service.UserService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("api/user/login")
    public LoginResult login(@RequestBody User user){
        List<User> findUsers =  userService.findUsers(user);
        if(findUsers.isEmpty()){
            User signUpUser = new User();
            signUpUser.setNickname(user.getNickname());
            String encryptPassword = passwordEncoder.encode(user.getPassword());
            signUpUser.setPassword(encryptPassword);
            userService.signUp(signUpUser);
            UserDto signUpUserDto = new UserDto();
            signUpUserDto.setNickname(signUpUser.getNickname());
            return new LoginResult(true,200,"signUp success.", signUpUserDto);
        }else {
            User passwordMatchCheckUser =  userService.signIn(user);
            if(!passwordEncoder.matches(user.getPassword(),passwordMatchCheckUser.getPassword())){
                UserDto notLoginUserDto = new UserDto();
                return new LoginResult(true,200,"The password is wrong.", notLoginUserDto);
            }
            UserDto loginUserDto = new UserDto();
            loginUserDto.setNickname(passwordMatchCheckUser.getNickname());
            return new LoginResult(true,200,"signIn success.", loginUserDto);
        }
    }

    @Getter @Setter
    @AllArgsConstructor
    static class LoginResult<T> {
        private boolean  success;
        private int status;
        private String message;
        private T data;
    }

    @Getter @Setter
    static class UserDto {
        private String nickname;
    }
}

