package com.pagination.response;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

	private Integer status;
	private String message;
	private Integer recordCount;
	T data;
	private Long totalRecord;
	private List<T> dataList;
	
}
