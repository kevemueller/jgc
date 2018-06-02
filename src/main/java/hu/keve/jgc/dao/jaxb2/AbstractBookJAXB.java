package hu.keve.jgc.dao.jaxb2;

import java.util.List;

import org.gnucash.xml.act.ActType;
import org.gnucash.xml.cmdty.CmdtyType;
import org.gnucash.xml.gnc.CountData;
import org.gnucash.xml.slot.SlotType;
import org.gnucash.xml.slot.SlotsType;

import hu.keve.jgc.dao.Account;
import hu.keve.jgc.dao.Account.AccountTypes;
import hu.keve.jgc.dao.Book;
import hu.keve.jgc.dao.Commodity;

public abstract class AbstractBookJAXB extends AbstractBaseJAXB implements Book {
	public abstract void setWrappedSlots(SlotsType value);

	public abstract SlotsType getWrappedSlots();

	public List<SlotType> getSlots() {
		SlotsType wrappedSlots = getWrappedSlots();
		if (null == wrappedSlots) {
			wrappedSlots = new SlotsType();
			setWrappedSlots(wrappedSlots);
		}
		return wrappedSlots.getSlot();
	}

	// fake abstract
	protected List<ActType> getAccount() {
		return null;
	}

	// fake abstract
	protected List<CmdtyType> getCommodity() {
		return null;
	}

	// fake abstract
	public List<CountData> getCountData() {
		return null;
	}

	@Override
	public CmdtyType createCommodity(String space, String mnemonic) {
		CmdtyType cmdtyType = cmdtyObjectFactory.createCmdtyType();
		cmdtyType.setVersion(GC_VERSION);
		cmdtyType.setNamespace(space);
		cmdtyType.setMnemonic(mnemonic);
		getCommodity().add(cmdtyType);
		increment(GC_CD_COMMODITY);
		return cmdtyType;
	}
	
	@Override
	public ActType createAccount(Account parent, String name, AccountTypes type, Commodity commodity) {
		ActType account = actObjectFactory.createActType();
		account.setId(createGuid().getValue());
		account.setVersion(GC_VERSION);
		account.setName(name);
		account.setAccountType(type);
		account.setCommodity((CmdtyType) commodity);
		account.setParent((ActType) parent);
		getAccount().add(account);
		increment(GC_CD_ACCOUNT);
		return account;
	}

	@Override
	public Account getRootAccount() {
		for (ActType account : getAccount()) {
			if (Account.AccountTypes.ROOT == account.getAccountType() && null != account.getCommodity()) {
				return account;
			}
		}
		throw new IllegalArgumentException("must have a ROOT account");
	}

	@Override
	public Account getRootTemplate() {
		for (ActType account : getAccount()) {
			if (Account.AccountTypes.ROOT == account.getAccountType() && null == account.getCommodity()) {
				return account;
			}
		}

		ActType account = createAccount(null, "Template ROOT", Account.AccountTypes.ROOT, null);
		return account;
	}

}
