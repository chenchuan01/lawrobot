package com.sys.base;

import com.SpringContextHolder;
import com.sys.common.Params;

/**
 * 
 * @author chenchuan
 * @date 2016年1月22日 数据对象基类
 */
public class BaseEntity {

	private Integer id;// 主键

	private String delflag = "0";// 删除标志

	private String compflag;// 公司标志

	public BaseEntity() {
		setCompflag(((Params) SpringContextHolder
				.getBean("params")).getComp_flag());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public String getCompflag() {
		return compflag;
	}

	public void setCompflag(String compflag) {
		this.compflag = compflag;
	}
	
	public boolean notNull(){
		return this!=null&&this.getId()!=null;
	}
}
