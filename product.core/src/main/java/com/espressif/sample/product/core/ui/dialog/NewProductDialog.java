package com.espressif.sample.product.core.ui.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.espressif.sample.product.core.data.ProductDAO;
import com.espressif.sample.product.core.data.model.ProductDTO;
import com.espressif.sample.product.core.data.model.TypeProduct;

public class NewProductDialog extends TitleAreaDialog {

	private Text name;
	private Text description;
	private Combo type;

	private ProductDTO productDTO;

	public NewProductDialog(Shell parentShell) {
		super(parentShell);
		productDTO = new ProductDTO();
	}

	@Override
	public void create() {
		super.create();
		setTitle("New Product");
		setMessage("Create new product for Espressif", IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		var area = (Composite) super.createDialogArea(parent);
		var container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		var layout = new GridLayout(2, false);
		container.setLayout(layout);

		createName(container);
		createType(container);
		createDescription(container);

		return area;
	}

	private void createType(Composite container) {
		var lbtName = new Label(container, SWT.NONE);
		lbtName.setText("Type: ");

		type = new Combo(container, SWT.NONE);
		String[] items = new String[] { "DevKit", "Soc's", "Module" };
		type.setItems(items);
	}

	private void createName(Composite container) {
		var lbtName = new Label(container, SWT.NONE);
		lbtName.setText("Name: ");

		var dataName = new GridData();
		dataName.grabExcessHorizontalSpace = true;
		dataName.horizontalAlignment = GridData.FILL;

		name = new Text(container, SWT.BORDER);
		name.setLayoutData(dataName);
	}

	private void createDescription(Composite container) {
		var lbtDescription = new Label(container, SWT.NONE);
		lbtDescription.setText("Description: ");
		var layoutData = new GridData();
		layoutData.horizontalAlignment = GridData.FILL;
		layoutData.verticalAlignment = GridData.FILL;
		lbtDescription.setLayoutData(layoutData);

		var dataDescription = new GridData();
		dataDescription.grabExcessHorizontalSpace = true;
		dataDescription.horizontalAlignment = GridData.FILL;
		dataDescription.verticalAlignment = GridData.FILL;
		dataDescription.grabExcessVerticalSpace = true;
		dataDescription.heightHint = 120;
		dataDescription.widthHint = 200;
		description = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		description.setLayoutData(dataDescription);
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, "Submit", true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(450, 500);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	private void saveInput() {
		productDTO.setDescription(description.getText());
		productDTO.setName(name.getText());
		productDTO.setType(TypeProduct.values()[type.getSelectionIndex()]);
		ProductDAO.getInstance().save(productDTO);
	}

	@Override
	protected void okPressed() {
		saveInput();
		super.okPressed();
	}

}