package com.codersknowledge.model;

import java.util.List;

import com.codersknowledge.entity.Student;

import lombok.Data;

@Data
public class ServersidePaginationResponse {

	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private List<String[]> data;
}
