package hu.keve.jgc.dao.jaxb;

import java.util.Collection;

import org.gnucash.xml.slot.SlotType;

import hu.keve.jgc.dao.Account;

public interface AccountJAXB extends Account, GuidUnwrapper, SlotsUnwrapper {

	@Override
	default String getGuid() {
		return GuidUnwrapper.super.getGuid();
	}

	@Override
	default Collection<SlotType> getSlots() {
		return SlotsUnwrapper.super.getSlots();
	}

	// List<ActType> getAccount();
	//
	// @Override
	// default Account createAccount(String name, AccountTypes type, Commodity
	// commodity) {
	// ActType account = new ActType(); // actObjectFactory.createActType();
	// account.setId("abc"/*createGuid().getValue()*/);
	// account.setVersion("2.0.0"/*GC_VERSION*/);
	// account.setName(name);
	// account.setAccountType(type);
	// account.setCommodity((CmdtyType)commodity);
	// getAccount().add(account);
	//// increment(GC_CD_ACCOUNT);
	// return account;
	// }
}
