package com.yukoon.turntablegames.services;

import com.yukoon.turntablegames.entities.Activity;
import com.yukoon.turntablegames.mappers.ActivityMapper;
import com.yukoon.turntablegames.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActivityService {
    //生成key的长度
    private static final int KEY_LENGTH = 4;
    @Autowired
    private ActivityMapper activityMapper;

    @Transactional
    public void addAct(Activity activity) {
        String key = KeyUtil.getKey(KEY_LENGTH);
        while (activityMapper.keyVaildate(key) != null) {
            key = KeyUtil.getKey(KEY_LENGTH);
        }
        activity.setAct_key(key);
        activity.setAct_status(1);
        System.out.println(activity);
        activityMapper.addAct(activity);
    }

    @Transactional
    public void closeAct(Integer id) {
        activityMapper.closeAct(id);
    }

    public List<Activity> findAll() {
        return activityMapper.findAll();
    }
}
