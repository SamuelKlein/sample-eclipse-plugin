package pluginsproductespressif.handlers;

import javax.inject.Inject;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import pluginsproductespressif.core.ProductDAO;
import pluginsproductespressif.core.model.ProductDTO;

import org.eclipse.jface.dialogs.MessageDialog;

public class SearchProductHandler extends AbstractHandler {

	private ProductDAO dao = ProductDAO.getInstance();
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		var window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(
				window.getShell(),
				"PluginsProductEspressif",
				dao.listAll().toString());
		
		return null;
	}
}
