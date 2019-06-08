package com.example.edunext.service;

import java.util.List;

import com.example.edunext.model.User;
import com.example.edunext.model.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    @Autowired
    private UserDaoImpl userDao;

    public List<User> getAllUser() {
        return userDao.getAllUser();
    }
}