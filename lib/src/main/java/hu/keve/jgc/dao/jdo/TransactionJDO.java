package hu.keve.jgc.dao.jdo;

import java.util.Collection;
import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import hu.keve.jgc.dao.Transaction;

@PersistenceCapable(table = "transactions")
public class TransactionJDO implements Transaction {
	@PrimaryKey
	String guid;

	@Column(name = "currency_guid")
	CommodityJDO currency;
	String num;

	Date postDate;
	Date enterDate;
	String description;

	@Element(column = "obj_guid")
	Collection<SlotJDO> slots;

	@Element(column = "tx_guid", mappedBy = "tx")
	Collection<SplitJDO> splits;

	@Override
	public String getGuid() {
		return guid;
	}

	@Override
	public CommodityJDO getCurrency() {
		return currency;
	}

	@Override
	public String getNum() {
		return num;
	}

	@Override
	public Date getDatePosted() {
		return postDate;
	}

	@Override
	public Date getDateEntered() {
		return enterDate;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Collection<SlotJDO> getSlots() {
		return slots;
	}

	public Collection<SplitJDO> getSplits() {
		return splits;
	}

}
