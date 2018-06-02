package hu.keve.jgc.dao.jaxb.adapters;

import org.gnucash.xml.IdTypeEnum;
import org.gnucash.xml.lot.LotRefType;
import org.gnucash.xml.lot.LotType;

import hu.keve.jgc.util.jaxb.AbstractIDREF2Adapter;
import hu.keve.jgc.util.jaxb.IDResolver2;

public class LotRefTypeAdapter extends AbstractIDREF2Adapter<LotRefType, LotType> {
	public LotRefTypeAdapter(final IDResolver2 resolver) {
		super(resolver);
	}

	@Override
	public LotRefType marshal(LotType v) throws Exception {
		if (null == v) {
			return null;
		}
		LotRefType ref = new LotRefType();
		ref.setType(IdTypeEnum.GUID);
		ref.setValue(v.getGuid());
		return ref;
	}

	@Override
	public LotType unmarshal(LotRefType v) throws Exception {
		return resolver.resolveBack(v.getValue(), LotType.class);
	}
}
