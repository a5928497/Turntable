package com.yukoon.turntablegames.services;

import com.yukoon.turntablegames.entities.User;
import com.yukoon.turntablegames.mappers.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UsersMapper usersMapper;

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
}
