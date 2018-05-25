package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.entities.Reward;
import com.yukoon.turntablegames.services.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RewardController {
    @Autowired
    RewardService rewardService;

    @GetMapping("/test1")
    public String test() {
        Reward reward = new Reward().setRewardName("123").setTotal(5).setSurplus(5).setProbability(0.32F).setAct_id(1);
        rewardService.addReward(reward);
        return  reward.toString();
    }
}
