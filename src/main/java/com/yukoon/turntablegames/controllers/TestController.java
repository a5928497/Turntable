package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.utils.EncodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TestController {
    private String corpno = "003211";
    private String corpkey = "5bj3dqvl" ;
    private String param = "UniqueUserID";
    private String url  = "tplogins://cmb.yukoon.com/callback";
    private String auth_url = url + "?param=" + param + "&corpno=" + corpno + corpkey;

    @GetMapping("/totest")
    public String toTest() {
        return "test/test";
    }

    @ResponseBody
    @GetMapping("/test")
    public String test() {
        String auth = EncodeUtil.MD5(auth_url);
        String result = url + "?param=" + param + "&corpno=" + corpno + "&auth=" + auth ;
        return result;
    }

    @ResponseBody
    @PostMapping("/callback")
    public String callback(@RequestParam(name = "param",required = false)String param) {
        if (null != param) {
            return param;
        }
        return "出现错误";
    }

}
