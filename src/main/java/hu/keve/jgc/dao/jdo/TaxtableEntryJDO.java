package hu.keve.jgc.dao.jdo;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import hu.keve.jgc.dao.TaxtableEntry;
import hu.keve.jgc.dao.TaxtableEntry.TaxtableEntryType;
import hu.keve.jgc.util.Fraction;

@PersistenceCapable(table = "taxtable_entries")
public class TaxtableEntryJDO implements TaxtableEntry {
	@PrimaryKey
	int id;

	@Column(name = "account")
	AccountJDO account;
	@Persistent(defaultFetchGroup = "true")
	@Embedded(members = { @Persistent(name = "num", column = "amount_num"),
			@Persistent(name = "denom", column = "amount_denom") })
	Fraction amount;
	@Extension(vendorName = "datanucleus", key = "enum-value-getter", value = "toIntValue")
	TaxtableEntryType type;
	
	
	public int getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.TaxtableEntry#getAccount()
	 */
	@Override
	public AccountJDO getAccount() {
		return account;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.TaxtableEntry#getAmount()
	 */
	@Override
	public Fraction getAmount() {
		return amount;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.TaxtableEntry#getType()
	 */
	@Override
	public TaxtableEntryType getType() {
		return type;
	}
	
	
}
