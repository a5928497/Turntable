package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.entities.Reward;
import com.yukoon.turntablegames.entities.User;
import com.yukoon.turntablegames.services.AwardInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class DrawController {
    @Autowired
    private AwardInfoService awardInfoService;

    public Reward getDrawResult(User user, Map<String,Object> map) {
        return null;
    }
}
