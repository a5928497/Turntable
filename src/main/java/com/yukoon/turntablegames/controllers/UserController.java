package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.entities.User;
import com.yukoon.turntablegames.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public String getUsers(@PathVariable("id")Integer id,Map<String,Object> map) {
        List<User> list = userService.findAllByActID(id);
        map.put("users",list);
        map.put("act_id",id);
        return "background/user_list";
    }

    @PostMapping("/finduser")
    public String finduser(User user,Map<String,Object> map) {
        List<User> list = userService.findByUsernameAndActid(user);
        map.put("users",list);
        map.put("act_id",user.getAct_id());
        return "background/user_list";
    }

    @DeleteMapping("/user/{id}")
    public String delUser(@PathVariable("id")Integer id,Integer act_id) {
        userService.delUser(id);
        return "redirect:/users/"+act_id;
    }

    @GetMapping("/user/{id}")
    public String toEditUser(@PathVariable("id")Integer id,Map<String,Object> map) {
        User user = userService.findById(id);
        map.put("user",user);
        return "background/user_input";
    }

    @PutMapping("/user")
    public String editUser(User user) {
        userService.updateUser(user);
        return "redirect:/users/"+user.getAct_id();
    }

    @GetMapping("/useradd/{id}")
    public String toAddUser(@PathVariable("id")Integer id,Map<String,Object> map) {
        map.put("act_id",id);
        return "background/user_input";
    }

    @PostMapping("/user")
    public String addUser(User user) {
        System.out.println(user);
        userService.addUser(user);
        return "redirect:/users/"+user.getAct_id();
    }
}
