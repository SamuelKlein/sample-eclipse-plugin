package com.espressif.sample.product.core.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import com.espressif.sample.product.core.data.ProductDAO;

public class SearchProductHandler extends AbstractHandler {

	private ProductDAO dao = ProductDAO.getInstance();
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			var problemView = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("com.espressif.sample.product.core.ui.view.ViewSeachProduct");
			problemView.setFocus();
		} catch (Exception e) {
		}
		return null;
	}
}
