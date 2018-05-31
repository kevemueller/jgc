package hu.keve.jgc.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;

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
 * @param <ValueType>
 * @param <BoundType>
 *
 */
public abstract class CompoundRefTypeAdapter<ValueType, BoundType> extends XmlAdapter<ValueType, BoundType> {
	protected final CompoundIDResolver compoundIdResolver;

	public CompoundRefTypeAdapter(final CompoundIDResolver compoundIdResolver) {
		this.compoundIdResolver = compoundIdResolver;
	}
}
