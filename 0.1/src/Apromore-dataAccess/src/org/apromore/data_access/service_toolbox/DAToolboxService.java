
/*
 * 
 */

package org.apromore.data_access.service_toolbox;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2.9
 * Fri Nov 12 08:54:47 CET 2010
 * Generated source version: 2.2.9
 * 
 */


@WebServiceClient(name = "DAToolboxService", 
                  wsdlLocation = "http://localhost:8080/Apromore-dataAccess/services/DAToolbox?wsdl",
                  targetNamespace = "http://www.apromore.org/data_access/service_toolbox") 
public class DAToolboxService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://www.apromore.org/data_access/service_toolbox", "DAToolboxService");
    public final static QName DAToolbox = new QName("http://www.apromore.org/data_access/service_toolbox", "DAToolbox");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/Apromore-dataAccess/services/DAToolbox?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://localhost:8080/Apromore-dataAccess/services/DAToolbox?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public DAToolboxService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public DAToolboxService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DAToolboxService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     * 
     * @return
     *     returns DAToolboxPortType
     */
    @WebEndpoint(name = "DAToolbox")
    public DAToolboxPortType getDAToolbox() {
        return super.getPort(DAToolbox, DAToolboxPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DAToolboxPortType
     */
    @WebEndpoint(name = "DAToolbox")
    public DAToolboxPortType getDAToolbox(WebServiceFeature... features) {
        return super.getPort(DAToolbox, DAToolboxPortType.class, features);
    }

}
