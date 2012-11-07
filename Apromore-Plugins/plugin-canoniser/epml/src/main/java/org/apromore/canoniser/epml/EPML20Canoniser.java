package org.apromore.canoniser.epml;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.apromore.anf.AnnotationsType;
import org.apromore.canoniser.Canoniser;
import org.apromore.canoniser.DefaultAbstractCanoniser;
import org.apromore.canoniser.epml.internal.Canonical2EPML;
import org.apromore.canoniser.epml.internal.EPML2Canonical;
import org.apromore.canoniser.exception.CanoniserException;
import org.apromore.canoniser.result.CanoniserMetadataResult;
import org.apromore.cpf.CanonicalProcessType;
import org.apromore.plugin.PluginRequest;
import org.apromore.plugin.PluginResult;
import org.apromore.plugin.exception.PluginPropertyNotFoundException;
import org.apromore.plugin.impl.PluginResultImpl;
import org.apromore.plugin.property.PluginParameterType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import de.epml.EPMLSchema;
import de.epml.TypeCoordinates;
import de.epml.TypeDirectory;
import de.epml.TypeEPC;
import de.epml.TypeEPML;

/**
 * EPML 2.0 Canoniser Plugin
 *
 * @author Felix Mannhardt (Bonn-Rhein-Sieg University oAS)
 *
 */
@Component("epmlCanoniser")
public class EPML20Canoniser extends DefaultAbstractCanoniser {

    private static final Logger LOGGER = LoggerFactory.getLogger(EPML20Canoniser.class);

    public static final String EPML_CONTEXT = "de.epml";

    public static final String ADD_FAKE_PROPERTY_ID = "addFakeProperties";

    private final PluginParameterType<Boolean> fakeEventsProperty;

	public EPML20Canoniser() {
		super();
		this.fakeEventsProperty = new PluginParameterType<Boolean>(ADD_FAKE_PROPERTY_ID,"Add Fake Events?", "", false, Canoniser.CANONISE_PARAMETER, true);
		registerParameter(fakeEventsProperty);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.apromore.canoniser.Canoniser#canonise(org.apromore.canoniser.NativeInput, java.io.OutputStream, java.io.OutputStream)
	 */
	@Override
	public PluginResult canonise(final InputStream nativeInput, final List<AnnotationsType> annotationFormat, final List<CanonicalProcessType> canonicalFormat, final PluginRequest request) throws CanoniserException {

		try {
			JAXBElement<TypeEPML> nativeElement = EPMLSchema.unmarshalEPMLFormat(nativeInput, true);
			EPML2Canonical epml2canonical = new EPML2Canonical(nativeElement.getValue());

			annotationFormat.add(epml2canonical.getANF());
			canonicalFormat.add(epml2canonical.getCPF());

			return newPluginResult();

		} catch (JAXBException | SAXException e) {
			throw new CanoniserException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.apromore.canoniser.Canoniser#deCanonise(java.io.InputStream, java.io.InputStream, org.apromore.canoniser.NativeOutput)
	 */
	@Override
	public PluginResult deCanonise(final CanonicalProcessType canonicalFormat, final AnnotationsType annotationFormat, final OutputStream nativeOutput, final PluginRequest request) throws CanoniserException {

		try {
			Canonical2EPML canonical2epml;

			if (annotationFormat != null) {
				canonical2epml = new Canonical2EPML(canonicalFormat, annotationFormat,
						request.getRequestParameter(fakeEventsProperty).getValue());
			} else {
				canonical2epml = new Canonical2EPML(canonicalFormat, request.getRequestParameter(fakeEventsProperty).getValue());
			}

			EPMLSchema.marshalEPMLFormat(nativeOutput, canonical2epml.getEPML(), true);

			return newPluginResult();

		} catch (JAXBException e) {
			throw new CanoniserException(e);
		} catch (PluginPropertyNotFoundException e) {
		    throw new CanoniserException(e);
        } catch (SAXException e) {
            throw new CanoniserException(e);
        }

	}
    /* (non-Javadoc)
     * @see org.apromore.canoniser.Canoniser#createInitialNativeFormat(java.io.OutputStream, org.apromore.plugin.PluginRequest)
     */
    @Override
    public PluginResult createInitialNativeFormat(final OutputStream nativeOutput, final String processName, final String processVersion, final String processAuthor,
            final Date processCreated, final PluginRequest request) {
        // create an empty epml process (see issue 129)
        // then just creation of an empty process.
        TypeEPML epml = new TypeEPML();
        TypeCoordinates coordinates = new TypeCoordinates();
        coordinates.setXOrigin("leftToRight");
        coordinates.setYOrigin("topToBottom");
        epml.setCoordinates(coordinates);
        TypeDirectory directory = new TypeDirectory();
        directory.setName("Root");
        epml.getDirectory().add(directory);
        TypeEPC epc = new TypeEPC();
        epc.setEpcId(BigInteger.ONE);
        if (processName != null) {
            epc.setName(processName);
        } else {
            epc.setName("");
        }
        directory.getEpcOrDirectory().add(epc);

        PluginResultImpl newPluginResult = newPluginResult();

        try {
            EPMLSchema.marshalEPMLFormat(nativeOutput, epml, true);
        } catch (JAXBException e) {
            LOGGER.error("Could not create initial EPML", e);
            newPluginResult.addPluginMessage("Could not create initial EPML, reason: {0}", e.getMessage());
        } catch (SAXException e) {
            LOGGER.error("Could not create initial EPML", e);
            newPluginResult.addPluginMessage("Could not create initial EPML, reason: {0}", e.getMessage());
        }

        return newPluginResult;
    }

    /* (non-Javadoc)
     * @see org.apromore.canoniser.Canoniser#readMetaData(java.io.InputStream, org.apromore.plugin.PluginRequest)
     */
    @Override
    public CanoniserMetadataResult readMetaData(final InputStream nativeInput, final PluginRequest request) {
        //TODO read metadata from EPML
        return new CanoniserMetadataResult();
    }

}
