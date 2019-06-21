package com.example.edunext.service;

import com.example.edunext.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userService.finduserByUserName(s);
        if(user==null)
        {
            throw new UsernameNotFoundException("User not Found");
        }
        return new UserDetailsImplementation(user);
    }
}
