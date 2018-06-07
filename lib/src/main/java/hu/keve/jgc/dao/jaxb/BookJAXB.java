package hu.keve.jgc.dao.jaxb;

import java.time.LocalDateTime;
import java.util.List;

import org.gnucash.xml.act.ActType;
import org.gnucash.xml.cmdty.CmdtyType;
import org.gnucash.xml.gnc.CountData;
import org.gnucash.xml.trn.TrnType;

import hu.keve.jgc.dao.Account;
import hu.keve.jgc.dao.Account.AccountTypes;
import hu.keve.jgc.dao.Book;
import hu.keve.jgc.dao.Commodity;

public interface BookJAXB extends Book {
	AbstractGnuCashJAXB getGuidRoot();

	@Override
	default Account getRootAccount() {
		for (ActType account : getAllAccounts()) {
			if (Account.AccountTypes.ROOT == account.getAccountType() && null != account.getCommodity()) {
				return account;
			}
		}
		throw new IllegalArgumentException("must have a ROOT account");
	}

	@Override
	default Account getRootTemplate() {
		for (ActType account : getAllAccounts()) {
			if (Account.AccountTypes.ROOT == account.getAccountType() && null == account.getCommodity()) {
				return account;
			}
		}

		ActType account = createAccount(null, "Template ROOT", Account.AccountTypes.ROOT, null);
		return account;
	}

	@Override
	List<ActType> getAllAccounts();

	@Override
	List<CmdtyType> getAllCommodities();

	@Override
	default Iterable<TrnType> getTransactionsBetween(LocalDateTime fromInclusive, LocalDateTime toExclusive) {
		return getTransaction().stream().filter(t -> {
			LocalDateTime dp = t.getDatePosted();
			return (null == fromInclusive || dp.isEqual(fromInclusive) || dp.isAfter(fromInclusive))
					&& (null == toExclusive || dp.isBefore(toExclusive));
		})::iterator;
	}

	List<CountData> getCountData();

	List<TrnType> getTransaction();

	default CmdtyType add(Commodity commodity) {
		AbstractGnuCashJAXB root = getGuidRoot();
		CmdtyType cmdty;

		cmdty = null == root ? null : root.resolve(commodity.getGuid(), CmdtyType.class);
		if (null != cmdty) {
			return cmdty;
		}
		for (CmdtyType existingCmdty : getAllCommodities()) {
			if (existingCmdty.equalsUnique(commodity)) {
				// merge
				return existingCmdty;
			}
		}

		if (commodity instanceof CmdtyType) {
			cmdty = (CmdtyType) commodity;
		} else {
			cmdty = GCUtilJAXB.cmdtyObjectFactory.createCmdtyType();
			;
			cmdty.copy(commodity);
		}
		getAllCommodities().add(cmdty);
		GCUtilJAXB.increment(getCountData(), GCUtilJAXB.GC_CD_COMMODITY);
		getGuidRoot().register(cmdty);
		return cmdty;
	}

	default CmdtyType createCommodity(String space, String mnemonic) {
		CmdtyType cmdtyType = GCUtilJAXB.cmdtyObjectFactory.createCmdtyType();
		cmdtyType.setVersion(GCUtilJAXB.GC_VERSION);
		cmdtyType.setNamespace(space);
		cmdtyType.setMnemonic(mnemonic);
		return add(cmdtyType);
	}

	default ActType createAccount(Account parent, String name, AccountTypes type, Commodity commodity) {
		ActType account = GCUtilJAXB.actObjectFactory.createActType();
		account.setWrappedGuid(GCUtilJAXB.createGuid());
		account.setVersion(GCUtilJAXB.GC_VERSION);
		account.setName(name);
		account.setAccountType(type);
		account.setCommodity((CmdtyType) commodity);
		account.setParent((ActType) parent);
		getAllAccounts().add(account);
		GCUtilJAXB.increment(getCountData(), GCUtilJAXB.GC_CD_ACCOUNT);
		return account;
	}

}
