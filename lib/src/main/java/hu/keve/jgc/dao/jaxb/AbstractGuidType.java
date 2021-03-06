package hu.keve.jgc.dao.jaxb;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import org.gnucash.xml.IdType;
import org.gnucash.xml.slot.SlotsType;

import hu.keve.jgc.dao.GuidType;
import hu.keve.jgc.dao.jaxb.unwrapper.GuidUnwrapper;
import hu.keve.jgc.dao.jaxb.unwrapper.SlotsUnwrapper;

public abstract class AbstractGuidType implements GuidType, BookRef, SlotsUnwrapper, GuidUnwrapper {
	protected AbstractGnuCashJAXB root;
	private IdType internalGuid = GCUtilJAXB.createGuid();

	@Override
	public final String getGuid() {
		return GuidUnwrapper.super.getGuid();
	}

	@XmlTransient
	public final IdType getInternalGuid() {
		return internalGuid;
	}

	@XmlTransient
	public final AbstractGnuCashJAXB getGuidRoot() {
		return root;
	}

	public final void setGuidRoot(AbstractGnuCashJAXB root) {
		assert (null == this.root || this.root == root);
		this.root = root;
	}

	@Override
	public final BookJAXB getBook() {
		return root.getBook();
	}

	@Override
	public Map<String, Object> getSlots() {
		HashMap<String, Object> slots = new HashMap<String, Object>();
		SlotsType wrappedSlots = getWrappedSlots();
		if (null != wrappedSlots) {
			wrappedSlots.getSlot().forEach(slot -> {
				slots.put(slot.getName(), slot.getValue());
			});
		}
		return slots;
	}
}
