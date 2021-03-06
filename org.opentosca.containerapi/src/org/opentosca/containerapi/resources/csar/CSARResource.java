package org.opentosca.containerapi.resources.csar;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.opentosca.containerapi.osgi.servicegetter.FileRepositoryServiceHandler;
import org.opentosca.containerapi.osgi.servicegetter.IOpenToscaControlServiceHandler;
import org.opentosca.containerapi.resources.utilities.ResourceConstants;
import org.opentosca.containerapi.resources.utilities.Utilities;
import org.opentosca.containerapi.resources.xlink.Reference;
import org.opentosca.containerapi.resources.xlink.References;
import org.opentosca.containerapi.resources.xlink.XLinkConstants;
import org.opentosca.core.file.service.ICoreFileService;
import org.opentosca.core.model.artifact.file.AbstractFile;
import org.opentosca.core.model.csar.CSARContent;
import org.opentosca.core.model.csar.id.CSARID;
import org.opentosca.exceptions.SystemException;
import org.opentosca.exceptions.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Resource represents a CSAR.<br />
 * <br />
 * 
 * 
 * Copyright 2013 IAAS University of Stuttgart<br />
 * <br />
 * 
 * @author Rene Trefft - trefftre@studi.informatik.uni-stuttgart.de
 * @author endrescn@fachschaft.informatik.uni-stuttgart.de
 * 
 * 
 */
public class CSARResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(CSARContentResource.class);
	
	// If csar is null, CSAR is not stored
	private final CSARContent CSAR;
	
	
	public CSARResource(CSARContent csar) {
		this.CSAR = csar;
		
		CSARResource.LOG.info("{} created: {}", this.getClass(), this);
	}
	
	@GET
	@Produces(ResourceConstants.LINKED_XML)
	public Response getReferences(@Context UriInfo uriInfo) {
		
		if (this.CSAR == null) {
			
			return Response.status(404).build();
		}
		
		References refs = new References();
		
		refs.getReference().add(new Reference(Utilities.buildURI(uriInfo.getAbsolutePath().toString(), "Content"), XLinkConstants.SIMPLE, "Content"));
		refs.getReference().add(new Reference(Utilities.buildURI(uriInfo.getAbsolutePath().toString(), "TopologyPicture"), XLinkConstants.SIMPLE, "TopologyPicture"));
		
		refs.getReference().add(new Reference(Utilities.buildURI(uriInfo.getAbsolutePath().toString(), "Instances"), XLinkConstants.SIMPLE, "Instances"));
		refs.getReference().add(new Reference(Utilities.buildURI(uriInfo.getAbsolutePath().toString(), "PublicPlans"), XLinkConstants.SIMPLE, "PublicPlans"));
		CSARResource.LOG.info("Number of References in Root: {}", refs.getReference().size());
		
		// selflink
		refs.getReference().add(new Reference(uriInfo.getAbsolutePath().toString(), XLinkConstants.SIMPLE, XLinkConstants.SELF));
		return Response.ok(refs.getXMLString()).build();
	}
	
	@Path("Content")
	public CSARContentResource getContent() {
		return new CSARContentResource(this.CSAR);
		
	}
	
	@Path("PublicPlans")
	public CSARPublicPlanTypesResource getPuplicPlans() {
		return new CSARPublicPlanTypesResource(this.CSAR.getCSARID());
	}
	
	@Path("Instances")
	public CSARInstancesResource getInstances() {
		return new CSARInstancesResource(this.CSAR.getCSARID());
	}
	
	// "image/*" will be preferred over "text/xml" when requesting an image.
	// This is a fix for Webkit Browsers who are too dumb for content
	// negotiation.
	
	@Produces("image/*; qs=2.0")
	@GET
	@Path("TopologyPicture")
	public Response getTopologyPicture() throws SystemException {
		
		AbstractFile topologyPicture = this.CSAR.getTopologyPicture();
		
		if (topologyPicture != null) {
			MediaType mt = new MediaType("image", "*");
			
			// try {
			InputStream is = topologyPicture.getFileAsInputStream();
			return Response.ok(is, mt).header("Content-Disposition", "attachment; filename=\"" + topologyPicture.getName() + "\"").build();
			// } catch (SystemException exc) {
			// CSARResource.LOG.error("An System Exception occured.", exc);
			// }
			
		}
		return Response.status(Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("No Topology Picture exists in CSAR \"" + this.CSAR.getCSARID() + "\".").build();
	}
	
	/**
	 * Exports this CSAR.
	 * 
	 * @return CSAR as {@code application/octet-stream}. If an error occurred
	 *         during exporting (e.g. during retrieving files from storage
	 *         provider(s)) 500 (internal server error).
	 * @throws SystemException
	 * @throws UserException
	 * 
	 * @see ICoreFileService#exportCSAR(CSARID)
	 * 
	 */
	@GET
	@Produces(ResourceConstants.OCTET_STREAM)
	public Response downloadCSAR() throws SystemException, UserException {
		
		CSARID csarID = this.CSAR.getCSARID();
		
		// try {
		
		java.nio.file.Path csarFile = FileRepositoryServiceHandler.getFileHandler().exportCSAR(csarID);
		InputStream csarFileInputStream;
		
		try {
			csarFileInputStream = Files.newInputStream(csarFile);
		} catch (IOException e) {
			throw new SystemException("Retrieving input stream of file \"" + csarFile.toString() + "\" failed.", e);
		}
		
		// We add Content Disposition header, because exported CSAR file to
		// download should have the correct file name.
		return Response.ok("CSAR \"" + csarID + "\" was successfully exported to \"" + csarFile + "\".").entity(csarFileInputStream).header("Content-Disposition", "attachment; filename=\"" + csarID.getFileName() + "\"").build();
		
		// } catch (SystemException exc) {
		// CSARResource.LOG.warn("A System Exception occured.", exc);
		// } catch (UserException exc) {
		// CSARResource.LOG.warn("An User Exception occured.", exc);
		// } catch (IOException exc) {
		// CSARResource.LOG.warn("An IO Exception occured.", exc);
		// }
		
		// return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		
	}
	
	@DELETE
	@Produces("text/plain")
	public Response delete() {
		
		CSARID csarID = this.CSAR.getCSARID();
		
		CSARResource.LOG.info("Deleting CSAR \"{}\".", csarID);
		List<String> errors = IOpenToscaControlServiceHandler.getOpenToscaControlService().deleteCSAR(csarID);
		
		// if (errors.contains("CSAR has instances.")) {
		// return Response.notModified("CSAR has instances.").build();
		// }
		
		if (errors.isEmpty()) {
			return Response.ok("Deletion of CSAR " + "\"" + csarID + "\" was sucessful.").build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Deletion of CSAR \"" + csarID + "\" failed.").build();
		}
		
	}
	
}
