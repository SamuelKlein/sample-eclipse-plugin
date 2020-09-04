package pluginsproductespressif.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;

public class WizardNewProduct extends Wizard {

	

    public WizardNewProduct() {
        super();
        setWindowTitle("New Product");
    }

    public void init(IWorkbench workbench, IStructuredSelection selection) {
    }

    public boolean performCancel() {
        return true;
    }

    public void addPages() {
        super.addPages();
    }

    public boolean canFinish() {
        var container = getContainer();
        return true;
    }

    public IWizardPage getNextPage(IWizardPage page) {
        var nextPage = super.getNextPage(page);
        var container = getContainer();
        return nextPage;
    }

    public boolean performFinish() {
    	return true;
    }
}