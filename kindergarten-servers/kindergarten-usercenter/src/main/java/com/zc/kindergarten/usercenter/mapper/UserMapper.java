package com.zc.kindergarten.usercenter.mapper;

import com.zc.kindergarten.usercenter.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {

	List<User> selectMemberByGroupId(@Param("groupId") int groupId);

	List<User> selectLeaderByGroupId(@Param("groupId") int groupId);
}