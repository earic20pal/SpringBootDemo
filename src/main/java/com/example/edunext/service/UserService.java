package com.example.edunext.service;

import java.util.List;

import com.example.edunext.model.User;
import com.example.edunext.model.UserDaoImpl;
import com.example.edunext.model.UserDaoImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class UserService {

    @Autowired
    private UserDaoImpl userDao;

    private UserDaoImpl2 userDao2;


    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    public List<User>  getAllUser1() {
        return userDao2.getAllUser();
    }

    public User finduserByUserName(HttpServletRequest request, String username)
    {
        return userDao.getUserByUserName(request,username);
    }
}