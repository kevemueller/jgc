package hu.keve.jgc.dao.jaxb.adapters;

import org.gnucash.xml.IdTypeEnum;
import org.gnucash.xml.taxtable.TaxtableRefType;
import org.gnucash.xml.taxtable.TaxtableType;

import hu.keve.jgc.util.jaxb.AbstractIDREF2Adapter;
import hu.keve.jgc.util.jaxb.IDResolver2;

public class TaxtableRefTypeAdapter extends AbstractIDREF2Adapter<TaxtableRefType, TaxtableType> {
	public TaxtableRefTypeAdapter(final IDResolver2 resolver) {
		super(resolver);
	}

	@Override
	public TaxtableRefType marshal(TaxtableType v) throws Exception {
		if (null == v) {
			return null;
		}
		TaxtableRefType ref = new TaxtableRefType();
		ref.setType(IdTypeEnum.GUID);
		ref.setValue(v.getGuid());
		return ref;
	}

	@Override
	public TaxtableType unmarshal(TaxtableRefType v) throws Exception {
		return resolver.resolveBack(v.getValue(), TaxtableType.class);
	}
}
