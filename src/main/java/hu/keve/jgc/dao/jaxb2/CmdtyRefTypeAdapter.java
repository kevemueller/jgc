package hu.keve.jgc.dao.jaxb2;

import org.gnucash.xml.cmdty.CmdtyRefType;
import org.gnucash.xml.cmdty.CmdtyType;

import hu.keve.jgc.util.CompoundIDResolver;
import hu.keve.jgc.util.CompoundRefTypeAdapter;

/**
 * Special Adapter that works around the jaxb restriction of IDREF. IDREF is
 * hardcoded to use the text content of a node, which is empty for a composite
 * key with additional subelements.
 * 
 * We use the passed CompoundIDResolver to resolve our composite key to the
 * actual object. This XMLAdapter must be passed to Unmarshaller, it cannot be
 * created by the unmarshaller.
 * 
 * @author keve
 *
 */
public class CmdtyRefTypeAdapter extends CompoundRefTypeAdapter<CmdtyRefType, CmdtyType> {
	public CmdtyRefTypeAdapter(CompoundIDResolver compoundIdResolver) {
		super(compoundIdResolver);
	}

	@Override
	public CmdtyType unmarshal(CmdtyRefType cmdtyRef) throws Exception {
		return compoundIdResolver.resolve(CmdtyType.class, cmdtyRef.getSpace(), cmdtyRef.getId());
	}

	@Override
	public CmdtyRefType marshal(CmdtyType id) throws Exception {
		CmdtyRefType cmdtyRefType = new CmdtyRefType();
		cmdtyRefType.setSpace(id.getNamespace());
		cmdtyRefType.setId(id.getMnemonic());
		return cmdtyRefType;
	}
}
