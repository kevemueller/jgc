package hu.keve.jgc.dao.jaxb2;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.gnucash.xml.GdateType;

public class GdateTypeAdapter extends XmlAdapter<GdateType, Date> {

	@Override
	public Date unmarshal(GdateType v) throws Exception {
		return null;
	}

	@Override
	public GdateType marshal(Date v) throws Exception {
		return null;
	}

}
