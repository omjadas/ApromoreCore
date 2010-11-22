
package org.apromore.toolbox.model_da;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.cxf.jaxb.JAXBToStringBuilder;
import org.apache.cxf.jaxb.JAXBToStringStyle;


/**
 * <p>Java class for ProcessVersionsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProcessVersionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessVersion" type="{http://www.apromore.org/data_access/model_toolbox}ProcessVersionType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessVersionsType", propOrder = {
    "processVersion"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2010-11-22T05:52:30+01:00", comments = "JAXB RI vhudson-jaxb-ri-2.1-2")
public class ProcessVersionsType {

    @XmlElement(name = "ProcessVersion")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2010-11-22T05:52:30+01:00", comments = "JAXB RI vhudson-jaxb-ri-2.1-2")
    protected List<ProcessVersionType> processVersion;

    /**
     * Gets the value of the processVersion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the processVersion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProcessVersion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProcessVersionType }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2010-11-22T05:52:30+01:00", comments = "JAXB RI vhudson-jaxb-ri-2.1-2")
    public List<ProcessVersionType> getProcessVersion() {
        if (processVersion == null) {
            processVersion = new ArrayList<ProcessVersionType>();
        }
        return this.processVersion;
    }

    /**
     * Generates a String representation of the contents of this type.
     * This is an extension method, produced by the 'ts' xjc plugin
     * 
     */
    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2010-11-22T05:52:30+01:00", comments = "JAXB RI vhudson-jaxb-ri-2.1-2")
    public String toString() {
        return JAXBToStringBuilder.valueOf(this, JAXBToStringStyle.DEFAULT_STYLE);
    }

}
