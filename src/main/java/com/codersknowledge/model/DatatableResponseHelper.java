package com.codersknowledge.model;

import java.util.List;

import lombok.Data;

/**
 * 
 * @author Zubayer Ahamed
 *
 */
@Data
public class DatatableResponseHelper<T> {

	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private List<T> data;
}
