package com.shopping.onlineshopping.user.dto;

import com.shopping.onlineshopping.user.entity.Cart;

public class CartDTO {

	BuyerDTO buyerid;
	ProductDTO prodid;
	int quantity;

	public BuyerDTO getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(BuyerDTO buyerid) {
		this.buyerid = buyerid;
	}

	public ProductDTO getProdid() {
		return prodid;
	}

	public void setProdid(ProductDTO prodid) {
		this.prodid = prodid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartDTO [buyerid=" + buyerid + ", prodid=" + prodid + ", quantity=" + quantity + "]";
	}

	public static CartDTO valueOf(Cart cart) {
		CartDTO cartDTO = new CartDTO();
		BuyerDTO buyerDTO = new BuyerDTO();
		buyerDTO.setBuyerid(cart.getBuyerid());
		cartDTO.setBuyerid(buyerDTO);
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProdid(cart.getProdid());
		cartDTO.setProdid(productDTO);
		cartDTO.setQuantity(cart.getQuantity());
		return cartDTO;
	}
	
	public Cart createEntity() {
		Cart cart = new Cart();
		cart.setBuyerid(this.getBuyerid().buyerid);
		cart.setProdid(this.getProdid().prodid);
		cart.setQuantity(this.getQuantity());
		return cart;
	}
}
