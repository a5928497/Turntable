package com.yukoon.turntablegames.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Reward {
    private Integer id;
    private String rewardName;
    private Integer total;
    private Integer surplus;
    private float probability;
    private Integer act_id;
}
