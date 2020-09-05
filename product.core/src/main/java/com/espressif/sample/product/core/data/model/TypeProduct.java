package com.espressif.sample.product.core.data.model;

public enum TypeProduct {
	
	DEV_KIT(1, "DevKit"),
	SOC_S(2, "Soc's"),
	MODULE(3, "Module");
	
	private int cod;
	private String desc;
	
	private TypeProduct(int cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDesc() {
		return desc;
	}
	
}
