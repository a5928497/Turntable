package com.yukoon.turntablegames.services;

import com.yukoon.turntablegames.entities.Activity;
import com.yukoon.turntablegames.mappers.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActivityServices {
    @Autowired
    private ActivityMapper activityMapper;

    @Transactional
    public void addAct(Activity activity) {
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
