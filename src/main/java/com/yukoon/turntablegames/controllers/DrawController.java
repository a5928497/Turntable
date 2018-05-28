package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.entities.Reward;
import com.yukoon.turntablegames.entities.User;
import com.yukoon.turntablegames.services.AwardInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class DrawController {
    @Autowired
    private AwardInfoService awardInfoService;

    @ResponseBody
    @PostMapping("/draw")
    public Reward getDrawResult(User user, Map<String,Object> map) {
        map.put("act_id",user.getAct_id());
        return awardInfoService.addAwardInfo(user);
    }
}
