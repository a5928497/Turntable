package com.yukoon.turntablegames.services;

import com.yukoon.turntablegames.entities.Reward;
import com.yukoon.turntablegames.mappers.RewardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DrawService {
    private final static int MAX_REWARD_NUMBER = 8;
    @Autowired
    private RewardMapper rewardMapper;

    public Reward randomChoice(Integer id){
        List<Reward> list = rewardMapper.getProbabilityByActid(id);
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

}
