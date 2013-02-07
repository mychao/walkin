package com.checkins.pojo;

import java.util.List;

/**
 * checkins pojo for json convert
 * @author mychao
 *
 */
public class CheckinsPojo {

	private int total;
	private List<?> rows;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
