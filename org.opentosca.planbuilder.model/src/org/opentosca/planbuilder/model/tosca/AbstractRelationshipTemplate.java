package org.opentosca.planbuilder.model.tosca;

import java.util.List;

/**
 * <p>
 * This class represents a TOSCA RelationshipTemplate
 * </p>
 * Copyright 2013 IAAS University of Stuttgart <br>
 * <br>
 * 
 * @author Kálmán Képes - kepeskn@studi.informatik.uni-stuttgart.de
 * 
 */
public abstract class AbstractRelationshipTemplate extends AbstractEntityTemplate {
	
	/**
	 * Returns the AbstractNodeTemplate representing the NodeTemplate which is
	 * the source of this RelationshipTemplate
	 * 
	 * @return an AbstractNodeTemplate representing the source NodeTemplate of
	 *         this RelationshipTemplate
	 */
	public abstract AbstractNodeTemplate getSource();
	
	/**
	 * Returns the AbstractNodeTemplate representing the NodeTemplate which is
	 * the target of this RelationshipTemplate
	 * 
	 * @return an AbstractNodeTemplate representing the taret NodeTemplate of
	 *         this RelationshipTemplate
	 */
	public abstract AbstractNodeTemplate getTarget();
	
	/**
	 * Returns the name of this RelationshipTemplate
	 * 
	 * @return a String containing the Name of this
	 *         AbstractRelationshipTemplate, if not declared null
	 */
	public abstract String getName();
	
	/**
	 * Returns the RelationshipType of this RelationshipTemplate
	 * 
	 * @return an AbstractRelationshipType
	 */
	public abstract AbstractRelationshipType getRelationshipType();
	
	/**
	 * Returns all RelationshipTypeImplementations of this RelationshipTemplate
	 * 
	 * @return a List of AbstractRelationshipTypeImplementations
	 */
	public abstract List<AbstractRelationshipTypeImplementation> getImplementations();
	
	/**
	 * Returns the Properties of this RelationshipTemplate
	 * 
	 * @return an AbstractProperties
	 */
	public abstract AbstractProperties getProperties();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof AbstractRelationshipTemplate) {
			AbstractRelationshipTemplate relation = (AbstractRelationshipTemplate) o;
			if (relation.getId().equals(this.getId())) {
				return true;
			}
			return false;
			
		} else {
			return false;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return (this.getId() + this.getName()).hashCode();
	}
	
}
