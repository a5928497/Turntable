package com.yukoon.turntablegames.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class AwardInof2human {
    private Integer id;
    private String username;
    private String act_name;
    private String reward_name;
    private Integer isCash;
    private Date winning_date;
    private Date cashing_date;
}
