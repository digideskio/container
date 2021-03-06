//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, v2.2.4-2
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2013.07.10 at 12:45:26 PM CEST
//
// TOSCA version: TOSCA-v1.0-cs02.xsd
//

package org.opentosca.model.tosca;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

/**
 * <p>
 * Java class for tCapabilityDefinition complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="tCapabilityDefinition">
 *   &lt;complexContent>
 *     &lt;extension base="{http://docs.oasis-open.org/tosca/ns/2011/12}tExtensibleElements">
 *       &lt;sequence>
 *         &lt;element name="Constraints" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Constraint" type="{http://docs.oasis-open.org/tosca/ns/2011/12}tConstraint" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="capabilityType" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" />
 *       &lt;attribute name="lowerBound" type="{http://www.w3.org/2001/XMLSchema}int" default="1" />
 *       &lt;attribute name="upperBound" default="1">
 *         &lt;simpleType>
 *           &lt;union>
 *             &lt;simpleType>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger">
 *                 &lt;pattern value="([1-9]+[0-9]*)"/>
 *               &lt;/restriction>
 *             &lt;/simpleType>
 *             &lt;simpleType>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                 &lt;enumeration value="unbounded"/>
 *               &lt;/restriction>
 *             &lt;/simpleType>
 *           &lt;/union>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tCapabilityDefinition", propOrder = {"constraints"})
public class TCapabilityDefinition extends TExtensibleElements {
	
	@XmlElement(name = "Constraints")
	protected TCapabilityDefinition.Constraints constraints;
	@XmlAttribute(name = "name", required = true)
	protected String name;
	@XmlAttribute(name = "capabilityType", required = true)
	protected QName capabilityType;
	@XmlAttribute(name = "lowerBound")
	protected Integer lowerBound;
	@XmlAttribute(name = "upperBound")
	protected String upperBound;
	
	
	/**
	 * Gets the value of the constraints property.
	 * 
	 * @return possible object is {@link TCapabilityDefinition.Constraints }
	 * 
	 */
	public TCapabilityDefinition.Constraints getConstraints() {
		return this.constraints;
	}
	
	/**
	 * Sets the value of the constraints property.
	 * 
	 * @param value allowed object is {@link TCapabilityDefinition.Constraints }
	 * 
	 */
	public void setConstraints(TCapabilityDefinition.Constraints value) {
		this.constraints = value;
	}
	
	/**
	 * Gets the value of the name property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the value of the name property.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setName(String value) {
		this.name = value;
	}
	
	/**
	 * Gets the value of the capabilityType property.
	 * 
	 * @return possible object is {@link QName }
	 * 
	 */
	public QName getCapabilityType() {
		return this.capabilityType;
	}
	
	/**
	 * Sets the value of the capabilityType property.
	 * 
	 * @param value allowed object is {@link QName }
	 * 
	 */
	public void setCapabilityType(QName value) {
		this.capabilityType = value;
	}
	
	/**
	 * Gets the value of the lowerBound property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public int getLowerBound() {
		if (this.lowerBound == null) {
			return 1;
		} else {
			return this.lowerBound;
		}
	}
	
	/**
	 * Sets the value of the lowerBound property.
	 * 
	 * @param value allowed object is {@link Integer }
	 * 
	 */
	public void setLowerBound(Integer value) {
		this.lowerBound = value;
	}
	
	/**
	 * Gets the value of the upperBound property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUpperBound() {
		if (this.upperBound == null) {
			return "1";
		} else {
			return this.upperBound;
		}
	}
	
	/**
	 * Sets the value of the upperBound property.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setUpperBound(String value) {
		this.upperBound = value;
	}
	
	
	/**
	 * <p>
	 * Java class for anonymous complex type.
	 * 
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this class.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="Constraint" type="{http://docs.oasis-open.org/tosca/ns/2011/12}tConstraint" maxOccurs="unbounded"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = {"constraint"})
	public static class Constraints {
		
		@XmlElement(name = "Constraint", required = true)
		protected List<TConstraint> constraint;
		
		
		/**
		 * Gets the value of the constraint property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a
		 * snapshot. Therefore any modification you make to the returned list
		 * will be present inside the JAXB object. This is why there is not a
		 * <CODE>set</CODE> method for the constraint property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getConstraint().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link TConstraint }
		 * 
		 * 
		 */
		public List<TConstraint> getConstraint() {
			if (this.constraint == null) {
				this.constraint = new ArrayList<TConstraint>();
			}
			return this.constraint;
		}
		
	}
	
}
