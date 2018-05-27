package com.yukoon.turntablegames.services;

import com.yukoon.turntablegames.entities.User;
import com.yukoon.turntablegames.mappers.ActivityMapper;
import com.yukoon.turntablegames.mappers.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private ActivityMapper activityMapper;

    @Transactional
    public User login(User user) {
        User user_temp = usersMapper.login(user);
        if (user_temp != null && user.getPassword().equals(user_temp.getPassword())) {
            System.out.println(user_temp);
            return user_temp;
        }
        return null;
    }

    @Transactional
    public List<User> findAllByActID(Integer id) {
        return usersMapper.findAllByActId(id);
    }

    @Transactional
    public User findById(Integer id) {
        return usersMapper.findById(id);
    }

    @Transactional
    public void updateUser(User user) {
        usersMapper.updateUser(user);
    }
    @Transactional
    public void addUser(User user) {
        user.setPassword(activityMapper.getKeyByActId(user.getAct_id())).setRole_id(1);
        usersMapper.addUser(user);
    }

    public List<User> findByUsernameAndActid(User user) {
        user.setUsername("%"+user.getUsername()+"%");
        return usersMapper.findByUsernameAndActid(user);
    }

    @Transactional
    public void delUser(Integer id) {
        usersMapper.delUser(id);
    }
}
