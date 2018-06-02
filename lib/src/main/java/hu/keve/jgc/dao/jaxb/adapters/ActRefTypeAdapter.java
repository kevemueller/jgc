package hu.keve.jgc.dao.jaxb.adapters;

import org.gnucash.xml.IdTypeEnum;
import org.gnucash.xml.act.ActRefType;
import org.gnucash.xml.act.ActType;

import hu.keve.jgc.util.jaxb.AbstractIDREF2Adapter;
import hu.keve.jgc.util.jaxb.IDResolver2;

public class ActRefTypeAdapter extends AbstractIDREF2Adapter<ActRefType, ActType> {
	public ActRefTypeAdapter(final IDResolver2 resolver) {
		super(resolver);
	}

	@Override
	public ActRefType marshal(ActType v) throws Exception {
		if (null == v) {
			return null;
		}
		ActRefType ref = new ActRefType();
		ref.setType(IdTypeEnum.GUID);
		ref.setValue(v.getGuid());
		return ref;
	}

	@Override
	public ActType unmarshal(ActRefType v) throws Exception {
		return resolver.resolveBack(v.getValue(), ActType.class);
	}
}
