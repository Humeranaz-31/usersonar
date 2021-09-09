package com.shopping.onlineshopping.user.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ProductDTO {

	String prodid;
	@NotNull(message = "Please provide product name")
	@Pattern(regexp = "[A-Za-z]+( [A-Za-z]+)*", message = "Name should contain only alphabets and space")
	@Max(100)
	String productname;
	@NotNull(message = "Please provide price of the product")
	@Min(200)
	Double price;
	@Min(10)
	Integer stock;
	@Size(min = 1, max = 500)
	String description;
	@Pattern(regexp = "([^\\s]+(\\.(?i)(jpeg|png))$)", message = "Image should be in jpeg or png format only")
	String image;
	String sellerid;
	String category;
	String subcategory;
	Integer productrating;

	public String getProdid() {
		return prodid;
	}

	public void setProdid(String prodid) {
		this.prodid = prodid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSellerid() {
		return sellerid;
	}

	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public Integer getProductrating() {
		return productrating;
	}

	public void setProductrating(Integer productrating) {
		this.productrating = productrating;
	}

	public ProductDTO() {
		super();
	}

	public ProductDTO(String prodid, String productname, Double price, Integer stock, String description, String image,
			String sellerid, String category, String subcategory, Integer productrating) {
		this();
		this.prodid = prodid;
		this.productname = productname;
		this.price = price;
		this.stock = stock;
		this.description = description;
		this.image = image;
		this.sellerid = sellerid;
		this.category = category;
		this.subcategory = subcategory;
		this.productrating = productrating;
	}

//	// Converts Entity into DTO
//	public static ProductDTO valueOf(Product product) {
//		ProductDTO productDTO = new ProductDTO();
//		productDTO.setProdid(product.getProdid());
//		productDTO.setProductname(product.getProductname());
//		productDTO.setPrice(product.getPrice());
//		productDTO.setStock(product.getStock());
//		productDTO.setDescription(product.getDescription());
//		productDTO.setImage(product.getImage());
//		productDTO.setSellerid(product.getSellerid());
//		productDTO.setCategory(product.getCategory());
//		productDTO.setSubcategory(product.getSubcategory());
//		productDTO.setProductrating(product.getProductrating());
//		return productDTO;
//	}

	@Override
	public String toString() {
		return "ProductDTO [prodid=" + prodid + ", productname=" + productname + ", price=" + price + ", stock=" + stock
				+ ", description=" + description + ", image=" + image + ", sellerid=" + sellerid + ", category="
				+ category + ", subcategory=" + subcategory + ", productrating=" + productrating + "]";
	}
}
