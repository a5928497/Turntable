package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.entities.Recommender;
import com.yukoon.turntablegames.services.ActivityService;
import com.yukoon.turntablegames.services.AwardInfoService;
import com.yukoon.turntablegames.services.RedeemCodeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Map;

@Controller
public class AwardInfoController {
    @Autowired
    private AwardInfoService awardInfoService;
    @Autowired
    private RedeemCodeService redeemCodeService;
    @Autowired
    private ActivityService activityService;

    //后台查询同一活动下所有中奖情况
    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @GetMapping("/awards/{id}")
    public String findAllByActid(@PathVariable("id")Integer id, Map<String,Object> map) {
        map.put("awardInfos",awardInfoService.findAllByActid(id));
        map.put("act_id","/awards/"+id);
        return "background/awardInfo_list";
    }

    //后台查询某一活动下某一客户中奖情况
    @RequiresRoles("admin")
    @RequiresPermissions("query")
    @GetMapping("/award/{id}")
    public String findAllByUserid(@PathVariable("id")Integer id, Map<String,Object> map) {
        map.put("awardInfos",awardInfoService.findAllByUserid(id));
        map.put("act_id","/award/"+awardInfoService.findActidByUserid(id));
        return "background/awardInfo_list";
    }
    //前台查询某一活动下某一客户获奖情况
    @GetMapping("pbaward/{id}")
    public String pbFindAllByUserid(@PathVariable("id")Integer id, Map<String,Object> map) {
        map.put("awardInfos",awardInfoService.findAllByUserid(id));
        Integer act_id = awardInfoService.findActidByUserid(id);
        map.put("act_id",act_id);
        map.put("act_status",activityService.getStatusById(act_id));
        map.put("user_id",id);
        map.put("cashingInfo",activityService.findCashingInfoById(act_id));
        return "public/awardInfo_list";
    }



}
