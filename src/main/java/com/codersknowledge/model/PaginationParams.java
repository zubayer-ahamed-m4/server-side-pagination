package com.codersknowledge.model;

import lombok.Data;

@Data
public class PaginationParams {
	private int draw;
	private int start;
	private int length;
}
