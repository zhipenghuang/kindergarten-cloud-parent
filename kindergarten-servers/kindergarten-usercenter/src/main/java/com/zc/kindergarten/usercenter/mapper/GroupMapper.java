package com.zc.kindergarten.usercenter.mapper;

import com.zc.kindergarten.usercenter.entity.Group;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface GroupMapper extends Mapper<Group> {

	void deleteGroupMembersById(@Param("groupId") int groupId);

	void deleteGroupLeadersById(@Param("groupId") int groupId);

	void insertGroupMembersById(@Param("groupId") int groupId, @Param("userId") int userId);

	void insertGroupLeadersById(@Param("groupId") int groupId, @Param("userId") int userId);
}