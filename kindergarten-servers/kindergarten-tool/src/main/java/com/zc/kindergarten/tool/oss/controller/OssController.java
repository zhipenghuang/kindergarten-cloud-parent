package com.zc.kindergarten.tool.oss.controller;

import com.zc.kindergarten.common.error.UploadErrors;
import com.zc.kindergarten.common.exception.BaseException;
import com.zc.kindergarten.common.msg.ResponseEntity;
import com.zc.kindergarten.tool.oss.cloud.OSSFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * 文件上传
 *
 * @author ace
 */
@RestController
@RequestMapping("/oss")
public class OssController {
	@Autowired
	private OSSFactory ossFactory;

	/**
	 * 上传文件
	 */
	@RequestMapping("/upload")
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			throw new BaseException(UploadErrors.FILE_DOESNOT_NULL);
		}
		//上传文件
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String url = ossFactory.build().uploadSuffix(file.getBytes(), suffix);
		return new ResponseEntity(url);
	}


}
