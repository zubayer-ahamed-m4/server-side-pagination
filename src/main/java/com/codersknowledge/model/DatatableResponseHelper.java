package com.codersknowledge.model;

import java.util.List;

import lombok.Data;

@Data
public class DatatableResponseHelper {

	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private List<String[]> data;
}
