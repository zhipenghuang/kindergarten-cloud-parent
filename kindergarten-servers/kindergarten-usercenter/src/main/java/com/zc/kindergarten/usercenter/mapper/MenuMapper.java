package com.zc.kindergarten.usercenter.mapper;

import com.zc.kindergarten.usercenter.entity.Menu;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface MenuMapper extends Mapper<Menu> {

	List<Menu> selectMenuByAuthorityId(@Param("authorityId") String authorityId, @Param("authorityType") String authorityType);

	/**
	 * 根据用户和组的权限关系查找用户可访问菜单
	 *
	 * @param userId
	 * @return
	 */
	List<Menu> selectAuthorityMenuByUserId(@Param("userId") Long userId);

	/**
	 * 根据用户和组的权限关系查找用户可访问的系统
	 *
	 * @param userId
	 * @return
	 */
	List<Menu> selectAuthoritySystemByUserId(@Param("userId") Long userId);
}