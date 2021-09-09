package com.shopping.onlineshopping.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.shopping.onlineshopping.user.dto.LoginDTO;
import com.shopping.onlineshopping.user.dto.ProductDTO;
import com.shopping.onlineshopping.user.dto.SellerDTO;
import com.shopping.onlineshopping.user.service.SellerService;

@RestController
@CrossOrigin
@RequestMapping
public class SellerController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	Environment environment;
	@Value("${product.uri}")
	String product;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	SellerService sellerservice;

	// Seller register
	@PostMapping(value = "/api/seller/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createSeller(@Valid @RequestBody SellerDTO sellerDTO) throws Exception {

		logger.info("Registration request for seller with data {}", sellerDTO);
		sellerservice.saveSeller(sellerDTO);
		String successMessage = environment.getProperty("API.INSERT_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);

	}

	// Get all sellers
	@GetMapping(value = "/api/sellers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SellerDTO>> getAllSeller() throws Exception {

		List<SellerDTO> sellerDTOs = sellerservice.getAllSeller();
		return new ResponseEntity<>(sellerDTOs, HttpStatus.OK);

	}

	// Seller login
	@PostMapping(value = "/seller/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> Login(@Valid @RequestBody LoginDTO loginDTO) throws Exception {

		sellerservice.login(loginDTO);
		logger.info("Login request for seller {} with password {}", loginDTO.getEmail(), loginDTO.getPassword());
		String successMessage = environment.getProperty("API.LOGIN_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);

	}

	// Delete seller
	@DeleteMapping(value = "/seller/{sellerid}")
	public ResponseEntity<String> deleteSeller(@PathVariable String sellerid) throws Exception {

		sellerservice.deleteSeller(sellerid);
		String successMessage = environment.getProperty("API.DELETE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);

	}

	// Get products of seller
	@GetMapping(value = "/api/sellers/products/{sellerid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getProductsOfSeller(@PathVariable String sellerid) throws Exception {

		@SuppressWarnings("unchecked")
		List<ProductDTO> productDTO = new RestTemplate().getForObject("http://localhost:8100/apis/products/" + sellerid,
				List.class);
		return new ResponseEntity<>(productDTO, HttpStatus.OK);

	}
}
