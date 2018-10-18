package com.yukoon.turntablegames.mappers;

import com.yukoon.turntablegames.entities.RedeemCode;
import com.yukoon.turntablegames.utils.RedeemCodeMapperProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RedeemCodeMapper {
	@Insert("INSERT INTO redeemcode(redeemCode,reward_id,status) VALUES (#{redeemCode},#{reward_id},0)")
	public void addRedeemCode(RedeemCode redeemCode);

	@Select("SELECT * from redeemcode where reward_id = #{reward_id}")
	public List<RedeemCode> findAllByRewardId(Integer reward_id);

	@Select("SELECT * from redeemcode where id = #{id}")
	public RedeemCode findById(Integer id);

	@Select("SELECT * from redeemcode where redeemCode = #{redeemCode} AND reward_id = #{reward_id}")
	public RedeemCode findByRedeemCodeAndRewardId(RedeemCode redeemCode);

	@Update("UPDATE redeemcode SET redeemCode = #{redeemCode},reward_id=#{reward_id},status = #{status} WHERE id = #{id}")
	public void updateRedeemCode(RedeemCode redeemCode);

	@Delete("DELETE FROM redeemcode WHERE id = #{id}")
	public void delRedeemCode (Integer id);

	//批量插入
	@InsertProvider(type = RedeemCodeMapperProvider.class,method = "insertAll")
	public void insertAll(@Param("list")List<RedeemCode> redeemCodes);
}
