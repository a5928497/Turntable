package com.yukoon.turntablegames.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class RedeemCode {
	private Integer id;
	private String redeemCode;
	private Integer reward_id;
	private Integer status;
	private Integer user_id;

}
