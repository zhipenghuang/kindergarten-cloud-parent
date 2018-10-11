package com.zc.kindergarten.usercenter.mapper;

import com.zc.kindergarten.usercenter.entity.Element;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface ElementMapper extends Mapper<Element> {

	List<Element> selectAuthorityElementByUserId(@Param("userId") String userId);

	List<Element> selectAuthorityMenuElementByUserId(@Param("userId") String userId, @Param("menuId") String menuId);

	List<Element> selectAuthorityElementByClientId(@Param("clientId") String clientId);

	List<Element> selectAllElementPermissions();
}