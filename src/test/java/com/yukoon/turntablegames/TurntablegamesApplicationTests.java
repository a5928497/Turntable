package com.yukoon.turntablegames;

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

	@Test
	public void contextLoads() {
		System.out.println(drawService.randomChoice(2));
	}

}
