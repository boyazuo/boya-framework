package com.github.boyaframework.core.persistence;

import java.io.Serializable;

/**
 * 通用实体基类
 * 
 * @author Boya
 *
 */
@SuppressWarnings("serial")
public class IdEntity implements Serializable {
	private Long id;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
