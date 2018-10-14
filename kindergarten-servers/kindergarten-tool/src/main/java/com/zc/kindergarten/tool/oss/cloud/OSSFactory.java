package com.zc.kindergarten.tool.oss.cloud;

import com.zc.kindergarten.tool.config.CloudStorageConfig;
import com.zc.kindergarten.tool.oss.constants.OSSConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 文件上传Factory
 *
 * @author hzp
 */
@Component
public class OSSFactory {
    @Autowired
    private CloudStorageConfig config;

    public CloudStorageService build() {
        if (config.getType().equals(OSSConstant.TYPE_QINIU)) {
            return new QiniuCloudStorageService(config);
        } else if (config.getType().equals(OSSConstant.TYPE_ALIYUN)) {
            return new AliyunCloudStorageService(config);
        } else if (config.getType().equals(OSSConstant.TYPE_QCLOUD)) {
            return new QcloudCloudStorageService(config);
        }
        return null;
    }

}
