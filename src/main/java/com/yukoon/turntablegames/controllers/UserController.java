package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.entities.User;
import com.yukoon.turntablegames.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(Map<String,Object> map, User user){
        User user_temp = userService.login(user);
        if (user_temp != null) {
            map.put("User",user_temp);
        }
        if (user_temp.getAct_id() == 1) {
            return "background";
        }
        return "draw";
    }
}
