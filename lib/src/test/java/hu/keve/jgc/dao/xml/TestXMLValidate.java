package hu.keve.jgc.dao.xml;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.xml.sax.SAXException;

import hu.keve.jgc.xml.GnuCashXMLSource;

public final class TestXMLValidate {
	private static Schema schema;
	
	@BeforeAll
	static void beforeAll() throws SAXException {
		schema = GnuCashXMLSource.getSchema();
	}

	@DisplayName("Validate resource")
	@ParameterizedTest(name = "{index} => resource=''{0}''")
	@ValueSource(strings = { "Simple.xml.gnucash", "Full.xml.gnucash" })
	void validateResource(String resourceName) throws SAXException, IOException {
		URL resource = getClass().getClassLoader().getResource(resourceName);
		assertNotNull(resource);
		InputStream in = GnuCashXMLSource.openStream(resource);
		GnuCashXMLSource.validate(in);
	}

	@DisplayName("Validate file")
	@ParameterizedTest(name = "{index} => file=''{0}''")
	@ValueSource(strings = { "CJ.xml", "PhA.xml", "Simple.xml" })
	void validateFile(String fileName) throws SAXException, IOException {
		File file = new File(fileName);
		assertTrue(file.exists());
		InputStream in = GnuCashXMLSource.openStream(file);
		StreamSource xmlStreamSource = new StreamSource(in);
		Validator validator = schema.newValidator();
		validator.validate(xmlStreamSource);
	}
}
