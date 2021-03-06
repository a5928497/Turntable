package com.yukoon.turntablegames.services;

import com.yukoon.turntablegames.entities.*;
import com.yukoon.turntablegames.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AwardInfoService {
    @Autowired
    private AwardInfoMapper awardInfoMapper;
    @Autowired
    private RewardMapper rewardMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private DrawService drawService;
    @Autowired
    private RedeemCodeMapper redeemCodeMapper;

    public List<AwardInof2human> convert(List<AwardInfo> awardList) {
        List<AwardInof2human> list = new ArrayList<>();
        for (AwardInfo element:awardList) {
            AwardInof2human awardInof2human = new AwardInof2human();
            awardInof2human.setId(element.getId());
            awardInof2human.setIs_Cash(element.getIs_Cash());
            awardInof2human.setWinning_date(element.getWinning_date());
            awardInof2human.setCashing_date(element.getCashing_date());
            awardInof2human.setUsername(usersMapper.findUsernameById(element.getUser_id()));
            awardInof2human.setAct_name(activityMapper.findById(element.getAct_id()));
            awardInof2human.setReward_name(rewardMapper.findRewardnameById(element.getReward_id()));
            list.add(awardInof2human);
        }
        return  list;
    }

    public List<AwardInof2human> findAllByActid(Integer id) {
        List<AwardInfo> list = awardInfoMapper.findAllByActid(id);
        return convert(list);
    }

    public List<AwardInof2human> findAllByUserid(Integer id) {
        List<AwardInfo> list = awardInfoMapper.findAllByUserid(id);
        return convert(list);
    }

    public AwardInfo findById(Integer id) {
        return awardInfoMapper.findById(id);
    }

    public Integer findActidByUserid(Integer id) {
        return usersMapper.findActidById(id);
    }

    public void cashAward(Integer id) {

        AwardInfo awardInfo = awardInfoMapper.findById(id);
        awardInfo.setIs_Cash(1).setCashing_date(new Date());
        awardInfoMapper.cashAward(awardInfo);
    }

    @Transactional
    public Reward addAwardInfo(User user) {
        Reward reward = drawService.getRandomReward(user.getAct_id());
        if (reward != null) {
            //若抽到谢谢惠顾
            if (reward.getRewardName().equals("thanks")){
                //扣减抽奖次数
                User user_temp = new User().setId(user.getId()).setAvailable_draw_times(user.getAvailable_draw_times()-1);
                usersMapper.minusAvailableDrawTimes(user_temp);
            }else {
                //若不是谢谢惠顾
                //扣减抽奖次数
                User user_temp = new User().setId(user.getId()).setAvailable_draw_times(user.getAvailable_draw_times()-1);
                //扣减奖品
                Reward reward_temp = new Reward().setId(reward.getId()).setSurplus(reward.getSurplus()-1);
                //发放兑换码
                RedeemCode redeemCode = redeemCodeMapper.findAvailableByRewardId(reward.getId()).get(0);
                redeemCode.setUser_id(user_temp.getId());
                //添加得奖信息
                Date date = new Date();
                AwardInfo awardInfo = new AwardInfo().setUser_id(user.getId()).setAct_id(reward.getAct_id())
                        .setReward_id(reward.getId()).setIs_Cash(1).setWinning_date(date).setCashing_date(date)
                        .setCode_id(redeemCode.getId());
                redeemCodeMapper.cashRedeemCode(redeemCode);
                usersMapper.minusAvailableDrawTimes(user_temp);
                rewardMapper.minusSurplus(reward_temp);
                awardInfoMapper.addAwardInfo(awardInfo);
            }
        }
        return reward;
    }
}
