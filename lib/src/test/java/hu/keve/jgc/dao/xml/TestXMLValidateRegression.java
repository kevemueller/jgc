package hu.keve.jgc.dao.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.helpers.XMLReaderFactory;

import hu.keve.jgc.util.junit.PathsSource;
import hu.keve.jgc.xml.GnuCashXMLSource;

public final class TestXMLValidateRegression {
	private static Schema schema;

	@BeforeAll
	static void beforeAll() throws SAXException {
		schema=GnuCashXMLSource.getSchema();
	}

	@ParameterizedTest(name = "{index}{0}")
	@PathsSource(path = "lib/Gnucash/data/accounts", regexp = ".*\\.gnucash-xea$")
	void validateDataAccounts(Path path) throws SAXException, IOException {
		doValidate(path);
	}

	@ParameterizedTest(name = "{index}{0}")
	@PathsSource(path = "lib/Gnucash/doc/examples", regexp = ".*\\.gnucash$")
	void expandDocExamples(Path path) throws SAXException, IOException {
		doCopy(path);
	}

	@ParameterizedTest(name = "{index}{0}")
	@PathsSource(path = "lib/Gnucash/doc/examples", regexp = ".*\\.gnucash$")
	void validateDocExamples(Path path) throws SAXException, IOException {
		doValidate(path);
	}

	@ParameterizedTest(name = "{index}{0}")
	@PathsSource(path = "src/test/resources", regexp = ".*xml\\.gnucash$")
	void validateResources(Path path) throws SAXException, IOException {
		doValidate(path);
	}

	@ParameterizedTest(name = "{index}{0}")
	@PathsSource(path = "src/test/resources", regexp = ".*xml\\.gnucash$")
	void expandResources(Path path) throws SAXException, IOException {
		doCopy(path);
	}

	private void doValidate(Path path) throws SAXException, IOException {
		InputStream in = GnuCashXMLSource.openStream(path.toFile());
		Validator validator = schema.newValidator();

		// StreamSource xmlStreamSource = new StreamSource(in);
		SAXSource xmlStreamSource = new SAXSource(new NamespaceFilter(XMLReaderFactory.createXMLReader()),
				new InputSource(in));

		validator.validate(xmlStreamSource);
	}

	protected static class NamespaceFilter extends XMLFilterImpl {
		private String ignoredNamespace;

		public NamespaceFilter(XMLReader parent) {
			super(parent);
		}

		@Override
		public void startPrefixMapping(String prefix, String uri) throws SAXException {
			// do not pass through default name
			if (prefix.isEmpty()) {
				ignoredNamespace = uri;
			} else {
				super.startPrefixMapping(prefix, uri);
			}
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
			if (uri.equals(ignoredNamespace)) {
				uri = "";
			}
			super.startElement(uri, localName, qName, atts);
		}

	}

	private void doCopy(Path path) throws IOException {
		InputStream in = GnuCashXMLSource.openStream(path.toFile());
		Files.copy(in, new File(path.getFileName() + ".out.xml").toPath(), StandardCopyOption.REPLACE_EXISTING);
	}

}
