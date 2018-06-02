package hu.keve.jgc.util.jaxb;

import java.util.HashMap;
import java.util.concurrent.Callable;

import javax.xml.bind.ValidationEventHandler;

import org.xml.sax.SAXException;

import com.sun.xml.bind.IDResolver;

/**
 * Simple IDResolver that can be used by Adapters.
 * 
 * @author keve
 *
 */
public class IDResolver2 extends IDResolver {
	protected HashMap<String, Object> idToObjectMap = null;

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
//		System.err.println("bind:" + id + " -> " + obj);
		idToObjectMap.put(id, obj);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Callable<?> resolve(final String id, final Class targetType) {
//		System.err.println("resolve:" + id + " -> " + targetType);
		return new Callable<Object>() {
			public Object call() throws Exception {
				return null == idToObjectMap ? null : idToObjectMap.get(id);
			}
		};
	}

	@SuppressWarnings("unchecked")
	public <T> T resolveBack(String id, final Class<T> targetType) {
		if (null == idToObjectMap) {
			throw new IllegalArgumentException("Illegal forward resolution.");
		}
		Object obj = idToObjectMap.get(id);
		if (null == obj) {
			throw new IllegalArgumentException("Illegal forward resolution.");			
		}
		return (T) obj;
	}
}