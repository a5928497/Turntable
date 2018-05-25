package com.yukoon.turntablegames.mappers;

import com.yukoon.turntablegames.entities.Activity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ActivityMapper {

    @Insert("INSERT INTO activities(activityName,act_status,act_key) VALUES(#{activityName},#{status},#{key})")
    public void addAct(Activity activity);

    @Update("UPDATE activities SET act_status = 0 WHERE id = #{id}")
    public void closeAct(Integer id);

    @Select("Select id,activityName FROM activities")
    public List<Activity> findAll();
}
