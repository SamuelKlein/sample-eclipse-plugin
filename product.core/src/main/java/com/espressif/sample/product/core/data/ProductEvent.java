package com.espressif.sample.product.core.data;

import java.util.function.Function;

import com.espressif.sample.product.core.data.model.Products;

public class ProductEvent {
	
	private static ProductEvent mySelf;

	private ProductEvent() {
		mySelf = this;
	}
	
	private Function<Products, Products> callUpdate;
	
	public void listeningUpdate(Function<Products, Products> callUpdate) {
		this.callUpdate = callUpdate;
	}
	
	public void callUpdate(Products products) {
		if(callUpdate != null) {
			callUpdate.apply(products);
		}
	}
	
	public static ProductEvent getInstance() {
		if (mySelf == null) {
			new ProductEvent();
		}
		return mySelf;
	}
	
}
