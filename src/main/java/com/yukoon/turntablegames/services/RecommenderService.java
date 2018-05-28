package com.yukoon.turntablegames.services;

import com.yukoon.turntablegames.entities.Recommender;
import com.yukoon.turntablegames.mappers.RecommenderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommenderService {
    @Autowired
    private RecommenderMapper recommenderMapper;

    public boolean vaildateRecommender(Recommender recommender) {
        boolean flag = false;
        List<Recommender> list = recommenderMapper.findRecommenderByActid(recommender.getAct_id());
        for (Recommender element:list) {
            if (recommender.getRecommender_id().equals(element.getRecommender_id())){
                flag = true;
                return flag;
            }
        }
        return flag;
    }
}
