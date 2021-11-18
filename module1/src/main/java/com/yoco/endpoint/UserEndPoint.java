package com.yoco.endpoint;

import com.yoco.entity.User;
import com.yoco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserEndPoint {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String hello() {

        System.out.println(" came here ");
        return userService.test();
    }

    @GetMapping("/get")
    public User get(@RequestParam String id) {

        return userService.getUser(id);
    }

    @PostMapping("/save")
    public User save(@RequestParam String name, @RequestParam String password){

        return userService.saveUser(name,password);
    }
}
