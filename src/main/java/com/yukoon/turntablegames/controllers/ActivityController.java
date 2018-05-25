package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.entities.Activity;
import com.yukoon.turntablegames.mappers.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActivityController {
    @Autowired
    private ActivityMapper activityMapper;

    @GetMapping("test")
    public List<Activity> test() {
        Activity activity = new Activity();
        activity.setActivityName("活动1").setKey("sfa").setStatus(1);
//        activityMapper.closeAct(1);
        return activityMapper.findAll();
    }
}
