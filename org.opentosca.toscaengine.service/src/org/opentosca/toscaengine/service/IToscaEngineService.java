package org.opentosca.toscaengine.service;

import java.util.List;

import javax.xml.namespace.QName;

import org.opentosca.core.model.artifact.AbstractArtifact;
import org.opentosca.core.model.csar.CSARContent;
import org.opentosca.core.model.csar.id.CSARID;
import org.opentosca.model.tosca.TPropertyConstraint;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * This interface describes functionality of resolving the references inside of
 * ServiceTemplates in a passed CSAR. This is needed to provide the data via the
 * ToscaReferenceMapper with which components resolve references inside of TOSCA
 * files and get the required objects.
 * 
 * Another provided functionality is to consolidate data of NodeTemplate and
 * NodeType objects. This is done for better access to the required informations
 * of one or multiple TOSCA documents. The implementation is at
 * org.opentosca.model.tosca.util.
 * 
 * Last functionality is to provide the tool ToscaReferenceMapper. This object
 * provides access to referenced informations.
 * 
 * It is used by <br>
 * org.opentosca.containerapi which needs the consolidated data and tools. <br>
 * org.opentosca.iaengine.service needs the consolidated data. <br>
 * org.opentosca.opentoscacontrol.service.impl which implements the control for
 * the OpenTosca Container. <br>
 * 
 * @author Christian Endres - endrescn@studi.informatik.uni-stuttgart.de
 * 
 */
public interface IToscaEngineService {
	
	/**
	 * This method returns the ToscaReferenceMapper.
	 * 
	 * @return the ToscaReferenceMapper
	 */
	public IToscaReferenceMapper getToscaReferenceMapper();
	
	/**
	 * This method resolves the ServiceTemplates of the passed CSAR and stores
	 * the contained references and their counterpart inside the
	 * ToscaImportMapper.
	 * 
	 * @param csarID CSAR ID in which the ServiceTemplate is stored.
	 * @return true for success, false for one or more errors
	 */
	public boolean resolveDefinitions(CSARID csarID);
	
	/**
	 * This method searches inside of certain ServiceTemplate of a CSAR for
	 * referenced NodeTypes. It returns a list of QNames which point to
	 * NodeTypes referenced by NodeTemplates via derivation or target by
	 * RelationshipTemplates via NodeTemplates.
	 * 
	 * @param csarID of the CSAR containing the ServiceTemplate.
	 * @param serviceTemplateID of the ServiceTemplate.
	 * @return List of QNames which point to NodeTypes or a empty list if no
	 *         NodeType is referenced.
	 */
	public List<QName> getReferencedNodeTypesOfAServiceTemplate(CSARID csarID, QName serviceTemplateID);
	
	/**
	 * This method checks if input parameter are specified for a given interface
	 * and operation of a NodeType.
	 * 
	 * @param csarID of the CSAR containing the NodeType.
	 * @param nodeTypeID of the NodeType to check.
	 * @param InterfaceName of the NodeType to check.
	 * @param operationName of the NodeType to check.
	 * @return <code>true</code> if input parameter are specified. Otherwise
	 *         <code>false</code>.
	 */
	boolean hasOperationOfANodeTypeSpecifiedInputParams(CSARID csarID, QName nodeTypeID, String interfaceName, String operationName);
	
	/**
	 * This method checks if input parameter are specified for a given interface
	 * and operation of a RelationshipType.
	 * 
	 * @param csarID of the CSAR containing the NodeType.
	 * @param relationshipTypeID of the RelationshipType to check.
	 * @param InterfaceName of the RelationshipType to check.
	 * @param operationName of the RelationshipType to check.
	 * @return <code>true</code> if input parameter are specified. Otherwise
	 *         <code>false</code>.
	 */
	boolean hasOperationOfARelationshipTypeSpecifiedInputParams(CSARID csarID, QName relationshipTypeID, String interfaceName, String operationName);
	
	/**
	 * This method checks if output parameter are specified for a given
	 * interface and operation of a NodeType.
	 * 
	 * @param csarID of the CSAR containing the NodeType.
	 * @param nodeTypeID of the NodeType to check.
	 * @param InterfaceName of the NodeType to check.
	 * @param operationName of the NodeType to check.
	 * @return <code>true</code> if input parameter are specified. Otherwise
	 *         <code>false</code>.
	 */
	boolean hasOperationOfANodeTypeSpecifiedOutputParams(CSARID csarID, QName nodeTypeID, String interfaceName, String operationName);
	
	/**
	 * This method checks if output parameter are specified for a given
	 * interface and operation of a RelationshipType.
	 * 
	 * @param csarID of the CSAR containing the NodeType.
	 * @param relationshipTypeID of the RelationshipType to check.
	 * @param InterfaceName of the RelationshipType to check.
	 * @param operationName of the RelationshipType to check.
	 * @return <code>true</code> if input parameter are specified. Otherwise
	 *         <code>false</code>.
	 */
	boolean hasOperationOfARelationshipTypeSpecifiedOutputParams(CSARID csarID, QName relationshipTypeID, String interfaceName, String operationName);
	
	/**
	 * Checks if specified operations is bound to sourceNode.
	 * 
	 * @param csarID of the CSAR containing the NodeType.
	 * @param relationshipTypeID of the RelationshipType to check.
	 * @param InterfaceName of the RelationshipType to check.
	 * @param operationName of the RelationshipType to check.
	 * @return <code>true</code> if operation is bound to sourceNode. Otherwise
	 *         <code>false</code>.
	 */
	boolean isOperationOfRelationshipBoundToSourceNode(CSARID csarID, QName relationshipTypeID, String interfaceName, String operationName);
	
	/**
	 * This method checks if the specified interface of a NodeType contains the
	 * specified operation.
	 * 
	 * @param csarID of the CSAR containing the NodeType.
	 * @param nodeTypeID of the NodeType to check.
	 * @param InterfaceName of the NodeType to check.
	 * @param operationName of the NodeType to check.
	 * @return <code>true</code> if interface contains the operation. Otherwise
	 *         <code>false</code>.
	 */
	boolean doesInterfaceOfNodeTypeContainOperation(CSARID csarID, QName nodeTypeID, String interfaceName, String operationName);
	
	/**
	 * This method checks if the specified interface of a RelationshipType
	 * contains the specified operation.
	 * 
	 * @param csarID of the CSAR containing the RelationshipType.
	 * @param relationshipTypeID of the RelationshipType to check.
	 * @param InterfaceName of the RelationshipType to check.
	 * @param operationName of the RelationshipType to check.
	 * @return <code>true</code> if interface contains the operation. Otherwise
	 *         <code>false</code>.
	 */
	boolean doesInterfaceOfRelationshipTypeContainOperation(CSARID csarID, QName relationshipTypeID, String interfaceName, String operationName);
	
	/**
	 * This method searches all NodeTypeImplementations for a certain NodeType
	 * inside of a given CSAR.
	 * 
	 * @param csarID of the CSAR containing the NodeTypeImplementation.
	 * @param nodeTypeID of the NodeTypeImplementation.
	 * @return List of QNames pointing to the NodeTypeImplementations or empty
	 *         list of nothing is found.
	 */
	public List<QName> getNodeTypeImplementationsOfNodeType(CSARID csarID, QName nodeTypeID);
	
	/**
	 * This method searches all NodeTypeImplementations for a certain NodeType
	 * inside of a given CSAR.
	 * 
	 * @param csarID of the CSAR containing the NodeTypeImplementation.
	 * @param relationshipTypeID of the RelationshipTypeImplementation.
	 * @return List of QNames pointing to the RelationshipTypeImplementations or
	 *         empty list of nothing is found.
	 */
	public List<QName> getRelationshipTypeImplementationsOfRelationshipType(CSARID csarID, QName relationshipTypeID);
	
	/**
	 * This method returns a list of the names of ImplementationArtifacts of a
	 * given NodeTypeImplementation in a given CSAR.
	 * 
	 * @param csarID of the CSAR containing the NodeTypeImplementation.
	 * @param nodeTypeImplementationID of the NodeTypeImplementation containing
	 *            the ImplementationArtifact names.
	 * @return List of String containing the names of the
	 *         ImplementationArtifacts or empty list if there none.
	 */
	public List<String> getImplementationArtifactNamesOfNodeTypeImplementation(CSARID csarID, QName nodeTypeImplementationID);
	
	/**
	 * This method returns a list of the names of ImplementationArtifacts of a
	 * given RelationshipTypeImplementation in a given CSAR.
	 * 
	 * @param csarID of the CSAR containing the NodeTypeImplementation.
	 * @param relationshipTypeImplementationID of the NodeTypeImplementation
	 *            containing the ImplementationArtifact names.
	 * @return List of String containing the names of the
	 *         ImplementationArtifacts or empty list if there none.
	 */
	public List<String> getImplementationArtifactNamesOfRelationshipTypeImplementation(CSARID csarID, QName relationshipTypeImplementationID);
	
	/**
	 * This method returns a list of Strings of the RequiredContainerFeatures of
	 * a given NodeTypeImplementation in a given CSAR.
	 * 
	 * @param csarID of the CSAR containing the NodeTypeImplementation.
	 * @param nodeTypeImplementationID of the NodeTypeImplementation containing
	 *            the RequiredContainerFeatures.
	 * @return List of String containing the URIs of the
	 *         RequiredContainerFeatures or empty list if there none.
	 */
	public List<String> getRequiredContainerFeaturesOfANodeTypeImplementation(CSARID csarID, QName nodeTypeImplementationID);
	
	/**
	 * This method returns the ArtifactType of a given ImplementationArtifact of
	 * a given NodeTypeImplementation in a given CSAR.
	 * 
	 * @param csarID of the CSAR containing the NodeTypeImplementation.
	 * @param nodeTypeImplementationID of the NodeTypeImplementation containing
	 *            the ImplementationArtifact.
	 * @param implementationArtifactName of the ImplementationArtifact
	 * @return QName of the ArtifactType or null in case of an error
	 */
	public QName getArtifactTypeOfAImplementationArtifactOfANodeTypeImplementation(CSARID csarID, QName nodeTypeImplementationID, String implementationArtifactName);
	
	/**
	 * This method returns the ArtifactType of a given ImplementationArtifact of
	 * a given RelationshipTypeImplementation in a given CSAR.
	 * 
	 * @param csarID of the CSAR containing the RelationshipTypeImplementation.
	 * @param relationshipTypeImplementationID of the
	 *            RelationshipTypeImplementation containing the
	 *            ImplementationArtifact.
	 * @param implementationArtifactName of the ImplementationArtifact
	 * @return QName of the ArtifactType or null in case of an error
	 */
	public QName getArtifactTypeOfAImplementationArtifactOfARelationshipTypeImplementation(CSARID csarID, QName relationshipTypeImplementationID, String implementationArtifactName);
	
	/**
	 * This method returns the ArtifactTemplate of a given
	 * ImplementationArtifact of a given NodeTypeImplementation in a given CSAR.
	 * 
	 * @param csarID of the CSAR containing the NodeTypeImplementation.
	 * @param nodeTypeImplementationID of the NodeTypeImplementation containing
	 *            the ImplementationArtifact.
	 * @param implementationArtifactName of the ImplementationArtifact
	 * @return QName of the ArtifactType or null in case of an error
	 */
	public QName getArtifactTemplateOfAImplementationArtifactOfANodeTypeImplementation(CSARID csarID, QName nodeTypeImplementationID, String implementationArtifactName);
	
	/**
	 * This method returns the ArtifactTemplate of a given
	 * ImplementationArtifact of a given RelationshipTypeImplementation in a
	 * given CSAR.
	 * 
	 * @param csarID of the CSAR containing the RelationshipTypeImplementation.
	 * @param relationshipTypeImplementationID of the
	 *            RelationshipTypeImplementation containing the
	 *            ImplementationArtifact.
	 * @param implementationArtifactName of the ImplementationArtifact
	 * @return QName of the ArtifactType or null in case of an error
	 */
	public QName getArtifactTemplateOfAImplementationArtifactOfARelationshipTypeImplementation(CSARID csarID, QName relationshipTypeImplementationID, String implementationArtifactName);
	
	/**
	 * This method returns the Node of the specified reference within the
	 * specified scarID.
	 * 
	 * @param csarID of the CSAR.
	 * @param reference of which the xml should be returned.
	 * @return Node of the reference.
	 */
	public Node getReferenceAsNode(CSARID csarID, QName reference);
	
	/**
	 * This method returns a node containing the InputParameter of Elements of
	 * the specified operation of the specified interface of the specified
	 * nodeType within the specified scarID.
	 * 
	 * @param csarID of the CSAR.
	 * @param nodeTypeID of the operation.
	 * @param interfaceName of the operation.
	 * @param operation name of which the parameters should be returned.
	 * @return Node containing of the InputParameters.
	 */
	public Node getInputParametersOfANodeTypeOperation(CSARID csarID, QName nodeTypeID, String interfaceName, String operationName);
	
	/**
	 * This method returns a node containing the OutputParameter of Elements of
	 * the specified operation of the specified interface of the specified
	 * nodeType within the specified scarID.
	 * 
	 * @param csarID of the CSAR.
	 * @param nodeTypeID of the operation.
	 * @param interfaceName of the operation.
	 * @param operation name of which the parameters should be returned.
	 * @return Node containing the OutputParameters.
	 */
	public Node getOutputParametersOfANodeTypeOperation(CSARID csarID, QName nodeTypeID, String interfaceName, String operationName);
	
	/**
	 * This method returns a node containing the InputParameter of Elements of
	 * the specified operation of the specified interface of the specified
	 * Relationship within the specified scarID.
	 * 
	 * @param csarID of the CSAR.
	 * @param relationshipTypeID of the operation.
	 * @param interfaceName of the operation.
	 * @param operation name of which the parameters should be returned.
	 * @return Node containing of the InputParameters.
	 */
	public Node getInputParametersOfARelationshipTypeOperation(CSARID csarID, QName relationshipTypeID, String interfaceName, String operationName);
	
	/**
	 * This method returns a node containing the OutputParameter of Elements of
	 * the specified operation of the specified interface of the specified
	 * Relationship within the specified scarID.
	 * 
	 * @param csarID of the CSAR.
	 * @param relationshipTypeID of the operation.
	 * @param interfaceName of the operation.
	 * @param operation name of which the parameters should be returned.
	 * @return Node containing the OutputParameters.
	 */
	public Node getOutputParametersOfARelationshipTypeOperation(CSARID csarID, QName relationshipTypeID, String interfaceName, String operationName);
	
	/**
	 * This method returns the specific content of a given
	 * ImplementationArtifact of a given NodeTypeImplementation in a given CSAR.
	 * 
	 * @param csarID of the CSAR containing the NodeTypeImplementation.
	 * @param nodeTypeImplementationID of the NodeTypeImplementation containing
	 *            the ImplementationArtifact.
	 * @param implementationArtifactName of the ImplementationArtifact
	 * @return Document which represents the specific content or null in case of
	 *         an error. A new document object is created for each method call.
	 */
	public Document getArtifactSpecificContentOfAImplementationArtifactOfANodeTypeImplementation(CSARID csarID, QName nodeTypeImplementationID, String implementationArtifactName);
	
	/**
	 * This method returns the specific content of a given
	 * ImplementationArtifact of a given RelationshipTypeImplementation in a
	 * given CSAR.
	 * 
	 * @param csarID of the CSAR containing the RelationshipTypeImplementation.
	 * @param relationshipTypeImplementationID of the
	 *            RelationshipTypeImplementation containing the
	 *            ImplementationArtifact.
	 * @param implementationArtifactName of the ImplementationArtifact
	 * @return Document which represents the specific content or null in case of
	 *         an error. A new document object is created for each method call.
	 */
	public Document getArtifactSpecificContentOfAImplementationArtifactOfARelationshipTypeImplementation(CSARID csarID, QName relationshipTypeImplementationID, String implementationArtifactName);
	
	/**
	 * This method returns the implemented interface of a given
	 * ImplementationArtifact of a given NodeTypeImplementation in a given CSAR.
	 * 
	 * @param csarID of the CSAR containing the NodeTypeImplementation.
	 * @param nodeTypeImplementationID of the NodeTypeImplementation containing
	 *            the ImplementationArtifact.
	 * @param implementationArtifactName of the ImplementationArtifact
	 * @return String which represents the implemented interface or null in case
	 *         of an error or no interface is specified.
	 */
	public String getInterfaceOfAImplementationArtifactOfANodeTypeImplementation(CSARID csarID, QName nodeTypeImplementationID, String implementationArtifactName);
	
	/**
	 * This method returns the implemented interface of a given
	 * ImplementationArtifact of a given RelationshipTypeImplementation in a
	 * given CSAR.
	 * 
	 * @param csarID of the CSAR containing the RelationshipTypeImplementation.
	 * @param relationshipTypeImplementationID of the
	 *            RelationshipTypeImplementation containing the
	 *            ImplementationArtifact.
	 * @param implementationArtifactName of the ImplementationArtifact
	 * @return String which represents the implemented interface or null in case
	 *         of an error or no interface is specified.
	 */
	public String getInterfaceOfAImplementationArtifactOfARelationshipTypeImplementation(CSARID csarID, QName relationshipTypeImplementationID, String implementationArtifactName);
	
	/**
	 * This method returns the implemented operation of a given
	 * ImplementationArtifact of a given NodeTypeImplementation in a given CSAR.
	 * 
	 * @param csarID of the CSAR containing the NodeTypeImplementation.
	 * @param nodeTypeImplementationID of the NodeTypeImplementation containing
	 *            the ImplementationArtifact.
	 * @param implementationArtifactName of the ImplementationArtifact
	 * @return String which represents the implemented operation or null in case
	 *         of an error or no operation is specified.
	 */
	public String getOperationOfAImplementationArtifactOfANodeTypeImplementation(CSARID csarID, QName nodeTypeImplementationID, String implementationArtifactName);
	
	/**
	 * This method returns the implemented operation of a given
	 * ImplementationArtifact of a given RelationshipTypeImplementation in a
	 * given CSAR.
	 * 
	 * @param csarID of the CSAR containing the RelationshipTypeImplementation.
	 * @param relationshipTypeImplementationID of the
	 *            RelationshipTypeImplementation containing the
	 *            ImplementationArtifact.
	 * @param implementationArtifactName of the ImplementationArtifact
	 * @return String which represents the implemented operation or null in case
	 *         of an error or no operation is specified.
	 */
	public String getOperationOfAImplementationArtifactOfARelationshipTypeImplementation(CSARID csarID, QName relationshipTypeImplementationID, String implementationArtifactName);
	
	/**
	 * This method returns the ArtiactSpecificContent of a deploymentArtifact of
	 * a given NodeTypeImplementation or NodeTemplate in a given CSAR.
	 * 
	 * @param csarID of the CSAR containing the ArtifactTemplate.
	 * @param reference the QName reference to a NodeTemplate or
	 *            NodeTypeImplementation.
	 * @param deploymentArtifactName the Name of the deploymentArtifact
	 * @return Document of the ArtifactSpecificContent. A new document object is
	 *         created for each method call.
	 */
	public Document getArtifactSpecificContentOfADeploymentArtifact(CSARID csarID, QName nodeTypeImplementationID, String deploymentArtifactName);
	
	/**
	 * This method returns the content of the Properties Element of an
	 * ArtifactTemplate.
	 * 
	 * @param csarID of the CSAR containing the ArtifactTemplate.
	 * @param artifactTemplateID of the requested ArtifactTemplate.
	 * @return Document which represents the Properties content or null in case
	 *         of an error.
	 */
	public Document getPropertiesOfAArtifactTemplate(CSARID csarID, QName artifactTemplateID);
	
	/**
	 * This method returns the List of PropertyConstraints of a
	 * ArtifactTemplate.
	 * 
	 * @param csarID of the CSAR containing the ArtifactTemplate.
	 * @param artifactTemplateID of the requested ArtifactTemplate.
	 * @return Document which represents the PropertyConstraints or an empty
	 *         list.
	 */
	public List<TPropertyConstraint> getPropertyConstraintsOfAArtifactTemplate(CSARID csarID, QName artifactTemplateID);
	
	/**
	 * This method returns a list of artifacts of a given ArtifactTemplate.
	 * 
	 * @param csarID of the CSAR containing the ArtifactTemplate.
	 * @param artifactTemplateID of the requested ArtifactTemplate.
	 * @return List of artifacts or empty list if there are no artifact
	 *         references or all artifact references are invalid / not
	 *         supported.
	 */
	public List<AbstractArtifact> getArtifactsOfAArtifactTemplate(CSARID csarID, QName artifactTemplateID);
	
	/**
	 * This method returns the QName of the NodeType which is the type of the
	 * NodeTemplate defined by the parameters.
	 * 
	 * 
	 * @param csarID of the CSAR containing the ArtifactTemplate.
	 * @param serviceTemplateID of the ServiceTemplate containing the
	 *            NodeTemplate.
	 * @param nodeTemplateID the String value of the attribute ID of the
	 *            NodeTemplate.
	 * @return of the NodeType or null in case of an error
	 */
	public QName getNodeTypeOfNodeTemplate(CSARID csarID, QName serviceTemplateID, String nodeTemplateID);
	
	// /**
	// * This method returns the QName of the NodeType which is the type of the
	// * NodeTemplate defined by the parameters.
	// *
	// *
	// * @param csarID
	// * of the CSAR containing the ArtifactTemplate.
	// * @param nodeTemplateID
	// * a QName value of the NodeTemplate.
	// * @return of the NodeType or null in case of an error
	// */
	// public QName getNodeTypeOfNodeTemplate(CSARID csarID, QName
	// nodeTemplateID);
	//
	/**
	 * This method returns the QName of the NodeType which is the type of the
	 * NodeTemplate defined by the parameters.
	 * 
	 * 
	 * @param csarID of the CSAR containing the ArtifactTemplate.
	 * @param serviceTemplateID of the ServiceTemplate containing the
	 *            NodeTemplate.
	 * @param relationshipTemplateID the String value of the attribute ID of the
	 *            RelationshipTemplate.
	 * @return of the NodeType or null in case of an error
	 */
	public QName getRelationshipTypeOfRelationshipTemplate(CSARID csarID, QName serviceTemplateID, String relationshipTemplateID);
	
	/**
	 * This method returns the Document which contains the default Properties of
	 * the NodeTemplate defined by the parameter
	 * 
	 * @param csarID of the CSAR containing the ArtifactTemplate.
	 * @param serviceTemplateID of the ServiceTemplate containing the
	 *            NodeTemplate.
	 * @param nodeTemplateID the String value of the attribute ID of the
	 *            NodeTemplate.
	 * @return Document containing the Properties (may be empty) or null in case
	 *         of an error
	 */
	public Document getPropertiesOfNodeTemplate(CSARID csarID, QName serviceTemplateID, String nodeTemplateID);
	
	/**
	 * This method returns a Document which contains the PropertiesDefinition of
	 * the NodeType defined by the parameter
	 * 
	 * @param csarID of the CSAR containing the ArtifactTemplate.
	 * @param nodeTypeID containing the PropertiesDefinition
	 * 
	 * 
	 * @return Document containing the PropertiesDefinition (may be empty) or
	 *         null in case of an error
	 */
	public Document getPropertiesDefinitionOfNodeType(CSARID csarID, QName nodeTypeID);
	
	/**
	 * This method returns the Document which contains the default Properties of
	 * the RelationshipTemplate defined by the parameter
	 * 
	 * @param csarID of the CSAR containing the ArtifactTemplate.
	 * @param serviceTemplateID of the ServiceTemplate containing the
	 *            RelationshipTemplate.
	 * @param relationshipTemplateID the String value of the attribute ID of the
	 *            RelationshipTemplate.
	 * @return Document containing the Properties (may be empty) or null in case
	 *         of an error
	 */
	public Document getPropertiesOfRelationshipTemplate(CSARID csarID, QName serviceTemplateID, String relationshipTemplateID);
	
	/**
	 * Returns the the resolved artifacts.
	 * 
	 * @param csarID of the CSAR containing the NodeTemplate.
	 * @param nodeTemplateID QName of the nodeTemplate (ID)
	 * @return Resolved Artifacts containing all resolvedArtifacts
	 */
	public ResolvedArtifacts getResolvedArtifactsOfNodeTemplate(CSARID csarID, QName nodeTemplateID);
	
	/**
	 * Returns the the resolved artifacts.
	 * 
	 * @param csarID of the CSAR containing the NodeTypeImplementation.
	 * @param nodeTypeImplementationID QName of the nodeTypeImplementation (ID)
	 * @return Resolved Artifacts containing all resolvedArtifacts
	 */
	public ResolvedArtifacts getResolvedArtifactsOfNodeTypeImplementation(CSARID csarID, QName nodeTypeImplementationID);
	
	/**
	 * Returns the the resolved artifacts.
	 * 
	 * @param csarID of the CSAR containing the NodeTypeImplementation.
	 * @param relationshipTypeImplementationID QName of the
	 *            relationshipTypeImplementation (ID)
	 * @return Resolved Artifacts containing all resolvedArtifacts
	 */
	public ResolvedArtifacts getResolvedArtifactsOfRelationshipTypeImplementation(CSARID csarID, QName relationshipTypeImplementationID);
	
	/**
	 * This function deletes the content about a certain CSAR, identified due
	 * the CSARID given as a parameter.
	 * 
	 * @param csarID the ID of a CSAR which stored informations shall be
	 *            deleted.
	 * @return true for success, false for one or more errors
	 */
	public boolean clearCSARContent(CSARID csarID);
	
	/**
	 * This function tries to invoke the <code>getName()</code> method of the
	 * jaxb-class belonging to the reference
	 * 
	 * @param csarID of the CSAR containing the reference
	 * @param reference the qualifiedName of the reference of which the name
	 *            attribute should be retrieved
	 * @return the return value of the call to getName-Method() of the
	 *         jaxb-class<br>
	 * 
	 *         <b>null</b> - if any error occured (f.ex. no jaxb-implementation
	 *         was found or the reference didnt exist)
	 */
	public String getNameOfReference(CSARID csarID, QName reference);
	
	/**
	 * This function retrieves the min and maxInstances of the nodeTemplates in
	 * the specified serviceTemplate
	 * 
	 * @param csarID of the CSAR containing the nodeTemplate
	 * @param serviceTemplateID of the serviceTemplate
	 * @return minInstances and maxInstances values of the nodeTemplates in the
	 *         specified serviceTemplate
	 */
	public NodeTemplateInstanceCounts getInstanceCountsOfNodeTemplatesByServiceTemplateID(CSARID csarID, QName serviceTemplateID);
	
	/**
	 * checks if a nodeTemplate exits.
	 * 
	 * @param csarID csarID of the CSAR containing the reference
	 * @param serviceTemplateID of the ServiceTemplate containing the
	 *            NodeTemplate.
	 * @param nodeTemplateID the String value of the attribute ID (=local part)
	 *            of the NodeTemplate. (Namespace is internally retrieved from
	 *            serviceTemplateID)
	 * @return true if the nodeTemplate exists, false otherwise
	 */
	boolean doesNodeTemplateExist(CSARID csarID, QName serviceTemplateID, String nodeTemplateID);
	
	/**
	 * Returns the ID of the target NodeTemplate if one exists.
	 * 
	 * @param csarID
	 * @param serviceTemplateID
	 * @param relationshipTemplateID
	 * @return the String or null in case of error or none existence
	 */
	public String getTargetNodeTemplateIDOfRelationshipTemplate(CSARID csarID, QName serviceTemplateID, String relationshipTemplateID);
	
	/**
	 * Returns the ID of the source NodeTemplate if one exists.
	 * 
	 * @param csarID
	 * @param serviceTemplateID
	 * @param relationshipTemplateID
	 * @return the String or null in case of error or none existence
	 */
	public String getSourceNodeTemplateIDOfRelationshipTemplate(CSARID csarID, QName serviceTemplateID, String relationshipTemplateID);
	
	/**
	 * Returns the ID of the related NodeTemplate if one exists.
	 * 
	 * @param csarID
	 * @param serviceTemplateID
	 * @param nodeTemplateID
	 * @param relationshipTypeName
	 * @return the String or null in case of error or none existence
	 */
	public String getRelatedNodeTemplateID(CSARID csarID, QName serviceTemplateID, String nodeTemplateID, QName relationshipType);
	
	/**
	 * This method returns the abstract artifact of a plan reference.
	 * 
	 * @param csar CSAR in which the plan is
	 * @param planId reference to the TOSCA Plan element
	 * @return the abstract artifact or null in case of an error
	 */
	public AbstractArtifact getPlanModelReferenceAbstractArtifact(CSARContent csar, QName planId);
	
}
