package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.entities.Activity;
import com.yukoon.turntablegames.mappers.ActivityMapper;
import com.yukoon.turntablegames.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @PostMapping("/act")
    public String actAdd(Activity activity) {
        activityService.addAct(activity);
        return "redirect:/acts";
    }

    @GetMapping("/act")
    public String actToAdd() {
        return "background/act_input";
    }

    @GetMapping("/acts")
    public String list(Map<String,Object> map) {
        List<Activity> list  = activityService.findAll();
        map.put("acts",list);
        return "background/act_list";
    }

    @GetMapping("/actclose/{id}")
    public  String actClose(@PathVariable("id") Integer id) {
        activityService.closeAct(id);
        return "redirect:/acts";
    }

    @GetMapping("/actopen/{id}")
    public  String actOpen(@PathVariable("id") Integer id) {
        activityService.openAct(id);
        return "redirect:/acts";
    }
}
