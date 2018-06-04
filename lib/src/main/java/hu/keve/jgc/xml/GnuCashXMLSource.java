package hu.keve.jgc.xml;

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
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public final class GnuCashXMLSource {
	private static Schema schema;

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

}
