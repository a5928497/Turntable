package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.entities.AwardInof2human;
import com.yukoon.turntablegames.services.AwardInfoService;
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

    @PutMapping("/award/{id}")
    public String cashAward(@PathVariable("id")Integer id,String act_id) {
        awardInfoService.cashAward(id);
        return "redirect:"+act_id;
    }
}
