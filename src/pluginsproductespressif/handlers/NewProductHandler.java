package pluginsproductespressif.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;

import pluginsproductespressif.core.ProductDAO;
import pluginsproductespressif.core.model.ProductDTO;
import pluginsproductespressif.core.model.TypeProduct;
import pluginsproductespressif.wizard.WizardNewProduct;

public class NewProductHandler extends AbstractHandler {
	
	private ProductDAO dao = ProductDAO.getInstance(); 
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		var productDTO = new ProductDTO();
		productDTO.setDescription("TESTES");
		productDTO.setType(TypeProduct.DEV_KIT);
		productDTO.setName("TESTES");
		dao.save(productDTO);
		
		var activeShell = HandlerUtil.getActiveShell(event);
		var wizard = new WizardNewProduct();
		var dialog = new WizardDialog(activeShell, wizard);
		dialog.open();
		
		return null;
	}
}
