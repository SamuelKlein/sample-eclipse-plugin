package com.espressif.sample.product.core.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;

public class SearchProductHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			var problemView = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("com.espressif.sample.product.core.ui.view.ViewSeachProduct");
			problemView.setFocus();
		} catch (Exception e) {
			//TODO
		}
		return null;
	}
}
