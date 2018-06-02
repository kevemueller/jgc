package hu.keve.jgc.util.jaxb;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

import javax.xml.bind.ValidationEventHandler;

import org.xml.sax.SAXException;

/**
 * Special IDResolver that handles multiple XMLId's on the same object.
 * 
 * @author keve
 *
 */
public final class CompoundIDResolver extends IDResolver2 {
	private final String compoundKeyFormat;

	public CompoundIDResolver(final String compoundKeyFormat) {
		this.compoundKeyFormat = compoundKeyFormat;
	}

	public String compound(final String id1, final String id2) {
		return String.format(compoundKeyFormat, id1, id2);
	}

	@SuppressWarnings("unchecked")
	public <T> T resolveBack(final Class<T> targetType, final String id1, final String id2) {
		T obj = (T) idToObjectMap.get(compound(id1, id2));
		if (null == obj) {
			throw new UnsupportedOperationException("Cannot handle forward reference!");
		}
		return obj;
	}

	@Override
	public void startDocument(ValidationEventHandler eventHandler) throws SAXException {
		if (idToObjectMap != null) {
			idToObjectMap.clear();
		}
	}

	@Override
	public void bind(String id, final Object obj) {
		if (idToObjectMap == null) {
			idToObjectMap = new HashMap<String, Object>();
		}
		for (Iterator<Entry<String, Object>> iter = idToObjectMap.entrySet().iterator(); iter.hasNext();) {
			Entry<String, Object> e = iter.next();
			if (obj == e.getValue()) { // we use strict pointer equality for a purpose
//				System.err.println("additional key element");
				id = compound(e.getKey(), id);
				iter.remove();
				break;
			}
		}
		System.err.println("bind:" + id + " -> " + obj);
		idToObjectMap.put(id, obj);
	}

	@Override
	public Callable<?> resolve(final String id, final Class targetType) {
		System.err.println("resolve:" + id + " -> " + targetType);
		return new Callable<Object>() {
			public Object call() throws Exception {
				return null == idToObjectMap ? null : idToObjectMap.get(id);
			}
		};
	}
}