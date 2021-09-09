package com.shopping.onlineshopping.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Wishlist implements Serializable{

		@Id
		@Column(name = "buyerid",nullable = false)
		String buyerid;
		@Column(name = "prodid",nullable = false)
		String prodid;
		public Wishlist() {
			
		}
		public String getBuyerid() {
			return buyerid;
		}
		public void setBuyerid(String buyerid) {
			this.buyerid = buyerid;
		}
		public String getProdid() {
			return prodid;
		}
		public void setProdid(String prodid) {
			this.prodid = prodid;
		}
		public Wishlist(String buyerid, String prodid) {
			super();
			this.buyerid = buyerid;
			this.prodid = prodid;
		}
		@Override
		public String toString() {
			return "Wishlist [buyerid=" + buyerid + ", prodid=" + prodid + "]";
		}
		
		
}
