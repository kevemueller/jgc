package hu.keve.jgc.dao.jaxb2;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.gnucash.xml.ts.TsType;

public class TsTypeAdapter extends XmlAdapter<TsType, Date> {

	@Override
	public Date unmarshal(TsType v) throws Exception {
		return null;
	}

	@Override
	public TsType marshal(Date v) throws Exception {
		return null;
	}

}
