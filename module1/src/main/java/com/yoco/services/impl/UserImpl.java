package com.yoco.services.impl;

import com.googlecode.objectify.Key;
import com.yoco.services.OfyService;
import com.yoco.services.dao.UserDao;
import com.yoco.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

public class UserImpl implements UserDao {

    @Autowired
    OfyService ofyService;

    @Override
    public User save(User user) {
        String jsonString = new com.google.gson.Gson().toJson(user);

        System.out.println(" in save user impl " + jsonString);
        Key<User> resp = ofyService.save(user);
        System.out.println(resp);
        return user;
    }

    @Override
    public User get(String id) {
        System.out.println(" in fetch user impl " + id);
        User resp =  ofyService.get(User.class,id);
        System.out.println(resp);
        return resp;
    }

}
