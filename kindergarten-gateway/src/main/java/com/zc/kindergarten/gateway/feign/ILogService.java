package com.zc.kindergarten.gateway.feign;

import com.zc.kindergarten.common.vo.LogInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ${DESCRIPTION}
 *
 * @author hzp
 * @create 2017-10-10 15:16
 */
@FeignClient("kindergarten-usercenter")
public interface ILogService {
    /**
     *   保存日志
     * @param info
     */
    @RequestMapping(value = "/gateLog/log/save", method = RequestMethod.POST)
    void saveLog(LogInfo info);
}
