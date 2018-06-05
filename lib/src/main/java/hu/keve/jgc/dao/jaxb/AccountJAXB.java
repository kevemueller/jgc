package hu.keve.jgc.dao.jaxb;

import java.util.Collection;
import java.util.HashSet;

import org.gnucash.xml.act.ActType;
import org.gnucash.xml.slot.SlotType;

import hu.keve.jgc.dao.Account;
import hu.keve.jgc.dao.jaxb.unwrapper.GuidUnwrapper;
import hu.keve.jgc.dao.jaxb.unwrapper.SlotsUnwrapper;

public interface AccountJAXB extends Account, BookRef, GuidUnwrapper, SlotsUnwrapper {

	@Override
	default String getGuid() {
		return GuidUnwrapper.super.getGuid();
	}

	@Override
	default Collection<SlotType> getSlots() {
		return SlotsUnwrapper.super.getSlots();
	}

	@Override
	default Iterable<ActType> getChildren() {
		HashSet<ActType> children = new HashSet<ActType>();
		BookJAXB book = getBook();
		book.getAllAccounts().forEach(a -> {
			if (this.equals(a.getParent())) {
				children.add(a);
			}
		});
		return children;
	}
}
