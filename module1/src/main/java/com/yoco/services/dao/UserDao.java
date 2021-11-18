package com.yoco.services.dao;

import com.yoco.entity.User;

public interface UserDao {

     User save(User user);

     User get(String id);
}
