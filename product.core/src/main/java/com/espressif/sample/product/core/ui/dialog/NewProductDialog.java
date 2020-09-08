package com.espressif.sample.product.core.ui.dialog;

import java.io.File;
import java.util.Optional;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.nebula.widgets.tablecombo.TableCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.espressif.sample.product.core.Activator;
import com.espressif.sample.product.core.data.ProductDAO;
import com.espressif.sample.product.core.data.ProductValidation;
import com.espressif.sample.product.core.data.model.ProductDTO;
import com.espressif.sample.product.core.data.model.TypeProduct;

public class NewProductDialog extends TitleAreaDialog {

	private Text name;
	private Text description;
	private TableCombo type;

	private ProductDTO productDTO;
	private ProductValidation productValidation;

	public NewProductDialog(Shell parentShell) {
		super(parentShell);
		productDTO = new ProductDTO();
		productValidation = new ProductValidation();
	}

	@Override
	public void create() {
		super.create();
		setTitle("New Product");
		setMessage("Create new product for Espressif", IMessageProvider.INFORMATION);
		getButton(IDialogConstants.OK_ID).setEnabled(false);
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

		type = new TableCombo(container, SWT.BORDER | SWT.READ_ONLY);
		String[] items = new String[] { "DevKit", "Soc's", "Module" };
		String[] itemsImg = new String[] { "devkit.png", "socs.png", "module.png" };

		for (int i = 0; i < items.length; i++) {
			var ti = new TableItem(type.getTable(), SWT.NONE);
			ti.setText(items[i]);
			var id = Activator.getImageDescriptor("icons/" + itemsImg[i]);
			ti.setImage(id.createImage());
		}
		type.addListener(SWT.KeyUp | SWT.MouseUp, (ev) -> {
			listener();
		});
		type.addVerifyListener((event) -> {
			listener();
			var valid = new ProductValidation();
			var typeproduct = Optional.of(type.getSelectionIndex()).map((in) -> in >= 0 ? in : null)
					.map((in) -> TypeProduct.values()[in]).orElse(null);

			valid.validType(typeproduct);
			sendError(valid);
		});
	}

	private void createName(Composite container) {
		var lbtName = new Label(container, SWT.NONE);
		lbtName.setText("Name: ");

		var dataName = new GridData();
		dataName.grabExcessHorizontalSpace = true;
		dataName.horizontalAlignment = GridData.FILL;

		name = new Text(container, SWT.BORDER);
		name.setLayoutData(dataName);
		name.addListener(SWT.KeyUp | SWT.MouseUp, (ev) -> {
			listener();
		});
		name.addVerifyListener((event) -> {
			listener();
			var valid = new ProductValidation();
			valid.validName(name.getText());
			sendError(valid);
		});
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
		description.addListener(SWT.KeyUp | SWT.MouseUp, (ev) -> {
			listener();
		});
		description.addVerifyListener((event) -> {
			listener();
			var valid = new ProductValidation();
			valid.validDescription(description.getText());
			sendError(valid);
		});
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

	private boolean saveInput() {
		productDTO.setDescription(description.getText());
		productDTO.setName(name.getText());
		if (type.getSelectionIndex() >= 0) {
			productDTO.setType(TypeProduct.values()[type.getSelectionIndex()]);
		}
		if (productValidation.valid(productDTO)) {
			ProductDAO.getInstance().save(productDTO);
			return true;
		} else {
			sendError(productValidation);
		}

		return false;
	}

	public void listener() {
		productDTO.setType(null);
		productDTO.setDescription(description.getText());
		productDTO.setName(name.getText());
		if (type.getSelectionIndex() >= 0) {
			productDTO.setType(TypeProduct.values()[type.getSelectionIndex()]);
		}
		getButton(IDialogConstants.OK_ID).setEnabled(productValidation.valid(productDTO));
	}

	private void sendError(ProductValidation productValidation) {
		if (productValidation.getMsg() != null) {
			setMessage(productValidation.getMsg(), IMessageProvider.ERROR);
		} else {
			setMessage("Create new product for Espressif", IMessageProvider.INFORMATION);
		}
	}

	@Override
	protected void okPressed() {
		if (saveInput()) {
			super.okPressed();
		}
	}

}