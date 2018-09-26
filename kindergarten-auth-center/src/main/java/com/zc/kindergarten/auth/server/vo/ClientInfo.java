package com.zc.kindergarten.auth.server.vo;

import com.zc.kindergarten.common.utils.jwt.IjwtInfo;
import lombok.Data;
/**
 * @author hzp
 * @create 2018/9/19.
 */
@Data
public class ClientInfo implements IjwtInfo {

    String clientId;
    String name;
    String id;

    public ClientInfo(String clientId, String name, String id) {
        this.clientId = clientId;
        this.name = name;
        this.id = id;
    }

    @Override
    public String getUniqueName() {
        return clientId;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
