package com.codersknowledge.model;

import java.util.List;

import lombok.Data;

@Data
public class RequestHelper {
	private int draw;
	private int start;
	private int length;
	//private String[] search;
	//private String[][] order;
	//private List<Columns> columns;
	//private String[][] columns;
}

@Data
class Columns{
	private String data;
	private String name;
	private boolean searchable;
	private boolean orderable;
}