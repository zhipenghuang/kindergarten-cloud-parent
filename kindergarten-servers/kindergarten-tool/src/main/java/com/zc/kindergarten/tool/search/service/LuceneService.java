package com.zc.kindergarten.tool.search.service;

import com.zc.kindergarten.common.msg.ResponseEntity;
import com.zc.kindergarten.common.vo.IndexObject;

/**
 * lucense 接口
 * @author ace
 */
public interface LuceneService {

    void save(IndexObject indexObject);

    void update(IndexObject indexObject);

    void delete(IndexObject indexObject);

    void deleteAll();

	ResponseEntity page(Integer pageNumber, Integer pageSize, String keyword);
}
