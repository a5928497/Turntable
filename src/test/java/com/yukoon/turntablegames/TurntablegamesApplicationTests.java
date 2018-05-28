package com.yukoon.turntablegames;

import com.yukoon.turntablegames.entities.AwardInfo;
import com.yukoon.turntablegames.mappers.UsersMapper;
import com.yukoon.turntablegames.services.AwardInfoService;
import com.yukoon.turntablegames.services.DrawService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
	public void contextLoads() {
		System.out.println(awardInfoService.addAwardInfo(usersMapper.findById(2)));
	}

}
