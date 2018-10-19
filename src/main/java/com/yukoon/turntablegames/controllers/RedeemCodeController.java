package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.entities.AwardInfo;
import com.yukoon.turntablegames.entities.RedeemCode;
import com.yukoon.turntablegames.services.AwardInfoService;
import com.yukoon.turntablegames.services.RedeemCodeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class RedeemCodeController {
	@Autowired
	private RedeemCodeService redeemCodeService;
	@Autowired
	private AwardInfoService awardInfoService;

	@ModelAttribute
	public void getCode(@RequestParam(value = "id",required = false)Integer id,Map<String,Object> map) {
		//若为修改
		if (null != id) {
			RedeemCode redeemCode = redeemCodeService.findById(id);
			map.put("redeemCode",redeemCode);
		}
	}

	@RequiresRoles("admin")
	@RequiresPermissions("query")
	@GetMapping("/codes/{reward_id}")
	public String listCodesByRewardId(@PathVariable("reward_id")Integer reward_id, Map<String,Object> map){
		map.put("codes",redeemCodeService.findAllCodesByRewardId(reward_id));
		map.put("reward_id",reward_id);
		return "background/redeem_code_list";
	}

	@RequiresRoles("admin")
	@RequiresPermissions("query")
	@GetMapping("/code/{reward_id}")
	public String toAddCode(@PathVariable("reward_id")Integer reward_id, Map<String,Object> map) {
		map.put("reward_id",reward_id);
		return "background/redeem_code_input";
	}

	@RequiresRoles("admin")
	@RequiresPermissions("query")
	@PostMapping("/code")
	public String addCode(RedeemCode code) {
		redeemCodeService.add(code);
		return "redirect:/codes/" + code.getReward_id();
	}

	@RequiresRoles("admin")
	@RequiresPermissions("query")
	@GetMapping("/editcode/{id}")
	public String toEditCode(@PathVariable("id")Integer id, Map<String,Object> map) {
		map.put("code",redeemCodeService.findById(id));
		return "background/redeem_code_input";
	}

	@RequiresRoles("admin")
	@RequiresPermissions("query")
	@PutMapping("/code")
	public String editCode(RedeemCode redeemCode) {
		redeemCodeService.update(redeemCode);
		return "redirect:/codes/" + redeemCode.getReward_id();
	}

	@RequiresRoles("admin")
	@RequiresPermissions("query")
	@DeleteMapping("/code/{id}")
	public String delCode(@PathVariable("id")Integer id,Integer reward_id) {
		redeemCodeService.delete(id);
		return "redirect:/codes/" + reward_id;
	}

	//前台查询兑换码
	@ResponseBody
	@GetMapping("/getcode/{awardInfoId}")
	public String pbGetcode(@PathVariable("awardInfoId")Integer awardInfoId) {
		AwardInfo awardInfo = awardInfoService.findById(awardInfoId);
		return redeemCodeService.findById(awardInfo.getCode_id()).getRedeemCode();
	}
}
