package pluginsproductespressif.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Products implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<ProductDTO> list = new ArrayList<>();

	public List<ProductDTO> getList() {
		return list;
	}

	public String toString() {
		return "Products [list=" + list + "]";
	}

}
