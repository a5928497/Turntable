package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.entities.User;
import com.yukoon.turntablegames.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(Map<String,Object> map, User user){
        System.out.println(user);
        User user_temp = userService.login(user);
        if (user_temp != null) {
            map.put("User",user_temp);
        }
        if (user_temp.getRole_id() == 2) {
            return "background/bg_index";
        }
        return "public/pb_index";
    }

    @GetMapping("/users/{id}")
    public String getUsers(@PathVariable("id")Integer id,Map<String,Object> map) {
        List<User> list = userService.findAllByActID(id);
        map.put("users",list);
        map.put("act_id",id);
        return "background/user_list";
    }

    @DeleteMapping("/user/{id}")
    public String delUser(@PathVariable("id")Integer id,Integer act_id) {
        userService.delUser(id);
        return "redirect:/users/"+act_id;
    }
}
