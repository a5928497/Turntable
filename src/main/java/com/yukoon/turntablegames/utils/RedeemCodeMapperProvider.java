package com.yukoon.turntablegames.utils;

import com.yukoon.turntablegames.entities.User;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedeemCodeMapperProvider {


    public String insertAll(Map map) {
        List<User> users = (List<User>) map.get("list");
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO redeemcode (redeemCode,reward_id,status) VALUES");
        MessageFormat mf = new MessageFormat("#'{'list[{0}].redeemCode'}',#'{'list[{0}].reward_id'}',0");
        for (int i = 0;i< users.size();i++) {
            sb.append("(");
            //这里如果直接用数字，超过1000会格式化变成1,000，但是String类型就没问题
            sb.append(mf.format(new Object[]{String.valueOf(i)}));
            sb.append(")");

            if (i < users.size() - 1) {
                sb.append(",");
            }
        }
        return  sb.toString();
    }
}
