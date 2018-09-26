package com.zc.kindergarten.auth.server.service;

import com.zc.kindergarten.auth.server.entity.AuthClient;
import java.util.List;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public interface ClientAndServiceService {

    void modifyClientServices(int id, String clients);

    List<AuthClient> getClients(int id);
}
