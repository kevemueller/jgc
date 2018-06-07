package hu.keve.jgc.util.jaxb;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NamespacePrefixMapper extends com.sun.xml.bind.marshaller.NamespacePrefixMapper {
	private final Map<String, String> prefixMap;
	private final Set<String> predeclaredSet;

	public NamespacePrefixMapper() {
		this.prefixMap = new HashMap<String, String>();
		this.predeclaredSet = new HashSet<String>();
	}

	public void add(final String namespaceUri, final String prefix, final boolean preDeclared) {
		prefixMap.put(namespaceUri, prefix);
		if (preDeclared) {
			predeclaredSet.add(namespaceUri);
		}
	}

	@Override
	public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
		String prefix = prefixMap.get(namespaceUri);
		if (null == prefix) {
			prefix = suggestion;
		}
		return prefix;
	}

	@Override
	public String[] getPreDeclaredNamespaceUris() {
		return predeclaredSet.toArray(new String[predeclaredSet.size()]);
	}

	// @Override
	// public String[] getPreDeclaredNamespaceUris2() {
	// String[] predeclared = new String[2 * predeclaredSet.size()];
	// int i = 0;
	// for (Entry<String, String> mapping : prefixMap.entrySet()) {
	// if (predeclaredSet.contains(mapping.getKey())) {
	// predeclared[i++] = mapping.getValue();
	// predeclared[i++] = mapping.getKey();
	// }
	// }
	// return predeclared;
	// }

}
