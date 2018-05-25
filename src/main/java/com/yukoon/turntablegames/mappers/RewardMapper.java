package com.yukoon.turntablegames.mappers;


import com.yukoon.turntablegames.entities.Reward;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RewardMapper {

    @Insert("INSERT INTO rewards(rewardName,total,surplus,probability,act_id) VALUES (#{rewardName},#{total},#{surplus},#{probability},#{act_id})")
    public void addReward(Reward reward);
}
