package com.yukoon.turntablegames.services;

import com.yukoon.turntablegames.entities.AwardInof2human;
import com.yukoon.turntablegames.entities.Excel;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DownloadService {
    @Autowired
    private AwardInfoService awardInfoService;

    public XSSFWorkbook exportExcel(List<AwardInof2human> list){
        List<Excel> excels = new ArrayList<>();
        Map<Integer,List<Excel>> map = new LinkedHashMap<>();
        XSSFWorkbook xssfWorkbook = null;
        //设置标题栏
        excels.add(new Excel("用户ID","id",0));
        excels.add(new Excel("用户名","username",0));
        excels.add(new Excel("活动名","act_name",0));
        excels.add(new Excel("奖品名","reward_name",0));
        excels.add(new Excel("状态","is_Cash",0));
        excels.add(new Excel("中奖时间","winning_date",0));
        excels.add(new Excel("兑换日期","cashing_date",0));
        return null;
    }

    //根据活动导出中奖名单
    public XSSFWorkbook exportExcelByActid(Integer act_id) {
        List<AwardInof2human> list = awardInfoService.findAllByActid(act_id);
        return exportExcel(list);
    }

    //导出具体用户的中间名单
    public XSSFWorkbook exportExcelByUserid(Integer user_id) {
        List<AwardInof2human> list = awardInfoService.findAllByActid(user_id);
        return exportExcel(list);
    }
}
