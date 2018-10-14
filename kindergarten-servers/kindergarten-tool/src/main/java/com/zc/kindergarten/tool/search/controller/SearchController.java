package com.zc.kindergarten.tool.search.controller;

import com.zc.kindergarten.common.msg.ResponseEntity;
import com.zc.kindergarten.common.vo.IndexObject;
import com.zc.kindergarten.tool.search.service.LuceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hzp
 * @create 2018/10/14.
 */
@RestController
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private LuceneService luceneService;

	@RequestMapping(value = "/w/{word}", method = RequestMethod.GET)
	public ResponseEntity<IndexObject> search(@PathVariable String word, @RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "15") Integer pageSize) {
		return luceneService.page(pageNumber, pageSize, word);
	}

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public ResponseEntity createIndexObject(@RequestBody IndexObject indexObject) {
		luceneService.save(indexObject);
		return new ResponseEntity();
	}

	@RequestMapping(value = "/index", method = RequestMethod.DELETE)
	public ResponseEntity removeIndexObject(@RequestBody IndexObject indexObject) {
		luceneService.delete(indexObject);
		return new ResponseEntity();
	}

	@RequestMapping(value = "/index", method = RequestMethod.PATCH)
	public ResponseEntity batchCreateIndexObject(@RequestBody List<IndexObject> indexObjects) {
		for (IndexObject object : indexObjects) {
			luceneService.save(object);
		}
		return new ResponseEntity();
	}

	@RequestMapping(value = "/index", method = RequestMethod.PUT)
	public ResponseEntity updateIndexObject(@RequestBody IndexObject indexObject) {
		luceneService.update(indexObject);
		return new ResponseEntity();
	}

}
