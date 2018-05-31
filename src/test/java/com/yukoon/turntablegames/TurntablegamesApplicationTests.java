package com.yukoon.turntablegames;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yukoon.turntablegames.entities.User;
import com.yukoon.turntablegames.mappers.UsersMapper;
import com.yukoon.turntablegames.services.AwardInfoService;
import com.yukoon.turntablegames.services.DrawService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TurntablegamesApplicationTests {
	@Autowired
	private DrawService drawService;
	@Autowired
	private AwardInfoService awardInfoService;
	@Autowired
	private UsersMapper usersMapper;

	@Test
	public void contextLoads() throws JsonProcessingException {
		User u1 = new User().setUsername("feili1").setPassword("123").setRole_id(1).setAct_id(2).setDraw_times(5).setAvailable_draw_times(5);
		User u2 = new User().setUsername("feili2").setPassword("123").setRole_id(1).setAct_id(2).setDraw_times(5).setAvailable_draw_times(5);
		List<User> users = new ArrayList<>();
		users.add(u1);
		users.add(u2);
		usersMapper.insertAll(users);
	}

}
