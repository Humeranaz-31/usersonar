package com.shopping.onlineshopping.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
import javax.validation.constraints.Size;

import com.shopping.onlineshopping.user.entity.Seller;

public class SellerDTO {

	String sellerid;
	@NotNull(message = "Please provide customer name")
	@Pattern(regexp = "[A-Za-z]+( [A-Za-z]+)*", message = "Name should contain only alphabets and space")
	String name;
	@Email(message = "Please provide valid email address")
    @NotNull(message = "Please provide email address")
	String email;
	@NotNull(message = "Please provide phone number")
	@Size(min=10,max=10)
	@Pattern(regexp="(^[7-9][0-9]{9})", message = "Phone number should be 10 digits only")
	String phoneno;
	@NotNull(message = "Please provide password")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#!@$%^&*]).{7,20}$", 
			message = "It should be 7 to 20 characters in length (both inclusive). It should contain at least one uppercase, at least one lowercase, at least one digit. "
					+ "It should also contain a special character amongst -! @, #, $, %, ^, &, *")
	String password;

	Boolean isactive;

	public String getSellerid() {
		return sellerid;
	}

	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	@Override
	public String toString() {
		return "SellerDTO [sellerid=" + sellerid + ", name=" + name + ", email=" + email + ", phoneno=" + phoneno
				+ ", password=" + password + ", isactive=" + isactive + "]";
	}

	// Converts Entity into DTO
	public static SellerDTO valueOf(Seller seller) {
		SellerDTO sellerDTO = new SellerDTO();
		sellerDTO.setSellerid(seller.getSellerid());
		sellerDTO.setName(seller.getName());
		sellerDTO.setEmail(seller.getEmail());
		sellerDTO.setPhoneno(seller.getPhoneno());
		sellerDTO.setPassword(seller.getPassword());
		sellerDTO.setIsactive(seller.getIsactive());
		return sellerDTO;
	}

	public Seller createSeller() {
		Seller seller = new Seller();
		seller.setSellerid(this.getSellerid());
		seller.setName(this.getName());
		seller.setPhoneno(this.getPhoneno());
		seller.setEmail(this.getEmail());
		seller.setPassword(this.getPassword());
		seller.setIsactive(this.getIsactive());
		return seller;
	}
}
