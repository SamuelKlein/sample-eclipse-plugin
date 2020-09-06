package com.espressif.sample.product.core.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.handlers.HandlerUtil;

import com.espressif.sample.product.core.ui.dialog.NewProductDialog;

public class NewProductHandler extends AbstractHandler {
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		var activeShell = HandlerUtil.getActiveShell(event);
		var dialog = new NewProductDialog(activeShell);
		dialog.open();
		return null;
	}
}
