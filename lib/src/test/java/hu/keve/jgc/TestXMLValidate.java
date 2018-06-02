package hu.keve.jgc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;

import org.gnucash.xml.ObjectFactory;
import org.gnucash.xml.act.ActType;
import org.gnucash.xml.gnc.GncV2BookType;
import org.gnucash.xml.gnc.GncV2Type;
import org.gnucash.xml.split.SplitType;
import org.gnucash.xml.trn.TrnType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.xml.sax.SAXException;

public final class TestXMLValidate {
	private static Schema schema;

	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { {} });
	};

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

	@ParameterizedTest(name = "{index} => file=''{0}''")
	@ValueSource(strings = { "CJ.xml", "PhA.xml", "Simple.xml" })
	//@ValueSource(strings = { "Simple.xml" })
	void jaxbLoadFile(String fileName) throws SAXException, IOException, JAXBException {
		File file = new File(fileName);
		assertTrue(file.exists());
		
		InputStream in = GnuCashXMLSource.openStream(file);
		Unmarshaller unmarshaller=GnuCashXMLSource.getUnmarshaller();
		GncV2Type gncv2 = ((JAXBElement<GncV2Type>) unmarshaller.unmarshal(in)).getValue();

		GncV2BookType book = gncv2.getBook();
		for (ActType act : book.getAccount()) {
			System.out.println(act.getName() + " " + act.getGuid() + " "
					+ (null == act.getParent() ? "" : act.getParent().getName()) + act.getCommodity());
		}

		for (TrnType trn : book.getTransaction()) {
			System.out.println(trn.getDescription() + " " + trn.getGuid());
			for (SplitType split : trn.getSplits()) {
				System.out.println(split.getMemo() + " " + split.getValue() + " " + split.getAccount().getName());
			}
		}
		// System.out.println(gncv2);
	}

	@ParameterizedTest(name = "{index} => file=''{0}''")
	@ValueSource(strings = { "CJ.xml", "PhA.xml", "Simple.xml" })
	void jaxbWriteFile(String fileName) throws SAXException, IOException, JAXBException {
		File file = new File(fileName);
		assertTrue(file.exists());

		InputStream in = GnuCashXMLSource.openStream(file);
		Unmarshaller unmarshaller=GnuCashXMLSource.getUnmarshaller();
		GncV2Type gncv2 = ((JAXBElement<GncV2Type>) unmarshaller.unmarshal(in)).getValue();

		FileOutputStream out = new FileOutputStream(fileName.replace(".xml", ".out.xml"));

		Marshaller marshaller=GnuCashXMLSource.getMarshaller();

		JAXBElement<GncV2Type> element = new ObjectFactory().createGncV2(gncv2);
		marshaller.marshal(element, out);
	}

	// File resource = new File("CJ.xml");
	// URL resource = new
	// File("c:/Users/keve/ownCloud/priv/Accounting/AccountingCJ.gnucash").toURI().toURL();

	// File resource = new File("PhA.xml");
	// GnuCashFileSource.copy(resource,
	// new
	// File("c:/Users/keve/ownCloud/work/PhönixAdvice/Accounting/PhA2018_v2.gnucash"));
}
