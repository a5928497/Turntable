package com.yukoon.turntablegames.services;

import com.yukoon.turntablegames.entities.AwardInfo;
import com.yukoon.turntablegames.entities.AwardInof2human;
import com.yukoon.turntablegames.mappers.ActivityMapper;
import com.yukoon.turntablegames.mappers.AwardInfoMapper;
import com.yukoon.turntablegames.mappers.RewardMapper;
import com.yukoon.turntablegames.mappers.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public List<AwardInof2human> findAllByActid(Integer id) {
        List<AwardInfo> list_temp = awardInfoMapper.findAllByActid(id);
        List<AwardInof2human> list = new ArrayList<>();
        for (AwardInfo element:list_temp) {
            AwardInof2human awardInof2human = new AwardInof2human();
            awardInof2human.setId(element.getId()).setIs_Cash(element.getIs_Cash())
                    .setWinning_date(element.getWinning_date()).setCashing_date(element.getCashing_date())
                    .setUsername(usersMapper.findUsernameById(element.getUser_id()))
                    .setAct_name(activityMapper.findById(element.getAct_id()))
                    .setReward_name(rewardMapper.findRewardnameById(element.getReward_id()));
            list.add(awardInof2human);
        }
        return  list;
    }

    public List<AwardInof2human> findAllByUserid(Integer id) {
        List<AwardInfo> list_temp = awardInfoMapper.findAllByUserid(id);
        List<AwardInof2human> list = new ArrayList<>();
        for (AwardInfo element:list_temp) {
            AwardInof2human awardInof2human = new AwardInof2human();
            awardInof2human.setId(element.getId()).setIs_Cash(element.getIs_Cash())
                    .setWinning_date(element.getWinning_date()).setCashing_date(element.getCashing_date())
                    .setUsername(usersMapper.findUsernameById(element.getUser_id()))
                    .setAct_name(activityMapper.findById(element.getAct_id()))
                    .setReward_name(rewardMapper.findRewardnameById(element.getReward_id()));
            list.add(awardInof2human);
        }
        return  list;
    }

    public Integer findActidByUserid(Integer id) {
        return usersMapper.findActidById(id);
    }

    public void cashAward(Integer id) {

        AwardInfo awardInfo = awardInfoMapper.findById(id);
        awardInfo.setIs_Cash(1).setCashing_date(new Date());
        awardInfoMapper.cashAward(awardInfo);
    }
}
