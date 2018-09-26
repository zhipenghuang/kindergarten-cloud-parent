package com.zc.kindergarten.auth.server.mapper;

import com.zc.kindergarten.auth.server.entity.ClientAndService;
import tk.mybatis.mapper.common.Mapper;
/**
 * @author hzp
 * @create 2018/9/19.
 */
public interface ClientAndServiceMapper extends Mapper<ClientAndService> {

    void deleteByServiceId(int id);
}
