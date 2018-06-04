package com.yukoon.turntablegames.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "pathconfig")
@PropertySource("classpath:pathconfig.yml")
public class PathConfig {

	@Value("${imagespath}")
	private String imagespath;

	public String getImagespath() {
		return imagespath;
	}

	public void setImagespath(String imagespath) {
		this.imagespath = imagespath;
	}
}
