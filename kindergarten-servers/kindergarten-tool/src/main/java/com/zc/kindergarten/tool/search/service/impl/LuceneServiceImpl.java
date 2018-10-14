package com.zc.kindergarten.tool.search.service.impl;

import com.zc.kindergarten.common.msg.ResponseEntity;
import com.zc.kindergarten.common.vo.IndexObject;
import com.zc.kindergarten.tool.search.lucene.LuceneDao;
import com.zc.kindergarten.tool.search.service.LuceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Description:luncene
 *
 * @author ace
 * @create 2017-06-06
 **/
@Service
public class LuceneServiceImpl implements LuceneService {

    @Autowired
    private LuceneDao luceneDao;


    @Override
    public void save(IndexObject indexObject) {
        luceneDao.create(indexObject);
    }


    @Override
    public void update(IndexObject indexObject) {
        luceneDao.update(indexObject);
    }

    @Override
    public void delete(IndexObject indexObject) {
        luceneDao.delete(indexObject);
    }

    @Override
    public void deleteAll() {
        luceneDao.deleteAll();
    }

    @Override
    public ResponseEntity page(Integer pageNumber, Integer pageSize, String keyword) {
        return luceneDao.page(pageNumber,pageSize,keyword);
    }
}
