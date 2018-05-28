package com.yukoon.turntablegames.services;

import com.yukoon.turntablegames.entities.Reward;
import com.yukoon.turntablegames.mappers.RewardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DrawService {
    private final static int MAX_REWARD_NUMBER = 7;
    @Autowired
    private RewardMapper rewardMapper;

    public Reward randomChoice(Integer act_id){
        List<Reward> list = rewardMapper.getProbabilityByActid(act_id);
        Random random  = new Random();
        int i = random.nextInt(100);
        System.out.println(i);

        if (list.size() == MAX_REWARD_NUMBER) {
            int range = 0;
            for (Reward element:list) {
                int temp = (int)(element.getProbability()*100);
                range = range + temp;
                System.out.println(range);
                if (i < range) {
                    return  element;
                }
            }
        }else {
            int range = 0;
            for (Reward element:list) {
                int temp = (int) (element.getProbability() * 100);
                range = range + temp;
            }
            while (i>=range){
                i = random.nextInt(100);
                System.out.println("new i:" + i);
            }
            range = 0;
            for (Reward element:list) {
                int temp = (int)(element.getProbability()*100);
                range = range + temp;
                System.out.println(range);
                if (i < range) {
                    return  element;
                }
            }
        }
        return null;
    }

    //对randomChoice()得到的reward进行检验保证能返回正确结果
    public Reward getRandomReward(Integer act_id) {
        Reward reward = randomChoice(act_id);
        while (reward == null ) {
            System.out.println("抽取发生问题，将重新抽取");
            reward = randomChoice(act_id);
        }
        if (reward.getSurplus() <1) {
            //没有库存，返回谢谢惠顾
            reward = rewardMapper.thanks();
        }
        return reward;
    }
}
