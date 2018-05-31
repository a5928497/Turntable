package com.yukoon.turntablegames.config;

import com.yukoon.turntablegames.utils.UserMapperProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {

    @Bean
    public UserMapperProvider getUserMapperProvider() {
        return new UserMapperProvider();
    }
}
