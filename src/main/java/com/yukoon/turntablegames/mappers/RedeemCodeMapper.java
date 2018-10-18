package com.yukoon.turntablegames.mappers;

import com.yukoon.turntablegames.entities.RedeemCode;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RedeemCodeMapper {
	@Insert("INSERT INTO redeemcode(redeemCode,reward_id) VALUES (#{redeemCode},#{reward_id})")
	public void addRedeemCode(RedeemCode redeemCode);

	@Select("SELECT * from redeemcode where reward_id = #{reward_id}")
	public List<RedeemCode> findAllByRewardId(Integer reward_id);

	@Select("SELECT * from redeemcode where id = #{id}")
	public RedeemCode findById(Integer id);

	@Update("UPDATE redeemcode SET redeemCode = #{redeemCode},reward_id=#{reward_id},cashing_date = #{cashingDate} WHERE id = #{id}")
	public void updateRedeemCode(RedeemCode redeemCode);

	@Delete("DELETE FROM redeemcode WHERE id = #{id}")
	public void delRedeemCode (Integer id);
}
