package com.zc.kindergarten.common.utils.jwt;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public interface IjwtInfo {
    /**
     * 获取用户名
     *
     * @return
     */
    String getUniqueName();

    /**
     * 获取用户ID
     *
     * @return
     */
    String getId();

    /**
     * 获取名称
     *
     * @return
     */
    String getName();
}
