package com.choi.jajaotalk.service;

import com.choi.jajaotalk.domain.User;
import com.choi.jajaotalk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public List<User> findUsers(User user){
        List<User> findUsers = userRepository.findUserName(user.getNickname());
        return findUsers;
    }
    @Transactional
    public Long signUp(User user){
        userRepository.save(user);
        return user.getId();
    }
    public User signIn(User user){
        User loginUser = userRepository.findByNickname(user.getNickname());
        return loginUser;
    }
}
