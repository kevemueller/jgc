package hu.keve.jgc.dao.jaxb2;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.gnucash.xml.IdType;
import org.gnucash.xml.IdTypeEnum;

public class IdTypeAdapter extends XmlAdapter<IdType, String> {

	@Override
	public IdType marshal(String v) throws Exception {
		IdType idType = new IdType();
		idType.setType(IdTypeEnum.GUID);
		idType.setValue(v);
		return idType;
	}

	@Override
	public String unmarshal(IdType v) throws Exception {
		return v.getValue();
	}

}
