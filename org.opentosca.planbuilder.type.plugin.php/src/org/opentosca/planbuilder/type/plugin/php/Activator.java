package org.opentosca.planbuilder.type.plugin.php;

import org.opentosca.planbuilder.plugins.IPlanBuilderTypePlugin;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
	
	private static BundleContext context;
	private ServiceRegistration registration;
	
	
	static BundleContext getContext() {
		return Activator.context;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.registration = Activator.context.registerService(IPlanBuilderTypePlugin.class.getName(), new Plugin(), null);
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		this.registration.unregister();
	}
	
}
