
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package org.apromore.canoniser.service_manager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apromore.anf.AnnotationsType;
import org.apromore.canoniser.adapters.Canonical2EPML;
import org.apromore.canoniser.adapters.Canonical2XPDL;
import org.apromore.canoniser.adapters.EPML2Canonical;
import org.apromore.canoniser.adapters.XPDL2Canonical;
import org.apromore.canoniser.common.Constants;
import org.apromore.canoniser.da.RequestToDA;
import org.apromore.canoniser.exception.ExceptionAdapters;
import org.apromore.canoniser.exception.ExceptionStore;
import org.apromore.canoniser.exception.ExceptionVersion;
import org.apromore.canoniser.model_manager.CanoniseProcessInputMsgType;
import org.apromore.canoniser.model_manager.CanoniseProcessOutputMsgType;
import org.apromore.canoniser.model_manager.CanoniseVersionInputMsgType;
import org.apromore.canoniser.model_manager.CanoniseVersionOutputMsgType;
import org.apromore.canoniser.model_manager.DeCanoniseProcessInputMsgType;
import org.apromore.canoniser.model_manager.DeCanoniseProcessOutputMsgType;
import org.apromore.canoniser.model_manager.ProcessSummaryType;
import org.apromore.canoniser.model_manager.ResultType;
import org.apromore.cpf.CanonicalProcessType;
import org.wfmc._2008.xpdl2.PackageType;

import de.epml.TypeEPML;


/**
 * This class was generated by Apache CXF 2.2.7
 * Mon Jul 26 14:22:20 EST 2010
 * Generated source version: 2.2.7
 * 
 */

@javax.jws.WebService(
                      serviceName = "CanoniserManagerService",
                      portName = "CanoniserManager",
                      targetNamespace = "http://www.apromore.org/canoniser/service_manager",
                      wsdlLocation = "http://localhost:8080/Apromore-canoniser/services/CanoniserManager?wsdl",
                      endpointInterface = "org.apromore.canoniser.service_manager.CanoniserManagerPortType")

		public class CanoniserManagerPortTypeImpl implements CanoniserManagerPortType {

	private static final Logger LOG = Logger.getLogger(CanoniserManagerPortTypeImpl.class.getName());



	public DeCanoniseProcessOutputMsgType deCanoniseProcess(DeCanoniseProcessInputMsgType payload) { 
		LOG.info("Executing operation deCanoniseProcess");
		System.out.println(payload);
		DeCanoniseProcessOutputMsgType res = new DeCanoniseProcessOutputMsgType();
		ResultType result = new ResultType();
		res.setResult(result);

		try {
			InputStream cpf_is = null, anf_is = null;
			AnnotationsType anf = null;
			DataHandler handler_cpf = payload.getCpf();
			DataHandler handler_anf = payload.getAnf();
			String nativeType = payload.getNativeType();
			int processId = payload.getProcessId();
			String version = payload.getVersion();

			JAXBContext jc = JAXBContext.newInstance("org.apromore.cpf");
			Unmarshaller u = jc.createUnmarshaller();
			cpf_is = handler_cpf.getInputStream();
			JAXBElement<CanonicalProcessType> rootElement = (JAXBElement<CanonicalProcessType>) u.unmarshal(cpf_is);
			CanonicalProcessType cpf = rootElement.getValue();
			if (handler_anf != null){
				anf_is = handler_anf.getInputStream();
				jc = JAXBContext.newInstance("org.apromore.anf");
				u = jc.createUnmarshaller();
				JAXBElement<AnnotationsType> rootAnf = (JAXBElement<AnnotationsType>) u.unmarshal(anf_is);
				anf = rootAnf.getValue();
			}
			
			ByteArrayOutputStream native_xml = new ByteArrayOutputStream();

			/**
			 * native type must be supported by apromore.
			 * At the moment: XPDL 2.1 and EPML 2.0
			 */
			if (nativeType.compareTo("XPDL 2.1")==0 || nativeType.compareTo("EPML 2.0")==0){
				if (nativeType.compareTo("XPDL 2.1")==0) {
					Canonical2XPDL canonical2xpdl ;
					if (anf==null) {
						canonical2xpdl = new Canonical2XPDL(cpf);
					} else {
						canonical2xpdl = new Canonical2XPDL(cpf, anf);
					}
					jc = JAXBContext.newInstance("org.wfmc._2008.xpdl2");
					Marshaller m = jc.createMarshaller();
					m.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
					JAXBElement<PackageType> rootxpdl = new org.wfmc._2008.xpdl2.ObjectFactory().createPackage(canonical2xpdl.getXpdl());
					m.marshal(rootxpdl, native_xml);

				} else if (nativeType.compareTo("EPML 2.0")==0) {
					Canonical2EPML canonical2epml ;
					if (anf==null) {
						canonical2epml = new Canonical2EPML (cpf);
					} else {
						canonical2epml = new Canonical2EPML(cpf, anf);
					}
					jc = JAXBContext.newInstance("de.epml");
					Marshaller m = jc.createMarshaller();
					m.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
					JAXBElement<TypeEPML> rootepml = new de.epml.ObjectFactory().createEpml(canonical2epml.getEPML());
					m.marshal(rootepml, native_xml);
				}

				InputStream native_xml_is = new ByteArrayInputStream(native_xml.toByteArray());
				//RequestToDA request = new RequestToDA();
				//request.StoreNative(processId, version, nativeType, native_xml_is);
				//native_xml_is = new ByteArrayInputStream(native_xml.toByteArray());
				DataSource native_ds = new ByteArrayDataSource(native_xml_is, "text/xml"); 
				res.setNativeDescription(new DataHandler(native_ds));

				result.setCode(0);
				result.setMessage("");

			} else {
				result.setCode(-1);
				result.setMessage("Native type not supported.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			result.setCode(-1);
			result.setMessage ("De-canonisation failed: " + ex.getMessage());
		}
		return res;
	}


	/* (non-Javadoc)
	 * @see org.apromore.canoniser.service_manager.CanoniserManagerPortType#canoniseProcess(org.apromore.canoniser.model_manager.CanoniseProcessInputMsgType  payload )*
	 */
	public CanoniseProcessOutputMsgType canoniseProcess(CanoniseProcessInputMsgType payload) { 
		LOG.info("Executing operation canoniseProcess");
		System.out.println(payload);
		CanoniseProcessOutputMsgType res = new CanoniseProcessOutputMsgType();
		ResultType result = new ResultType();
		res.setResult(result);

		try {
			DataHandler handler = payload.getProcessDescription();
			InputStream process_xml = handler.getInputStream();
			String username = payload.getUsername();
			String nativeType = payload.getNativeType();
			String processName = payload.getProcessName();
			String domain = payload.getDomain();
			String versionName = payload.getVersionName();
			String documentation = payload.getDocumentation();
			String created = payload.getCreationDate();
			String lastupdate = payload.getLastUpdate();
			/**
			 * native type must be supported by apromore.
			 * At the moment: XPDL 2.1 adn EPML 2.0
			 */
			if (nativeType.compareTo("XPDL 2.1")==0) {
				JAXBContext jc1 = JAXBContext.newInstance(Constants.JAXB_CONTEXT_XPDL);
				Unmarshaller u = jc1.createUnmarshaller();
				JAXBElement<PackageType> rootElement = (JAXBElement<PackageType>) u.unmarshal(process_xml);
				PackageType pkg = rootElement.getValue();
				
				XPDL2Canonical xpdl2canonical = new XPDL2Canonical(pkg);

				jc1 = JAXBContext.newInstance(Constants.JAXB_CONTEXT_ANF);
				Marshaller m_anf = jc1.createMarshaller();
				m_anf.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
				JAXBElement<AnnotationsType> cproc_anf = 
					new org.apromore.anf.ObjectFactory().createAnnotations(xpdl2canonical.getAnf());
				ByteArrayOutputStream anf_xml = new ByteArrayOutputStream();
				m_anf.marshal(cproc_anf, anf_xml);
				System.out.println("Canoniser, anf size : " + anf_xml.size());
				InputStream anf_xml_is = new ByteArrayInputStream(anf_xml.toByteArray());

				jc1 = JAXBContext.newInstance(Constants.JAXB_CONTEXT_CPF);
				Marshaller m_cpf = jc1.createMarshaller();
				m_cpf.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
				JAXBElement<CanonicalProcessType> cproc_cpf = 
					new org.apromore.cpf.ObjectFactory().createCanonicalProcess(xpdl2canonical.getCpf());
				ByteArrayOutputStream cpf_xml = new ByteArrayOutputStream();
				m_cpf.marshal(cproc_cpf, cpf_xml);
				System.out.println("Canoniser, cpf size : " + cpf_xml.size());
				InputStream cpf_xml_is = new ByteArrayInputStream(cpf_xml.toByteArray());

				RequestToDA request = new RequestToDA();
				ProcessSummaryType process =
					request.storeNativeCpf (username, processName, domain, nativeType, versionName, 
							documentation, created, lastupdate, handler.getInputStream(), 
						cpf_xml_is, anf_xml_is);
				res.setProcessSummary(process);
				result.setCode(0);
				result.setMessage("");
			} else if (nativeType.compareTo("EPML 2.0")==0) {
				JAXBContext jc1 = JAXBContext.newInstance("de.epml");
				Unmarshaller u = jc1.createUnmarshaller();
				JAXBElement<TypeEPML> rootElement = (JAXBElement<TypeEPML>) u.unmarshal(process_xml);
				TypeEPML epml = rootElement.getValue();
				EPML2Canonical epml2canonical = new EPML2Canonical(epml);

				jc1 = JAXBContext.newInstance(Constants.JAXB_CONTEXT_ANF);
				Marshaller m_anf = jc1.createMarshaller();
				m_anf.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
				JAXBElement<AnnotationsType> cproc_anf = 
					new org.apromore.anf.ObjectFactory().createAnnotations(epml2canonical.getANF());
				ByteArrayOutputStream anf_xml = new ByteArrayOutputStream();
				m_anf.marshal(cproc_anf, anf_xml);
				InputStream anf_xml_is = new ByteArrayInputStream(anf_xml.toByteArray());

				jc1 = JAXBContext.newInstance(Constants.JAXB_CONTEXT_CPF);
				Marshaller m_cpf = jc1.createMarshaller();
				m_cpf.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
				JAXBElement<CanonicalProcessType> cproc_cpf = 
					new org.apromore.cpf.ObjectFactory().createCanonicalProcess(epml2canonical.getCPF());
				ByteArrayOutputStream cpf_xml = new ByteArrayOutputStream();
				m_cpf.marshal(cproc_cpf, cpf_xml);
				InputStream cpf_xml_is = new ByteArrayInputStream(cpf_xml.toByteArray());

				RequestToDA request = new RequestToDA();
				ProcessSummaryType process =
					request.storeNativeCpf (username, processName, domain, nativeType, versionName, 
							documentation, created, lastupdate, handler.getInputStream(), 
						cpf_xml_is, anf_xml_is);
				res.setProcessSummary(process);
				result.setCode(0);
				result.setMessage("");
			} else {
				result.setCode(-1);
				result.setMessage("Native type not supported.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			result.setCode(-1);
			result.setMessage("Canonisation failed: " + ex.getMessage());
		}
		return res;
	}

	public CanoniseVersionOutputMsgType canoniseVersion(CanoniseVersionInputMsgType payload) { 
		LOG.info("Executing operation canoniseVersion");
		System.out.println(payload);
		CanoniseVersionOutputMsgType res = new CanoniseVersionOutputMsgType();
		ResultType result = new ResultType();
		res.setResult(result);

		try {
			DataHandler handler = payload.getNative();
			InputStream native_is = handler.getInputStream();
			int editSessionCode = payload.getEditSessionCode();
			int processId = payload.getProcessId();
			String nativeType = payload.getNativeType();
			String domain = payload.getDomain();
			String annotationName = payload.getAnnotationName();
			String preVersion = payload.getPreVersion();
			/**
			 * native type must be supported by apromore.
			 * At the moment: XPDL 2.1 adn EPML 2.0
			 */
			if (nativeType.compareTo("XPDL 2.1")==0) {

				JAXBContext jc1 = JAXBContext.newInstance(Constants.JAXB_CONTEXT_XPDL);
				Unmarshaller u = jc1.createUnmarshaller();
				JAXBElement<PackageType> rootElement = (JAXBElement<PackageType>) u.unmarshal(native_is);
				PackageType pkg = rootElement.getValue();

				XPDL2Canonical xpdl2canonical = new XPDL2Canonical(pkg);

				jc1 = JAXBContext.newInstance(Constants.JAXB_CONTEXT_ANF);
				Marshaller m_anf = jc1.createMarshaller();
				m_anf.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
				JAXBElement<AnnotationsType> cproc_anf = 
					new org.apromore.anf.ObjectFactory().createAnnotations(xpdl2canonical.getAnf());
				ByteArrayOutputStream anf_xml = new ByteArrayOutputStream();
				m_anf.marshal(cproc_anf, anf_xml);
				InputStream anf_xml_is = new ByteArrayInputStream(anf_xml.toByteArray());

				jc1 = JAXBContext.newInstance(Constants.JAXB_CONTEXT_CPF);
				Marshaller m_cpf = jc1.createMarshaller();
				m_cpf.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
				JAXBElement<CanonicalProcessType> cproc_cpf = 
					new org.apromore.cpf.ObjectFactory().createCanonicalProcess(xpdl2canonical.getCpf());
				ByteArrayOutputStream cpf_xml = new ByteArrayOutputStream();
				m_cpf.marshal(cproc_cpf, cpf_xml);
				InputStream cpf_xml_is = new ByteArrayInputStream(cpf_xml.toByteArray());

				RequestToDA request = new RequestToDA();
				request.StoreVersion (editSessionCode, processId, preVersion, nativeType, domain, annotationName,
						handler.getInputStream(), anf_xml_is, cpf_xml_is);
				result.setCode(0);
				result.setMessage("");

			} else if (nativeType.compareTo("EPML 2.0")==0) {

				JAXBContext jc1 = JAXBContext.newInstance("de.epml");
				Unmarshaller u = jc1.createUnmarshaller();
				JAXBElement<TypeEPML> rootElement = (JAXBElement<TypeEPML>) u.unmarshal(native_is);
				TypeEPML epml = rootElement.getValue();
				EPML2Canonical epml2canonical = new EPML2Canonical(epml);

				jc1 = JAXBContext.newInstance(Constants.JAXB_CONTEXT_ANF);
				Marshaller m_anf = jc1.createMarshaller();
				m_anf.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
				JAXBElement<AnnotationsType> cproc_anf = 
					new org.apromore.anf.ObjectFactory().createAnnotations(epml2canonical.getANF());
				ByteArrayOutputStream anf_xml = new ByteArrayOutputStream();
				m_anf.marshal(cproc_anf, anf_xml);
				InputStream anf_xml_is = new ByteArrayInputStream(anf_xml.toByteArray());

				jc1 = JAXBContext.newInstance(Constants.JAXB_CONTEXT_CPF);
				Marshaller m_cpf = jc1.createMarshaller();
				m_cpf.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
				JAXBElement<CanonicalProcessType> cproc_cpf = 
					new org.apromore.cpf.ObjectFactory().createCanonicalProcess(epml2canonical.getCPF());
				ByteArrayOutputStream cpf_xml = new ByteArrayOutputStream();
				m_cpf.marshal(cproc_cpf, cpf_xml);
				InputStream cpf_xml_is = new ByteArrayInputStream(cpf_xml.toByteArray());

				RequestToDA request = new RequestToDA();
				request.StoreVersion (editSessionCode, processId, preVersion, nativeType, domain, annotationName,
						handler.getInputStream(), anf_xml_is, cpf_xml_is);
				result.setCode(0);
				result.setMessage("");

			} else {
				result.setCode(-1);
				result.setMessage("Native type not supported.");
			}
		} catch (ExceptionStore ex) {
			result.setCode(-1);
			result.setMessage(ex.getMessage());
		} catch (IOException ex) {
			result.setCode(-1);
			result.setMessage(ex.getMessage());
		} catch (JAXBException ex) {
			result.setCode(-1);
			result.setMessage("Error JAXB: " + ex.getMessage());
		} catch (ExceptionAdapters ex) {
			result.setCode(-1);
			result.setMessage("Error Adapter: " + ex.getMessage());
		} catch (ExceptionVersion ex) {
			result.setCode(-3);
			result.setMessage(ex.getMessage());
		} 
		return res;
	}

}
