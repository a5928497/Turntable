package com.yukoon.turntablegames.services;

import com.yukoon.turntablegames.entities.User;
import com.yukoon.turntablegames.mappers.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UsersMapper usersMapper;

    @Transactional
    public User login(User user) {
        User user_temp = usersMapper.login(user);
        if (user_temp == null) {

        }
        if (user.getPasssword() == user_temp.getPasssword()) {
            return user_temp;
        }
        return null;
    }
}
