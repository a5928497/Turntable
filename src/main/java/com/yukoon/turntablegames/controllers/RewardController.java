package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.entities.Reward;
import com.yukoon.turntablegames.services.ActivityService;
import com.yukoon.turntablegames.services.RewardService;
import com.yukoon.turntablegames.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class RewardController {
    @Autowired
    RewardService rewardService;
    @Autowired
    ActivityService activityService;

    @GetMapping("/rewards/{id}")
    public String actRewards(@PathVariable("id") Integer id, Map<String,Object> map) {
        List<Reward> list = rewardService.findByActid(id);
        map.put("rewards",list);
        map.put("act_id",id);
        map.put("act_status",activityService.getStatusById(id));
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

    @GetMapping("/touploadimg/{act_id}")
    public String toUpload(@PathVariable("act_id") Integer act_id, Map<String,Object> map) {
        map.put("act_id",act_id);
        return "background/reward_picture_upload";
    }

    @PostMapping("/imgupload")
    public String upload(@RequestParam("pic")MultipartFile pic, HttpServletRequest request,Integer act_id, Map<String,Object> map){
        String filePath = request.getSession().getServletContext().getRealPath("images/");
        String fileName = "lottery"+act_id;
        map.put("act_id",act_id);
        try {
            FileUtil.uploadFile(pic.getBytes(),filePath,fileName);
        }catch (Exception e) {
            map.put("msg","图片上传出错，请重新上传!");
            return "background/reward_picture_upload";
        }
        map.put("msg","图片上传成功!");
        return "background/reward_picture_upload";
    }
}
