package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.entities.Reward;
import com.yukoon.turntablegames.mappers.RewardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RewardController {
    @Autowired
    RewardMapper rewardMapper;

    @GetMapping("/test1")
    public String test() {
        Reward reward = new Reward().setRewardName("reward").setProbability(0.33F).setTotal(5).setSurplus(5).setAct_id(1);
        rewardMapper.addReward(reward);
        return "111";
    }
}
