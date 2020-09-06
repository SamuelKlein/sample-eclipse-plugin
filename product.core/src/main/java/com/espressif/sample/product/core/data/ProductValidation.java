package com.espressif.sample.product.core.data;

import com.espressif.sample.product.core.data.model.ProductDTO;
import com.espressif.sample.product.core.data.model.TypeProduct;

public class ProductValidation {

	private String msg;

	public boolean valid(ProductDTO productDTO) {
		msg = null;
		if (productDTO == null) {
			msg = "Product not found";
			return false;
		}

		if (!validName(productDTO.getName())) {
			return false;
		}
		if (!validType(productDTO.getType())) {
			return false;
		}
		if (!validDescription(productDTO.getDescription())) {
			return false;
		}

		return true;
	}

	public boolean validDescription(String description) {
		msg = null;
		if (description == null || description.trim().equals("") || description.length() > 300) {
			msg = "Description invalid";
			return false;
		}

		return true;
	}

	public boolean validName(String name) {
		msg = null;
		if (name == null || name.trim().equals("") || name.length() > 100) {
			msg = "Name invalid";
			return false;
		}

		return true;
	}

	public boolean validType(TypeProduct type) {
		msg = null;
		if (type == null) {
			msg = "Type invalid";
			return false;
		}

		return true;
	}

	public String getMsg() {
		return msg;
	}
}
