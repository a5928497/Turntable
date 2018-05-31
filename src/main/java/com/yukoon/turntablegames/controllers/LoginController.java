package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.entities.User;
import com.yukoon.turntablegames.services.ActivityService;
import com.yukoon.turntablegames.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.websocket.Session;
import java.util.Map;

@Controller
@SessionAttributes(value = {"user"},types = {User.class})
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;

    @PostMapping("/login")
    public String login(Map<String,Object> map, User user,String flag){
        User user_temp = userService.login(user);
        if (user_temp != null) {
            //若不为空则代表验证正确
            map.put("user",user_temp);
        }else {
            //若从后台登录，则回后台登录界面
            if (flag.equals("bg")){
                return "redirect:/loginpage/login.html";
            }
            //前台登录则返回前台登录界面
            return "redirect:/index.html";
        }
        if (user_temp.getRole_id() == 2) {
            //若为管理员，则进入后台
            return "redirect:/acts";
        }
        if (activityService.getStatusById(user_temp.getAct_id()) == 2) {
            //若活动已经结束，则前往奖品查询页面
            return "redirect:/pbaward/" + user_temp.getId();
        }
        return "public/pb_index";
    }

    @GetMapping("/reflash")
    public String reflash(ModelMap modelMap, User user) {
        //更新session中user信息
        modelMap.addAttribute("user",userService.findById(user.getId()));
        return "public/pb_index";
    }
}
