package com.yoco.service;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.yoco.entity.User;
import com.yoco.services.dao.UserDao;
import com.yoco.services.impl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    UserDao userDao = new UserImpl();

//    @Autowired
//    UserDao userDao;

    public String test(){

        return "from module 1";
    }

    public User saveUser(String name, String password){
        System.out.println(" saving user to db name " + name + " password " + password);
        User user = new User();
//        user.setId(UUID.randomUUID().getLeastSignificantBits());
        user.setName(name);
        user.setPassword(password);

        String jsonString = new com.google.gson.Gson().toJson(user);

        System.out.println(" in save user " + jsonString);

        return userDao.save(user);
    }

    public User getUser(String key) {
        System.out.println(" fetching user from db ");
        return userDao.get(key);
    }

}
