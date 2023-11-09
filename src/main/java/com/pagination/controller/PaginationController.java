package com.pagination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pagination.response.BaseResponse;
import com.pagination.response.ProductResponse;
import com.pagination.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class PaginationController {

	@Autowired
	ProductService productService;
	
	@GetMapping(path = "/", produces = "application/json")
	public ResponseEntity<BaseResponse<ProductResponse>> getAllProducts(){
		return productService.getAllProducts();
	}

	@GetMapping(path = "sort/{field}/{order}", produces = "application/json")
	public ResponseEntity<BaseResponse<ProductResponse>> findProductsWithFieldSorting(@PathVariable String field , @PathVariable String order){
		return productService.findProductsWithFieldSorting(field, order);
	}

	@GetMapping(path = "pagination/{offset}/{pageSize}", produces = "application/json")
	public ResponseEntity<BaseResponse<ProductResponse>> findProductsWithPagination(@PathVariable Integer offset , @PathVariable Integer pageSize){
		return productService.findProductsWithPagination(offset, pageSize);
	}

	@GetMapping(path = "pagination/{offset}/{pageSize}/sort/{field}/{order}", produces = "application/json")
	public ResponseEntity<BaseResponse<ProductResponse>> findProductsWithPaginationAndSorting(@PathVariable Integer offset, @PathVariable Integer pageSize, @PathVariable String field, @PathVariable String order) {
	    return productService.findProductsWithPaginationAndSorting(offset, pageSize, field, order);
	}

	
}
