package hu.keve.jgc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Unmarshaller.Listener;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.gnucash.xml.IdType;
import org.gnucash.xml.cmdty.CmdtyType;
import org.gnucash.xml.owner.OwnerType;
import org.xml.sax.SAXException;

import com.sun.xml.bind.IDResolver;

import hu.keve.jgc.dao.jaxb.AbstractGnuCashJAXB;
import hu.keve.jgc.dao.jaxb.AbstractGuidType;
import hu.keve.jgc.dao.jaxb.GCDefaultXMLPrefixes;
import hu.keve.jgc.dao.jaxb.adapters.ActRefTypeAdapter;
import hu.keve.jgc.dao.jaxb.adapters.BilltermRefTypeAdapter;
import hu.keve.jgc.dao.jaxb.adapters.CmdtyRefTypeAdapter;
import hu.keve.jgc.dao.jaxb.adapters.InvoiceRefTypeAdapter;
import hu.keve.jgc.dao.jaxb.adapters.LotRefTypeAdapter;
import hu.keve.jgc.dao.jaxb.adapters.OrderRefTypeAdapter;
import hu.keve.jgc.dao.jaxb.adapters.TaxtableRefTypeAdapter;
import hu.keve.jgc.dao.jaxb.adapters.TrnRefTypeAdapter;
import hu.keve.jgc.util.jaxb.IDResolver2;

public final class GnuCashXMLSource {
	private static Schema schema;
	private static JAXBContext jaxbContext;

	private GnuCashXMLSource() {
	}

	public static Schema getSchema() throws SAXException {
		if (null == schema) {
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			URL xsdResource = GnuCashXMLSource.class.getResource("jgc.xsd");
			schema = schemaFactory.newSchema(xsdResource);
		}
		return schema;
	}

	public static InputStream openStream(URL resource) throws IOException {
		BufferedInputStream urlStream = new BufferedInputStream(resource.openStream());
		urlStream.mark(8);
		try {
			InputStream in = new GZIPInputStream(urlStream);
			return in;
		} catch (ZipException ex) {
			urlStream.reset();
			return urlStream;
		}
	}

	public static InputStream openStream(File file) throws IOException {
		return openStream(file.toURI().toURL());
	}

	public static void validate(InputStream in) throws SAXException, IOException {
		StreamSource xmlStreamSource = new StreamSource(in);
		Validator validator = getSchema().newValidator();
		validator.validate(xmlStreamSource);
	}

	public static void validate(URL resource) throws SAXException, IOException {
		validate(openStream(resource));
	}

	public static void validate(File file) throws SAXException, IOException {
		validate(openStream(file));
	}

	public static long copy(File destination, URL source) throws IOException {
		InputStream in = openStream(source);
		return Files.copy(in, destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}

	public static long copy(File destination, File source) throws IOException {
		return copy(destination, source.toURI().toURL());
	}

	public static Unmarshaller getUnmarshaller() throws JAXBException {
		if (null == jaxbContext) {
			jaxbContext = JAXBContext.newInstance("org.gnucash.xml:org.gnucash.xml.ts");
		}
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		IDResolver2 idResolver = new IDResolver2();
		unmarshaller.setProperty(IDResolver.class.getName(), idResolver);
		unmarshaller.setAdapter(new ActRefTypeAdapter(idResolver));
		unmarshaller.setAdapter(new BilltermRefTypeAdapter(idResolver));
		unmarshaller.setAdapter(new CmdtyRefTypeAdapter(idResolver));
		unmarshaller.setAdapter(new InvoiceRefTypeAdapter(idResolver));
		unmarshaller.setAdapter(new LotRefTypeAdapter(idResolver));
		unmarshaller.setAdapter(new OrderRefTypeAdapter(idResolver));
		unmarshaller.setAdapter(new TaxtableRefTypeAdapter(idResolver));
		unmarshaller.setAdapter(new TrnRefTypeAdapter(idResolver));
		Listener listener = new Listener() {
			AbstractGnuCashJAXB root;

			@Override
			public void beforeUnmarshal(Object target, Object parent) {
				// System.err.println("Before: " + target + " -> " + parent);
				if (target instanceof AbstractGnuCashJAXB) {
					System.err.println("Setting root!");
					root = (AbstractGnuCashJAXB) target;
				}
				if (target instanceof AbstractGuidType) {
					// System.err.println("Setting guidroot!" + root);
					((AbstractGuidType) target).setGuidRoot(root);
				}
				super.beforeUnmarshal(target, parent);
			}

			@Override
			public void afterUnmarshal(Object target, Object parent) {
				// System.err.println("After: " + target + " -> " + parent);
				if (target instanceof IdType) {
					if (parent instanceof OwnerType) {

					} else {
						idResolver.bind(((IdType) target).getValue(), parent);
						root.register(((IdType) target).getValue(), (AbstractGuidType) parent);
					}
				} else if (target instanceof CmdtyType) {
					String id = CmdtyRefTypeAdapter.makeId((CmdtyType) target);
					idResolver.bind(id, target);
					root.register((CmdtyType) target);
				}
				super.afterUnmarshal(target, parent);
			}
		};
		unmarshaller.setListener(listener);
		return unmarshaller;
	}

	public static Marshaller getMarshaller() throws JAXBException {
		if (null == jaxbContext) {
			jaxbContext = JAXBContext.newInstance("org.gnucash.xml:org.gnucash.xml.ts");
		}
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper",
				GCDefaultXMLPrefixes.getNamespacePrefixMapper());

		IDResolver2 idResolver = null;
		marshaller.setAdapter(new ActRefTypeAdapter(idResolver));
		marshaller.setAdapter(new BilltermRefTypeAdapter(idResolver));
		marshaller.setAdapter(new CmdtyRefTypeAdapter(idResolver));
		marshaller.setAdapter(new InvoiceRefTypeAdapter(idResolver));
		marshaller.setAdapter(new LotRefTypeAdapter(idResolver));
		marshaller.setAdapter(new OrderRefTypeAdapter(idResolver));
		marshaller.setAdapter(new TaxtableRefTypeAdapter(idResolver));
		marshaller.setAdapter(new TrnRefTypeAdapter(idResolver));

		return marshaller;
	}
}
