package com.shopping.onlineshopping.user.dto;

import java.util.List;

import com.shopping.onlineshopping.user.entity.Wishlist;

public class WishlistDTO {

//	BuyerDTO buyerid;
//	List<ProductDTO> prodid;
//
//	public List<ProductDTO> getProdid() {
//		return prodid;
//	}
//
//	public void setProdid(List<ProductDTO> prodid) {
//		this.prodid = prodid;
//	}
//
//	public BuyerDTO getBuyerid() {
//		return buyerid;
//	}
//
//	public void setBuyerid(BuyerDTO buyerid) {
//		this.buyerid = buyerid;
//	}
//
//	@Override
//	public String toString() {
//		return "WishlistDTO [buyerid=" + buyerid + ", prodid=" + prodid + "]";
//	}
//
//	public static WishlistDTO valueOf(Wishlist wishlist) {
//		WishlistDTO wishlistDTO = new WishlistDTO();
//		BuyerDTO buyerDTO = new BuyerDTO();
//		buyerDTO.setBuyerid(wishlist.getBuyerid());
//		wishlistDTO.setBuyerid(buyerDTO);
//		ProductDTO productDTO = new ProductDTO();
//		productDTO.setProdid(wishlist.getProdid());
////		wishlistDTO.setProdid(productDTO);
//		return wishlistDTO;
//	}
//	
//	public Wishlist createEntity() {
//		Wishlist wish = new Wishlist();
//		wish.setBuyerid(this.getBuyerid().buyerid);
////		wish.setProdid(this.getProdid());
//		return wish;
//		
//	}
	String buyerid;
	ProductDTO prodid;
	public String getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(String buyerid) {
		this.buyerid = buyerid;
	}
	public ProductDTO getProdid() {
		return prodid;
	}
	public void setProdid(ProductDTO prodid) {
		this.prodid = prodid;
	}
	@Override
	public String toString() {
		return "WishlistDTO [buyerid=" + buyerid + ", prodid=" + prodid + "]";
	}
	public static WishlistDTO valueOf(Wishlist wishlist) {
		WishlistDTO wishlistDTO = new WishlistDTO();
		wishlistDTO.setBuyerid(wishlist.getBuyerid());
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProdid(wishlist.getProdid());
    return wishlistDTO;
	}
	public Wishlist createEntity() {
		Wishlist wish = new Wishlist();
		wish.setBuyerid(this.getBuyerid());
		wish.setProdid(this.getProdid().prodid);
		return wish;
		
	}
}
