package com.yukoon.turntablegames.mappers;

import com.yukoon.turntablegames.entities.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UsersMapper {
    @Insert("INSERT INTO users(username,password,role_id,act_id,draw_times,available_draw_times) VALUES(#{username}," +
            "#{password},#{role_id},#{act_id},#{draw_times},#{draw_times})")
    public void addUser(User user);

    @Select("SELECT username,password,role_id,act_id,draw_times,available_draw_times FROM users WHERE username = #{username}")
    public User login(User user);

    @Select("SELECT id,username,draw_times,available_draw_times FROM users WHERE act_id =#{act_id}")
    public List<User> findAllByActId(Integer act_id);

    @Delete("DELETE FROM users WHERE id = #{id}")
    public void delUser(Integer id);

}
