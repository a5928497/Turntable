package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.entities.Reward;
import com.yukoon.turntablegames.services.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class RewardController {
    @Autowired
    RewardService rewardService;

    @GetMapping("/rewards/{id}")
    public String actRewards(@PathVariable("id") Integer id, Map<String,Object> map) {
        List<Reward> list = rewardService.findByActid(id);
        System.out.println(list);
        map.put("rewards",list);
        map.put("act_id",id);
        return "background/reward_list";
    }

    @DeleteMapping("/reward/{id}")
    public String delRewards(@PathVariable("id") Integer id,Map<String,Object> map,Integer act_id) {
        rewardService.delReward(id);
        List<Reward> list = rewardService.findByActid(act_id);
        map.put("rewards",list);
        return "background/reward_list";
    }

    @GetMapping("/reward/{id}")
    public String rewardToEdit(@PathVariable("id") Integer id, Map<String,Object> map) {
        Reward reward  = rewardService.findById(id);
        map.put("reward",reward);
        return "background/reward_input";
    }

    @PutMapping("/reward")
    public String rewardEdit(Reward reward) {
        rewardService.updateReward(reward);
        return "redirect:/rewards/"+reward.getAct_id();
    }

    @GetMapping("/rewardToAdd/{id}")
    public String rewardToAdd(@PathVariable("id") Integer id, Map<String,Object> map) {
        map.put("act_id",id);
        return "background/reward_input";
    }

    @PostMapping("/reward")
    public String rewardAdd(Reward reward) {
        System.out.println(reward);
        rewardService.addReward(reward);
        return "redirect:/rewards/"+reward.getAct_id();
    }
}
