package com.espressif.sample.product.core.ui.view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.espressif.sample.product.core.data.ProductDAO;
import com.espressif.sample.product.core.data.ProductEvent;
import com.espressif.sample.product.core.data.model.ProductDTO;

public class ViewSeachProduct extends ViewPart {

	public static final String ID = "pluginsproductespressif.views.SampleViewSeachProduct";

	IWorkbench workbench;
	
	public static ViewSeachProduct viewSeachProduct;

	private Table table;
	
	private String description;

	private Text txtDescription;


	public ViewSeachProduct() {
		workbench = PlatformUI.getWorkbench();
		viewSeachProduct = this;
		ProductEvent.getInstance().listeningUpdate((p) -> {
			refresh();
			return p;
		});
	}

	@Override
	public void createPartControl(Composite parent) {
		var container = new Composite(parent, SWT.BORDER);
		container.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		container.setLayout(new GridLayout(1, true));
		
		var containerSeach = new Composite(container, SWT.NONE);
		containerSeach.setLayoutData(new GridData());
		containerSeach.setLayout(new GridLayout(3, false));
		var lblSearch = new Label(containerSeach, SWT.NONE);
		lblSearch.setText("Search:");
		txtDescription = new Text(containerSeach, SWT.BORDER);
		var dataDescription = new GridData();
		dataDescription.widthHint = 200;
		txtDescription.setLayoutData(dataDescription);
		txtDescription.setSize(300, txtDescription.getSize().y);
		
		var btnSearch = new Button(containerSeach, SWT.NONE);
		btnSearch.setText("Go");

	    btnSearch.addListener(SWT.Selection, (Event event) -> {
	    	description = txtDescription.getText();
	    	refresh();
	    });
		
		table = new Table(container, SWT.BORDER | SWT.VIRTUAL);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		var column = new TableColumn(table, SWT.BORDER);
		column.setText("Nome");
		column.setWidth(100);
		column = new TableColumn(table, SWT.BORDER);
		column.setText("Type");
		column.setWidth(100);
		column = new TableColumn(table, SWT.BORDER);
		column.setText("Description");
		column.setWidth(100);
		refresh();
	}

	@Override
	public void setFocus() {
		txtDescription.setFocus();
	}
	
	private void refresh() {
		var list = ProductDAO.getInstance().listFindByDescOrName(description);
		updateTable(list);
	}

	private void updateTable(List<ProductDTO> list) {
		table.removeAll();
		for (var item : list) {
			var tableItem = new TableItem(table, SWT.BORDER);
			tableItem.setText(0, item.getName());
			tableItem.setText(1, item.getType().getDesc());
			tableItem.setText(2, item.getDescription());
		}
	}

}
