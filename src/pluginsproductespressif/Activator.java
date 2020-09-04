package pluginsproductespressif;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.*;

public class Activator extends AbstractUIPlugin implements BundleActivator {

	private static Activator plugin;

	public Activator() {
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static Activator getDefault() {
		return plugin;
	}

}