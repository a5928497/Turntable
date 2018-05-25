package com.yukoon.turntablegames.services;

import com.yukoon.turntablegames.mappers.RewardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RewardService {
    @Autowired
    private RewardMapper rewardMapper;
}
