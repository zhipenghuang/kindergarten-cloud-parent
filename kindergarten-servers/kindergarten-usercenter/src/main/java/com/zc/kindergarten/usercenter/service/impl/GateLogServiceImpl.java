package com.zc.kindergarten.usercenter.service.impl;

import com.zc.kindergarten.common.vo.LogInfo;
import com.zc.kindergarten.usercenter.entity.GateLog;
import com.zc.kindergarten.usercenter.mapper.GateLogMapper;
import com.zc.kindergarten.usercenter.service.GateLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GateLogServiceImpl implements GateLogService {

	@Autowired
	private GateLogMapper gateLogMapper;
	@Override
	public void insertSelective(LogInfo info) {
		GateLog log = new GateLog();
		BeanUtils.copyProperties(info,log);
		gateLogMapper.insertSelective(log);
	}
}
