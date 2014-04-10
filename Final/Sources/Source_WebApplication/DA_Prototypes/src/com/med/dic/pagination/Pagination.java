package com.med.dic.pagination;

public class Pagination {

	private int page_size = 0;
	private int page_number = 0;
	private String sortColumn = null;
	private String sortOrder = "ASC";
	private int total_records = 0;
	private int page_records = 0;
	private int start = 0;
	private int end = 0;
	private int total_pages = 0;
	private String sortClass = null;

	public void setPreperties(int count) {
		total_records = count;
		total_pages = (int) Math.ceil(((double) count / (double) page_size));
		start = ((page_size * page_number) - page_size);
		end = page_size;
		if (page_size == 0) {
			start = 0;
			end = count;
			total_pages = 1;
		}
		if (sortOrder.equals("ASC"))
			sortClass = "order1";
		else
			sortClass = "order2";
	}

	public Pagination(int page_size, int page_number) {
		this.page_number = page_number;
		this.page_size = page_size;
	}

	public int getPage_size() {
		return page_size;
	}

	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}

	public int getPage_number() {
		return page_number;
	}

	public void setPage_number(int page_number) {
		this.page_number = page_number;
	}

	public int getTotal_records() {
		return total_records;
	}

	public void setTotal_records(int total_records) {
		this.total_records = total_records;
	}

	public int getPage_records() {
		return page_records;
	}

	public void setPage_records(int page_records) {
		this.page_records = page_records;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getTotal_pages() {
		return total_pages;
	}

	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;
	}

	public String getSortOrder() {
		return (sortOrder == null) ? "" : sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortColumn() {
		return (sortColumn == null) ? "" : sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getSortClass() {
		return sortClass;
	}

	public void setSortClass(String sortClass) {
		this.sortClass = sortClass;
	}

	/**
	 * @param query
	 * @return
	 */
	public String getSQLQuery(String query, boolean hasOrderBy) {
		if (sortColumn != null && sortColumn.length() > 0)
			if(hasOrderBy == true) {
				query = query.substring(0, query.lastIndexOf("ORDER BY"));
				query += " ORDER BY " + sortColumn + " collate utf8_czech_ci " + sortOrder;
			} else {
				query += " ORDER BY " + sortColumn + " collate utf8_czech_ci " + sortOrder;
			}
		query += " LIMIT " + start + ", " + end;
		return query;
	}
}
