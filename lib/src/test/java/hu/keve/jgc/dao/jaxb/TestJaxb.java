package hu.keve.jgc.dao.jaxb;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;

import org.gnucash.xml.ObjectFactory;
import org.gnucash.xml.act.ActType;
import org.gnucash.xml.gnc.GncV2BookType;
import org.gnucash.xml.gnc.GncV2Type;
import org.gnucash.xml.split.SplitType;
import org.gnucash.xml.trn.TrnType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.xml.sax.SAXException;

import hu.keve.jgc.xml.GnuCashXMLSource;

public class TestJaxb {

	private static Schema schema;

	@BeforeAll
	static void beforeAll() throws SAXException {
		schema = GnuCashXMLSource.getSchema();
	}

	@ParameterizedTest(name = "{index} => file=''{0}''")
	@ValueSource(strings = { "CJ.xml", "PhA.xml", "Simple.xml" })
	// @ValueSource(strings = { "Simple.xml" })
	void jaxbLoadFile(String fileName) throws SAXException, IOException, JAXBException {
		File file = new File(fileName);
		assertTrue(file.exists());

		InputStream in = GnuCashXMLSource.openStream(file);
		Unmarshaller unmarshaller = GCUtilJAXB.getUnmarshaller();
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
		Unmarshaller unmarshaller = GCUtilJAXB.getUnmarshaller();
		GncV2Type gncv2 = ((JAXBElement<GncV2Type>) unmarshaller.unmarshal(in)).getValue();

		FileOutputStream out = new FileOutputStream(fileName.replace(".xml", ".out.xml"));

		Marshaller marshaller = GCUtilJAXB.getMarshaller();

		JAXBElement<GncV2Type> element = new ObjectFactory().createGncV2(gncv2);
		marshaller.marshal(element, out);
	}
}
