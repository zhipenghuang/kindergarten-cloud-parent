/*
 *
 *  *  Copyright (C) 2018  Wanghaobin<463540703@qq.com>
 *
 *  *  AG-Enterprise 企业版源码
 *  *  郑重声明:
 *  *  如果你从其他途径获取到，请告知老A传播人，奖励1000。
 *  *  老A将追究授予人和传播人的法律责任!
 *
 *  *  This program is free software; you can redistribute it and/or modify
 *  *  it under the terms of the GNU General Public License as published by
 *  *  the Free Software Foundation; either version 2 of the License, or
 *  *  (at your option) any later version.
 *
 *  *  This program is distributed in the hope that it will be useful,
 *  *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  *  GNU General Public License for more details.
 *
 *  *  You should have received a copy of the GNU General Public License along
 *  *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 */

package com.zc.kindergarten.common.vo;


import lombok.Data;

import java.io.Serializable;

/**
 * Description:索引对象
 *
 * @author ace
 **/
@Data
public class IndexObject implements Comparable<IndexObject>,Serializable{

	private static final long serialVersionUID = -2579883977130156331L;

	private Long id;

	private String title;

	private String keywords;

	private String descripton;

	private String postDate;

	private String url;

	/*相似度*/
	private float score;

	public IndexObject() {
		super();
	}

	public IndexObject(Long _id, String _keywords, String _descripton, String _postDate, float _score) {
		super();
		this.id = _id;
		this.keywords = _keywords;
		this.score = _score;
		this.descripton=_descripton;
		this.postDate=_postDate;
	}
	@Override
	public int compareTo(IndexObject o) {
		if(this.score < o.getScore()){
			return 1;
		}else if(this.score > o.getScore()){
			return -1;
		}
		return 0;
	}
}
