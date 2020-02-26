package com.codersknowledge.model;

import java.util.List;

import lombok.Data;

@Data
public class ResponseHelper {

	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private List<String[]> data;
}
