package hu.keve.jgc.dao.jaxb.adapters;

import org.gnucash.xml.IdTypeEnum;
import org.gnucash.xml.invoice.InvoiceRefType;
import org.gnucash.xml.invoice.InvoiceType;

import hu.keve.jgc.util.jaxb.AbstractIDREF2Adapter;
import hu.keve.jgc.util.jaxb.IDResolver2;

public class InvoiceRefTypeAdapter extends AbstractIDREF2Adapter<InvoiceRefType, InvoiceType> {
	public InvoiceRefTypeAdapter(final IDResolver2 resolver) {
		super(resolver);
	}

	@Override
	public InvoiceRefType marshal(InvoiceType v) throws Exception {
		if (null == v) {
			return null;
		}
		InvoiceRefType ref = new InvoiceRefType();
		ref.setType(IdTypeEnum.GUID);
		ref.setValue(v.getGuid());
		return ref;
	}

	@Override
	public InvoiceType unmarshal(InvoiceRefType v) throws Exception {
		return resolver.resolveBack(v.getValue(), InvoiceType.class);
	}
}
