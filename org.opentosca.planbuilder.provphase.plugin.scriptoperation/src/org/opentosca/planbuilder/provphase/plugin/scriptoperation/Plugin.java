package org.opentosca.planbuilder.provphase.plugin.scriptoperation;

import javax.xml.namespace.QName;

import org.opentosca.planbuilder.model.tosca.AbstractImplementationArtifact;
import org.opentosca.planbuilder.model.tosca.AbstractOperation;
import org.opentosca.planbuilder.plugins.IPlanBuilderProvPhaseOperationPlugin;
import org.opentosca.planbuilder.plugins.context.TemplatePlanContext;
import org.opentosca.planbuilder.provphase.plugin.scriptoperation.handler.Handler;

/**
 * <p>
 * This class implements a ProvPhase Plugin, in particular to enable
 * provisioning with scripts
 * </p>
 * Copyright 2013 IAAS University of Stuttgart <br>
 * <br>
 * 
 * @author Kalman Kepes - kepeskn@studi.informatik.uni-stuttgart.de
 * 
 */
public class Plugin implements IPlanBuilderProvPhaseOperationPlugin {
	
	private QName baseTypeScriptArtifact = new QName("http://docs.oasis-open.org/tosca/ns/2011/12/ToscaBaseTypes", "ScriptArtifact");
	private Handler handler = new Handler();
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getID() {
		return "OpenTOSCA ProvPhase ScriptOperation Plugin v0.1";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canHandle(QName artifactType) {
		return artifactType.toString().equals(this.baseTypeScriptArtifact.toString());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean handle(TemplatePlanContext context, AbstractOperation operation, AbstractImplementationArtifact ia) {
		return this.handler.handle(context, operation, ia);
	}
	
}
