package hu.keve.jgc.dao.jaxb.adapters;

import org.gnucash.xml.IdTypeEnum;
import org.gnucash.xml.billterm.BilltermRefType;
import org.gnucash.xml.billterm.BilltermType;

import hu.keve.jgc.util.jaxb.AbstractIDREF2Adapter;
import hu.keve.jgc.util.jaxb.IDResolver2;

public class BilltermRefTypeAdapter extends AbstractIDREF2Adapter<BilltermRefType, BilltermType> {
	public BilltermRefTypeAdapter(final IDResolver2 resolver) {
		super(resolver);
	}

	@Override
	public BilltermRefType marshal(BilltermType v) throws Exception {
		if (null == v) {
			return null;
		}
		BilltermRefType ref = new BilltermRefType();
		ref.setType(IdTypeEnum.GUID);
		ref.setValue(v.getGuid());
		return ref;
	}

	@Override
	public BilltermType unmarshal(BilltermRefType v) throws Exception {
		return resolver.resolveBack(v.getValue(), BilltermType.class);
	}
}
