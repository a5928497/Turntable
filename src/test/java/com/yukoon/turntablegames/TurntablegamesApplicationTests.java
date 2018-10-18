package com.yukoon.turntablegames;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yukoon.turntablegames.entities.RedeemCode;
import com.yukoon.turntablegames.entities.User;
import com.yukoon.turntablegames.mappers.RedeemCodeMapper;
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
	private RedeemCodeMapper redeemCodeMapper;
	@Test
	public void contextLoads() throws JsonProcessingException {
		RedeemCode redeemCode = new RedeemCode();
		redeemCode.setId(1).setUser_id(4055);
		redeemCodeMapper.cashRedeemCode(redeemCode);
	}

}
