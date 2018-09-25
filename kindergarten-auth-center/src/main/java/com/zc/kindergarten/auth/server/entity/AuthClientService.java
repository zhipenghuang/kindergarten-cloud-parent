package com.zc.kindergarten.auth.server.entity;

import com.zc.kindergarten.common.entity.BaseEntity;
import lombok.Data;
import java.util.Date;
/**
 * @author hzp
 * @create 2018/9/19.
 */
@Data
public class AuthClientService extends BaseEntity {

    private String serviceId;

    private String clientId;

    private String description;

    private String crtUser;

    private String crtName;

    private String crtHost;

}