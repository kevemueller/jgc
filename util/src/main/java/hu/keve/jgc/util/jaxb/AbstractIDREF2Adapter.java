package hu.keve.jgc.util.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public abstract class AbstractIDREF2Adapter<ReferenceType, ActualType> extends XmlAdapter<ReferenceType, ActualType> {
	protected final IDResolver2 resolver;

	public AbstractIDREF2Adapter(final IDResolver2 resolver) {
		this.resolver = resolver;
	}
}
