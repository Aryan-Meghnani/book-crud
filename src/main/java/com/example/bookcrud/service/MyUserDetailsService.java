package com.example.bookcrud.service;

import com.example.bookcrud.entity.Logininfo;
import com.example.bookcrud.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Logininfo userInfo=authRepository.findByUsername(userName);
        if(userInfo==null){
            throw new UsernameNotFoundException("User Not Available For Given Username");
        }
        return new User(userInfo.getUsername(),userInfo.getPassword(),new ArrayList<>());
    }
}
