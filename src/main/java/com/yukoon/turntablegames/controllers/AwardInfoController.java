package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.entities.AwardInof2human;
import com.yukoon.turntablegames.entities.Recommender;
import com.yukoon.turntablegames.services.AwardInfoService;
import com.yukoon.turntablegames.services.RecommenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class AwardInfoController {
    @Autowired
    private AwardInfoService awardInfoService;
    @Autowired
    private RecommenderService recommenderService;


    @GetMapping("/awards/{id}")
    public String findAllByActid(@PathVariable("id")Integer id, Map<String,Object> map) {
        map.put("awardInfos",awardInfoService.findAllByActid(id));
        map.put("act_id","/awards/"+id);
        return "background/awardInfo_list";
    }

    @GetMapping("/award/{id}")
    public String findAllByUserid(@PathVariable("id")Integer id, Map<String,Object> map) {
        map.put("awardInfos",awardInfoService.findAllByUserid(id));
        map.put("act_id","/award/"+awardInfoService.findActidByUserid(id));
        return "background/awardInfo_list";
    }
    //前台查询获奖情况
    @GetMapping("pbaward/{id}")
    public String pbFindAllByUserid(@PathVariable("id")Integer id, Map<String,Object> map) {
        map.put("awardInfos",awardInfoService.findAllByUserid(id));
        map.put("act_id",awardInfoService.findActidByUserid(id));
        return "public/awardInfo_list";
    }

    //后台兑换
    @PutMapping("/award/{id}")
    public String cashAward(@PathVariable("id")Integer id,String act_id) {
        awardInfoService.cashAward(id);
        return "redirect:"+act_id;
    }

//    前台兑换
    @PutMapping("/pbaward/{id}")
    public String pbcashAward(@PathVariable("id")Integer id, Recommender recommender) {
        System.out.println(recommender);
        if(recommenderService.vaildateRecommender(recommender)){
            awardInfoService.cashAward(id);
            return "redirect:"+recommender.getAct_id();
        }
        return null;
    }
}
