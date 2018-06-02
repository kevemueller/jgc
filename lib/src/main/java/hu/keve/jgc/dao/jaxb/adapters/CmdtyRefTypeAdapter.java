package hu.keve.jgc.dao.jaxb.adapters;

import org.gnucash.xml.cmdty.CmdtyRefType;
import org.gnucash.xml.cmdty.CmdtyType;

import hu.keve.jgc.util.jaxb.AbstractIDREF2Adapter;
import hu.keve.jgc.util.jaxb.IDResolver2;

public class CmdtyRefTypeAdapter extends AbstractIDREF2Adapter<CmdtyRefType, CmdtyType> {
	public CmdtyRefTypeAdapter(final IDResolver2 resolver) {
		super(resolver);
	}

	@Override
	public CmdtyRefType marshal(CmdtyType v) throws Exception {
		if (null == v) {
			return null;
		}
		CmdtyRefType ref = new CmdtyRefType();
		ref.setSpace(v.getNamespace());
		ref.setId(v.getMnemonic());
		return ref;
	}

	@Override
	public CmdtyType unmarshal(CmdtyRefType v) throws Exception {
		return resolver.resolveBack("X" + v.getSpace() + "//" + v.getId(), CmdtyType.class);
	}

	public static String makeId(CmdtyType target) {
		return "X" + target.getNamespace() + "//" + target.getMnemonic();
	}
}
