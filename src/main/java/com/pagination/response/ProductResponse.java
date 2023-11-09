package com.pagination.response;


import java.util.List;
import java.util.stream.Collectors;

import com.pagination.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

	private Long id;
	
	private String name;
	
	private Integer quantity;
	
	private Double price;
	
	
	public List<ProductResponse> convertToResponseList(List<Product> productList) {
		return productList
				.parallelStream()
				.map(product -> convertToResponse(product))
				.collect(Collectors.toList());
	}
	
	public ProductResponse convertToResponse(Product product) {
		
		ProductResponse response = new ProductResponse();
		
		response.setId(product.getId());
		response.setName(product.getName());
		response.setQuantity(product.getQuantity());
		response.setPrice(product.getPrice());
		
		return response;
	}
}
