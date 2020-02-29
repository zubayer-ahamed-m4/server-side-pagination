package com.codersknowledge.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DatatableColumnDefination {
	private String data;
	private String name;
	private boolean searchable;
	private boolean orderable;
	private String searchValue;
	private boolean searchRegex;
}
