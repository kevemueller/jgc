package hu.keve.jgc;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.gnucash.xml.ObjectFactory;
import org.gnucash.xml.gnc.GncV2Type;
import org.junit.jupiter.api.Test;

import hu.keve.jgc.dao.Account;
import hu.keve.jgc.dao.Book;
import hu.keve.jgc.dao.Commodity;
import hu.keve.jgc.dao.GnuCashDAO;

class TestXMLCreate {

	@Test
	void test() throws JAXBException {
		GnuCashDAO gc = new GncV2Type();
		Book book = gc.createBook("CURRENCY", "EUR");
		Commodity xtsCommodity = book.createCommodity("CURRENCY", "XTS");
		Account rootAccount = book.getRootAccount();
		book.createAccount(rootAccount, "Expenses", Account.AccountTypes.EXPENSE, xtsCommodity);

		Marshaller marshaller = GnuCashXMLSource.getMarshaller();
		JAXBElement<GncV2Type> element = new ObjectFactory().createGncV2((GncV2Type) gc);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(element, System.out);
	}
}
