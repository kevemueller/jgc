package hu.keve.jgc.dao.jaxb.adapters;

import org.gnucash.xml.IdTypeEnum;
import org.gnucash.xml.order.OrderRefType;
import org.gnucash.xml.order.OrderType;

import hu.keve.jgc.util.jaxb.AbstractIDREF2Adapter;
import hu.keve.jgc.util.jaxb.IDResolver2;

public class OrderRefTypeAdapter extends AbstractIDREF2Adapter<OrderRefType, OrderType> {
	public OrderRefTypeAdapter(final IDResolver2 resolver) {
		super(resolver);
	}

	@Override
	public OrderRefType marshal(OrderType v) throws Exception {
		if (null == v) {
			return null;
		}
		OrderRefType ref = new OrderRefType();
		ref.setType(IdTypeEnum.GUID);
		ref.setValue(v.getGuid());
		return ref;
	}

	@Override
	public OrderType unmarshal(OrderRefType v) throws Exception {
		return resolver.resolveBack(v.getValue(), OrderType.class);
	}
}
