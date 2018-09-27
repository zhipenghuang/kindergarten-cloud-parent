package com.zc.kindergarten.usercenter.controller;

import com.zc.kindergarten.common.vo.LogInfo;
import com.zc.kindergarten.usercenter.entity.GateLog;
import com.zc.kindergarten.usercenter.service.GateLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ${DESCRIPTION}
 *
 * @author hzp
 * @create 2017-07-01 20:32
 */
@Controller
@RequestMapping("gateLog")
public class GateLogController {
	@Autowired
	private GateLogService gateLogService;


//    @RequestMapping(value = "/page",method = RequestMethod.GET)
//    @ResponseBody
//    public TableResultResponse<GateLog> page(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "1")int offset, String name){
//        Example example = new Example(GateLog.class);
//        if(StringUtils.isNotBlank(name)) {
//            example.createCriteria().andLike("menu", "%" + name + "%");
//        }
//        int count = baseBiz.selectCountByExample(example);
//        PageHelper.startPage(offset, limit);
//        return new TableResultResponse<GateLog>(count,baseBiz.selectByExample(example));
//    }

	@RequestMapping(value="/log/save",method = RequestMethod.POST)
	public @ResponseBody void saveLog(@RequestBody LogInfo info){
		GateLog log = new GateLog();
		BeanUtils.copyProperties(info,log);
		gateLogService.insertSelective(info);
	}
}
