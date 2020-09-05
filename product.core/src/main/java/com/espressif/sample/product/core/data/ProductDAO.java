package com.espressif.sample.product.core.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import com.espressif.sample.product.core.Activator;
import com.espressif.sample.product.core.data.model.ProductDTO;
import com.espressif.sample.product.core.data.model.Products;
import com.espressif.sample.product.core.ui.view.ViewSeachProduct;
import com.google.gson.Gson;

public class ProductDAO {

	File file;
	Products products;

	private static ProductDAO myself;

	public static ProductDAO getInstance() {
		if (myself == null) {
			myself = new ProductDAO();
		}
		return myself;
	}

	ProductDAO() {
		try {
			File baseDir = Activator.getDefault().getStateLocation().toFile();
			file = new File(baseDir, "products.json");
			if (!file.exists()) {
				file.createNewFile();
				products = new Products();
				update();
			}
			refresh();
		} catch (Exception e) {
			throw new DataException(e, "Error Open File Prop.");
		}
	}

	void refresh() {
		try {
			var json = Files.readString(file.toPath());
			var gson = new Gson();
			products = gson.fromJson(json, Products.class);
		} catch (Exception e) {
			throw new DataException(e, "Error Loading Product");
		}
	}

	public ProductDTO save(ProductDTO productDTO) {
		products.getList().add(productDTO);
		update();
		refresh();
		return productDTO;
	}

	public List<ProductDTO> listFindByDescOrName(String desc) {
		if (desc != null && !desc.equals("")) {
			return products.getList()
					.stream().filter((f) -> 
								f.getDescription().toUpperCase().startsWith(desc.toUpperCase()) ||
								f.getName().toUpperCase().startsWith(desc.toUpperCase()))
					.collect(Collectors.toList());
		}
		
		return products.getList();
	}

	public List<ProductDTO> listAll() {
		return products.getList();
	}

	void update() {
		try (var out = new FileOutputStream(file)) {
			var gson = new Gson();
			out.write(gson.toJson(products).getBytes());
			ViewSeachProduct.refresh();
		} catch (Exception e) {
			throw new DataException(e, "Error Product Send to File");
		}
	}

}
