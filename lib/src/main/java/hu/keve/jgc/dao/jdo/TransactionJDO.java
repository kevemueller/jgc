package hu.keve.jgc.dao.jdo;

import java.time.LocalDateTime;
import java.util.Collection;

import hu.keve.jgc.dao.Transaction;

public final class TransactionJDO extends AbstractGuidTypeJDO implements Transaction {
	CommodityJDO currency;
	String num;

	LocalDateTime postDate;
	LocalDateTime enterDate;
	String description;

	Collection<SplitJDO> splits;

	@Override
	public CommodityJDO getCurrency() {
		return currency;
	}

	@Override
	public String getNum() {
		return num;
	}

	@Override
	public LocalDateTime getDatePosted() {
		return postDate;
	}

	@Override
	public LocalDateTime getDateEntered() {
		return enterDate;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public Collection<SplitJDO> getSplits() {
		return splits;
	}

}
