package hu.keve.jgc.dao.jaxb.adapters;

import org.gnucash.xml.IdTypeEnum;
import org.gnucash.xml.trn.TrnRefType;
import org.gnucash.xml.trn.TrnType;

import hu.keve.jgc.util.jaxb.AbstractIDREF2Adapter;
import hu.keve.jgc.util.jaxb.IDResolver2;

public class TrnRefTypeAdapter extends AbstractIDREF2Adapter<TrnRefType, TrnType> {
	public TrnRefTypeAdapter(final IDResolver2 resolver) {
		super(resolver);
	}

	@Override
	public TrnRefType marshal(TrnType v) throws Exception {
		if (null == v) {
			return null;
		}
		TrnRefType ref = new TrnRefType();
		ref.setType(IdTypeEnum.GUID);
		ref.setValue(v.getGuid());
		return ref;
	}

	@Override
	public TrnType unmarshal(TrnRefType v) throws Exception {
		return resolver.resolveBack(v.getValue(), TrnType.class);
	}
}
