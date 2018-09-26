package com.zc.kindergarten.auth.server.service.impl;

import com.zc.kindergarten.auth.server.entity.AuthClient;
import com.zc.kindergarten.auth.server.entity.ClientAndService;
import com.zc.kindergarten.auth.server.mapper.AuthClientMapper;
import com.zc.kindergarten.auth.server.mapper.ClientAndServiceMapper;
import com.zc.kindergarten.auth.server.service.ClientAndServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
/**
 * @author hzp
 * @create 2018/9/19.
 */
@Service
public class ClientAndServiceServiceImpl implements ClientAndServiceService {

    @Autowired
    private ClientAndServiceMapper clientAndServiceMapper;
    @Autowired
    private AuthClientMapper authClientMapper;

    @Override
    public void modifyClientServices(int id, String clients) {
        clientAndServiceMapper.deleteByServiceId(id);
        if (!StringUtils.isEmpty(clients)) {
            String[] mem = clients.split(",");
            for (String m : mem) {
                ClientAndService clientService = new ClientAndService();
                clientService.setServiceId(m);
                clientService.setClientId(id + "");
                clientAndServiceMapper.insertSelective(clientService);
            }
        }
    }

    @Override
    public List<AuthClient> getClients(int id) {
        return authClientMapper.selectAuthorityServiceInfo(id);
    }
}
