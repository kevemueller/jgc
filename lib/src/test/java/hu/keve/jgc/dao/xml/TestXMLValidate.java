package hu.keve.jgc.dao.xml;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.xml.sax.SAXException;

import hu.keve.jgc.xml.GnuCashXMLSource;

public final class TestXMLValidate {
	@DisplayName("Validate resource")
	@ParameterizedTest(name = "{index} => resource=''{0}''")
	@ValueSource(strings = { "Simple.xml.gnucash", "Full.xml.gnucash" })
	void validateResource(String resourceName) throws SAXException, IOException {
		URL resource = getClass().getClassLoader().getResource(resourceName);
		assertNotNull(resource);
		InputStream in = GnuCashXMLSource.openStream(resource);
		GnuCashXMLSource.validate(in);
	}
}
