package com.espressif.sample.product.core.data;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.espressif.sample.product.core.data.model.ProductDTO;
import com.espressif.sample.product.core.data.model.TypeProduct;

public class ProductDAOTest {
	
	private ProductDAO productDAO;  
	
	
	@Before
	public void init() {
		productDAO = ProductDAO.getInstance();
	}
	
	@Test
	public void saveProduct() {
		assertNotNull(saveProduct("TESTES", "Testes", TypeProduct.DEV_KIT));
		assertTrue(productDAO.listAll().size() > 0);
	}
	
	@Test
	public void listAllProduct() {
		saveProduct("TESTES", "Testes", TypeProduct.DEV_KIT);
		saveProduct("TESTES", "Testes", TypeProduct.DEV_KIT);
		saveProduct("TESTES", "Testes", TypeProduct.DEV_KIT);
		assertTrue(productDAO.listAll().size() > 0);
	}
	
	@Test
	public void listDescProduct() {
		saveProduct("MYPRODUCY", "Testes", TypeProduct.DEV_KIT);
		saveProduct("TESTES", "Testes", TypeProduct.DEV_KIT);
		saveProduct("TESTES", "Testes", TypeProduct.DEV_KIT);
		System.out.println(productDAO.listFindByDescOrName("MYPRODUCY").size());
		assertTrue(productDAO.listFindByDescOrName("MYPRODUCY").size() > 0);
	}
	
	@Test
	public void listDescNullProduct() {
		saveProduct("MYPRODUCY", "Testes", TypeProduct.DEV_KIT);
		saveProduct("TESTES", "Testes", TypeProduct.DEV_KIT);
		saveProduct("TESTES", "Testes", TypeProduct.DEV_KIT);
		System.out.println(productDAO.listFindByDescOrName(null).size());
		assertTrue(productDAO.listFindByDescOrName(null).size() > 0);
	}
	
	@Test(expected = DataException.class)
	public void updateError() {
		var productDAO = new ProductDAO();
		productDAO.file = null;
		productDAO.update();
		fail("Exception not found");
	}
	
	@Test(expected = DataException.class)
	public void refreshError() {
		var productDAO = new ProductDAO();
		productDAO.file = null;
		productDAO.refresh();
		fail("Exception not found");
	}
	
	
	private ProductDTO saveProduct(String desc, String name, TypeProduct type) {
		var productDTO = new ProductDTO();
		productDTO.setDescription(desc);
		productDTO.setName(name);
		productDTO.setType(type);
		return productDAO.save(productDTO);
	}
	
}
