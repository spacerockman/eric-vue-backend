package com.spacerockman.eric.service;

import com.spacerockman.eric.dao.UserDAO;
import com.spacerockman.eric.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public boolean isExit(String username){
        User user = getByName(username);
        return null != user;
    }

    public User getByName(String username){
        User user = userDAO.findByUsername(username);
        return user;
    }

    public User get(String username, String password){
        User user = userDAO.getByUsernameAndPassword(username,password);
        return user;
    }



}
