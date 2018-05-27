package com.yukoon.turntablegames.mappers;

import com.yukoon.turntablegames.entities.AwardInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AwardInfoMapper {
    @Select("SELECT * FROM AwardInfo WHERE act_id = #{act_id}")
    public List<AwardInfo> findAllByActid(Integer act_id);
}
