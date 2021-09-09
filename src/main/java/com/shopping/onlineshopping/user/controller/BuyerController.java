package com.shopping.onlineshopping.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.shopping.onlineshopping.user.dto.BuyerDTO;
import com.shopping.onlineshopping.user.dto.CartDTO;
import com.shopping.onlineshopping.user.dto.LoginDTO;
import com.shopping.onlineshopping.user.dto.ProductDTO;
import com.shopping.onlineshopping.user.dto.WishlistDTO;
import com.shopping.onlineshopping.user.entity.Buyer;
import com.shopping.onlineshopping.user.service.BuyerService;
import com.shopping.onlineshopping.user.service.SellerService;

@RestController
@CrossOrigin
@RequestMapping
public class BuyerController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	Environment environment;
	@Value("${product.uri}")
	String product;

	@Autowired
	RestTemplate restTemplate;
	@Autowired
	BuyerService buyerservice;
	@Autowired
	SellerService sellerservice;

	// Register buyer
	@PostMapping(value = "/api/buyer/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createBuyer(@Valid @RequestBody BuyerDTO buyerDTO) throws Exception {

		String successMessage = environment.getProperty("API.INSERT_SUCCESS");
		logger.info("Registration request for buyer with data {}", buyerDTO);
		buyerservice.saveBuyer(buyerDTO);
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);

	}

	// Get all buyers
	@GetMapping(value = "/api/buyers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BuyerDTO>> getAllBuyer() throws Exception {

		List<BuyerDTO> buyerDTOs = buyerservice.getAllBuyer();
		return new ResponseEntity<>(buyerDTOs, HttpStatus.OK);

	}

	// Get buyer by id
	@GetMapping(value = "/buyer/{buyerid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BuyerDTO> getBuyerById(@PathVariable String buyerid) throws Exception {

		BuyerDTO buyer = buyerservice.getBuyerById(buyerid);
		return new ResponseEntity<>(buyer, HttpStatus.OK);

	}

	// Buyer login
	@PostMapping(value = "/buyer/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO) throws Exception {

		buyerservice.login(loginDTO);
		logger.info("Login request for buyer {} with password {}", loginDTO.getEmail(), loginDTO.getPassword());
		String successMessage = environment.getProperty("API.LOGIN_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);

	}

	// Delete buyer
	@DeleteMapping(value = "/buyer/{buyerid}")
	public ResponseEntity<String> deleteBuyer(@PathVariable String buyerid) throws Exception {

		buyerservice.deleteBuyer(buyerid);
		String successMessage = environment.getProperty("API.DELETE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);

	}

//	@GetMapping(value = "/api/buyers/wishlist/{buyerid}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<WishlistDTO> getWishlistOfBuyer(@PathVariable String buyerid) throws InfyMarketException {
//		try {
//			WishlistDTO wishlistDTOs = buyerservice.getWishlistOfBuyer(buyerid);
//			BuyerDTO buyerDTO = new RestTemplate().getForObject("http://localhost:8400/buyer/"+buyerid, BuyerDTO.class);
//			wishlistDTOs.setBuyerid(buyerDTO.getBuyerid());
//			
//			@SuppressWarnings("unchecked")
//			List<ProductDTO> products = new RestTemplate().getForObject("http://localhost:8100/api/products", List.class);
//			wishlistDTOs.setProdid(products);
//			
//			return new ResponseEntity<>(wishlistDTOs, HttpStatus.OK);
//		} catch (Exception exception) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()),
//					exception);
//		}
//	}

	// Add product to wishlist
	@PostMapping(value = "/api/wishlist/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveWishlist(@RequestBody WishlistDTO wishlistDTO) throws Exception {

		logger.info("Creation request for customer {} with data {}", wishlistDTO);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("prodid", wishlistDTO.getProdid().getProdid());
		// map.put("buyerid",wishlistDTO.getBuyerid());
		System.out.println("adding map" + map);
		ProductDTO projectDTOs = restTemplate.getForObject("http://localhost:8100/api/verifyprod/{prodid}",
				ProductDTO.class, map);
		System.out.println("adding wishlist" + projectDTOs);
		buyerservice.createWishlist(wishlistDTO);
		String successMessage = environment.getProperty("API.WISHLIST_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);

	}

	// Delete product from wishlist
	@DeleteMapping(value = "/wishlist/{buyerid}")
	public ResponseEntity<String> deleteWishlist(@PathVariable String buyerid) throws Exception {

		buyerservice.deleteWishlist(buyerid);
		String successMessage = environment.getProperty("API.DELETE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);

	}

	// Add product to cart
	@PostMapping(value = "/api/cart/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveCart(@RequestBody CartDTO cartDTO) throws Exception {

		logger.info("Creation request for customer {} with data {}", cartDTO);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buyerid", cartDTO.getBuyerid().getBuyerid());
		map.put("prodid", cartDTO.getProdid().getProdid());
		// map.put("buyerid",wishlistDTO.getBuyerid());
		System.out.println("adding map" + map);
		ProductDTO projectDTOs = restTemplate.getForObject("http://localhost:8100/api/verifyprod/{prodid}",
				ProductDTO.class, map);
		System.out.println("adding cart" + projectDTOs);
		buyerservice.createCart(cartDTO);
		String successMessage = environment.getProperty("API.CART_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);

	}

	// Delete product from cart
	@DeleteMapping(value = "/api/cart/{buyerid}")
	public ResponseEntity<String> deleteCart(@PathVariable String buyerid) throws Exception {

		buyerservice.deleteCart(buyerid);
		String successMessage = environment.getProperty("API.DELETE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);

	}

	// Update is privileged
	@RequestMapping(value = "/api/isprivilege/{buyerid}", method = RequestMethod.PUT)
	public ResponseEntity<Buyer> updateIsprivilege(@RequestBody Buyer buyer, @PathVariable String buyerid)
			throws Exception {
		Buyer buyers = buyerservice.updateIsprivilege(buyer, buyerid);
		return new ResponseEntity<>(buyers, HttpStatus.OK);

	}

}
