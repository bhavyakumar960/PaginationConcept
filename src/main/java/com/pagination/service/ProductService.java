package com.pagination.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pagination.entity.Product;
import com.pagination.repository.ProductRepository;
import com.pagination.response.BaseResponse;
import com.pagination.response.ProductResponse;


@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

//	@PostConstruct    //import jakarta.annotation.PostConstruct;
//	public void initDB() {
//
//		List<Product> products = IntStream.rangeClosed(1, 200)
//				.mapToObj(i -> Product.builder()
//						.name("Product " + i)
//						.quantity(new Random().nextInt(100)) // Random quantity between 1 and 100
//						.price(new Random().nextDouble(50000)) // Random price between 0 and 100
//						.build())
//				.collect(Collectors.toList());
//		
//		productRepository.saveAll(products);
//	}
	
	
	public ResponseEntity<BaseResponse<ProductResponse>> getAllProducts() {
		List<Product> products = productRepository.findAll();
		
		BaseResponse<ProductResponse> baseResponse = new BaseResponse<>();
		baseResponse.setStatus(200);
		baseResponse.setMessage("Data Successfully Fetched");
		baseResponse.setRecordCount(products.size());
		baseResponse.setDataList(new ProductResponse().convertToResponseList(products));
		
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}
	
	
	public ResponseEntity<BaseResponse<ProductResponse>> findProductsWithFieldSorting(String field, String order){
		
		List<Product> productList = (order.equalsIgnoreCase("asc"))?productRepository.findAll(Sort.by(Direction.ASC, field))
				:productRepository.findAll(Sort.by( Direction.DESC,field));
		
		
		BaseResponse<ProductResponse> baseResponse = new BaseResponse<>();
		baseResponse.setStatus(200);
		baseResponse.setMessage("Data Successfully Fetched");
		baseResponse.setRecordCount(productList.size());
		baseResponse.setDataList(new ProductResponse().convertToResponseList(productList));
		
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	
	public ResponseEntity<BaseResponse<ProductResponse>> findProductsWithPagination(Integer offset, Integer paseSize){
		
//		List<Product> productList = productRepository.findAll(PageRequest.of(offset, paseSize)).toList();
		Page<Product> productList = productRepository.findAll(PageRequest.of(offset, paseSize));
		
		
		BaseResponse<ProductResponse> baseResponse = new BaseResponse<>();
		baseResponse.setStatus(200);
		baseResponse.setMessage("Data Successfully Fetched");
		baseResponse.setRecordCount(productList.getSize());
		baseResponse.setTotalRecord(productList.getTotalElements());
		baseResponse.setDataList(new ProductResponse().convertToResponseList(productList.toList()));
		
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	
	public ResponseEntity<BaseResponse<ProductResponse>> findProductsWithPaginationAndSorting(Integer offset, Integer paseSize, String field, String order){
		
//		List<Product> productList = productRepository.findAll(PageRequest.of(offset, paseSize)).toList();
		Page<Product> productList = (order.equalsIgnoreCase("asc"))?productRepository.findAll(PageRequest.of(offset, paseSize).withSort(Sort.by( Direction.ASC, field)))
				:productRepository.findAll(PageRequest.of(offset, paseSize).withSort(Sort.by( Direction.DESC, field)));
		
		
		BaseResponse<ProductResponse> baseResponse = new BaseResponse<>();
		baseResponse.setStatus(200);
		baseResponse.setMessage("Data Successfully Fetched");
		baseResponse.setRecordCount(productList.getSize());
		baseResponse.setTotalRecord(productList.getTotalElements());
		baseResponse.setDataList(new ProductResponse().convertToResponseList(productList.toList()));
		
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}
	
}
