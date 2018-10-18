package com.yukoon.turntablegames.services;

import com.yukoon.turntablegames.entities.RedeemCode;
import com.yukoon.turntablegames.mappers.RedeemCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RedeemCodeService {
	@Autowired
	private RedeemCodeMapper redeemCodeMapper;

	public List<RedeemCode> findAllCodesByRewardId(Integer reward_id) {
		return redeemCodeMapper.findAllByRewardId(reward_id);
	}

	public RedeemCode findById(Integer id) {
		return redeemCodeMapper.findById(id);
	}

	@Transactional
	public void add(RedeemCode redeemCode) {
		redeemCodeMapper.addRedeemCode(redeemCode);
	}

	@Transactional
	public void update(RedeemCode redeemCode) {
		redeemCodeMapper.updateRedeemCode(redeemCode);
	}

	@Transactional
	public void delete(Integer id) {
		redeemCodeMapper.delRedeemCode(id);
	}

//	@Transactional
//	public void cash(Integer id) {
//		redeemCodeMapper.cashRedeemCode(id);
//	}
}
