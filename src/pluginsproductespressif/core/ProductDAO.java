package pluginsproductespressif.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.stream.Collectors;

import pluginsproductespressif.Activator;
import pluginsproductespressif.core.model.ProductDTO;
import pluginsproductespressif.core.model.Products;

public class ProductDAO {

	private File file;
	private Products products;
	
	private static ProductDAO myself;
	
	public static ProductDAO getInstance() {
		if (myself == null) {
			myself = new ProductDAO();
		}
		return myself;
	}

	private ProductDAO() {
		try {
			File baseDir = Activator.getDefault().getStateLocation().toFile();
			file = new File(baseDir, "products");
			if (!file.exists()) {
				file.createNewFile();
				products = new Products();
				update();
			}
			readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readObject() throws FileNotFoundException, IOException, ClassNotFoundException {
		try (var in = new ObjectInputStream(new FileInputStream(file))) {
			products = (Products) in.readObject();
		}
	}

	public ProductDTO save(ProductDTO productDTO) {
		products.getList().add(productDTO);
		update();
		return productDTO;
	}

	public List<ProductDTO> listFindByDesc(String desc) {
		if (desc != null && !desc.equals("")) {
			return products.getList()
					.stream()
					.filter((f) -> f.getDescription().startsWith(desc))
					.collect(Collectors.toList());
		}
		return products.getList();
	}

	public List<ProductDTO> listAll() {
		return products.getList();
	}

	private void update() {
		try (var out = new ObjectOutputStream(new FileOutputStream(file))) {
			out.writeObject(products);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
