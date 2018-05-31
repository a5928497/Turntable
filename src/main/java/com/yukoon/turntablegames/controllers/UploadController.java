package com.yukoon.turntablegames.controllers;

import com.yukoon.turntablegames.services.ExcelUploadService;
import com.yukoon.turntablegames.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Map;

@Controller
public class UploadController {
    @Autowired
    private ExcelUploadService excelUploadService;

    //前往图片上传
    @GetMapping("/touploadimg/{act_id}")
    public String toUploadImg(@PathVariable("act_id") Integer act_id, Map<String,Object> map, String uploadMsg) {
        if (uploadMsg !=null) {
            System.out.println(uploadMsg);
            map.put("uploadMsg",uploadMsg);
        }
        map.put("act_id",act_id);
        return "background/reward_picture_upload";
    }

    //图片上传
    @PostMapping("/imgupload")
    public String upload(@RequestParam("pic")MultipartFile pic, HttpServletRequest request
            , Integer act_id, ModelMap modelMap){
        String filePath = request.getSession().getServletContext().getRealPath("images/");
        String fileName = pic.getOriginalFilename();
        String uploadMsg = "图片上传成功!";
        if (!FileUtil.isImg(fileName)){
            uploadMsg = "图片上传出现错误,请重新上传!";
            return "redirect:/touploadimg/"+act_id+"?uploadMsg="+uploadMsg;
        }
        //重命名文件
        fileName = "lottery"+act_id+".jpg";
        try {
            FileUtil.uploadFile(pic.getBytes(),filePath,fileName);
        }catch (Exception e) {
            uploadMsg = "图片上传出现错误,请重新上传!";
            return "redirect:/touploadimg/"+act_id+"?uploadMsg="+uploadMsg;
        }
        return "redirect:/touploadimg/"+act_id+"?uploadMsg="+uploadMsg;
    }

    //前往Excel上传
    @GetMapping("/touploadexcel/{act_id}")
    public String toUploadExcel(@PathVariable("act_id") Integer act_id, Map<String,Object> map, String uploadMsg) {
        if (uploadMsg !=null) {
            System.out.println(uploadMsg);
            map.put("uploadMsg",uploadMsg);
        }
        map.put("act_id",act_id);
        return "background/user_excel_upload";
    }

    //Excel上传
    @PostMapping("/excelupload")
    public String uploadExcel(@RequestParam("excel")MultipartFile excel,Integer act_id) {
        try {
            InputStream in = excel.getInputStream();
            excelUploadService.importUserExcel(in,excel,act_id);
            in.close();
        }catch (Exception e){
        	e.printStackTrace();
			System.out.println("失败");
			return "redirect:/touploadexcel/"+act_id;
        }
		System.out.println("成功");
        return "redirect:/touploadexcel/"+act_id;
    }

}
