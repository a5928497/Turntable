package com.yukoon.turntablegames.services;

import com.yukoon.turntablegames.entities.User;
import com.yukoon.turntablegames.mappers.ActivityMapper;
import com.yukoon.turntablegames.mappers.UsersMapper;
import com.yukoon.turntablegames.utils.EncodeUtil;
import com.yukoon.turntablegames.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelUploadService {
	@Autowired
	private ActivityMapper activityMapper;
	@Autowired
	private UsersMapper usersMapper;

	public List<String> importUserExcel(InputStream in, MultipartFile file, Integer act_id) throws Exception {
		List<List<Object>> listob = ExcelUtil.getUserstByExcel(in,file.getOriginalFilename());
		List<User> users  = new ArrayList<>();
		List<String> repeatUser = new ArrayList<>();
		List<User> updateUser = new ArrayList<>();
		for (int i = 0;i<listob.size();i++) {
			boolean flag = false;
			List<Object> ob = listob.get(i);
			User user = new User();
			Integer times = Integer.valueOf(ob.get(1).toString());
			user.setUsername(String.valueOf(ob.get(0)));
			//加密密码
			user.setPassword(EncodeUtil.encodePassword(activityMapper.getKeyByActId(act_id),String.valueOf(ob.get(0))));
			user.setRole_id(1).setAct_id(act_id).setDraw_times(times).setAvailable_draw_times(times);
			//excel表去重
			for(User temp:users) {
				flag = temp.getUsername().equals(user.getUsername());
				if (flag == true) {
					break;
				}
			}
			if (flag == false) {
				users.add(user);
			}else {
				repeatUser.add(user.getUsername());
			}
		}
		//查询数据库更新已存在手机号信息
		for (int i = 0;i<users.size();) {
			User temp = users.get(i);
			Integer temp_id = usersMapper.findIdByActidAndUsername(temp);
			if (temp_id != null) {
				System.out.println("有内容需要覆盖");
				temp.setId(temp_id);
				updateUser.add(temp);
				users.remove(temp);
				}else {
				i++;
			}
		}
		if (updateUser.size() != 0 ) {
			System.out.println("updateusers:"+updateUser);
			for (User temp: updateUser) {
				usersMapper.updateUser(temp);
			}
		}
		if (users.size() !=0) {
			usersMapper.insertAll(users);
		}
		return repeatUser;
	}
}
