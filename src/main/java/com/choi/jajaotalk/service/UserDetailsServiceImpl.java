package com.choi.jajaotalk.service;

import com.choi.jajaotalk.domain.Role;
import com.choi.jajaotalk.domain.User;
import com.choi.jajaotalk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        User user = userRepository.findByNickname(nickname);

        if (user == null) {
            throw new UsernameNotFoundException(nickname + "is not found.");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (user.getRole().equals("USER")) {
            grantedAuthorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        } else {
            grantedAuthorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        }
        return new org.springframework.security.core.userdetails.User(user.getNickname(), user.getPassword(), grantedAuthorities);
    }
}
