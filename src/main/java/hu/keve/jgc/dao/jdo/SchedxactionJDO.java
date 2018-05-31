package hu.keve.jgc.dao.jdo;

import java.util.Collection;
import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Convert;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import hu.keve.jgc.dao.Schedxaction;
import hu.keve.jgc.util.GCDateStringConverter;

@PersistenceCapable(table = "schedxactions")
public final class SchedxactionJDO implements Schedxaction {
	@PrimaryKey
	String guid;

	String name;
	boolean enabled;

	@Convert(GCDateStringConverter.class)
	Date startDate;
	@Convert(GCDateStringConverter.class)
	Date endDate;
	@Convert(GCDateStringConverter.class)
	Date lastOccur;
	int numOccur;
	int remOccur;
	boolean autoCreate;
	boolean autoNotify;
	@Column(name="adv_creation")
	short advanceCreateDays;
	@Column(name="adv_notify")
	short advanceRemindDays;
	short instanceCount;
	@Column(name = "template_act_guid")
	AccountJDO templateAccount;
	@Element(column = "obj_guid")
	Collection<RecurrenceJDO> recurrences;
	@Element(column = "obj_guid")
	Collection<SlotJDO> slots;

	public String getGuid() {
		return guid;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#isEnabled()
	 */
	@Override
	public Boolean isEnabled() {
		return enabled;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#getStartDate()
	 */
	@Override
	public Date getStartDate() {
		return startDate;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#getEndDate()
	 */
	@Override
	public Date getEndDate() {
		return endDate;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#getLastOccur()
	 */
	@Override
	public Date getLastOccur() {
		return lastOccur;
	}

	public int getNumOccur() {
		return numOccur;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#getRemOccur()
	 */
	@Override
	public Integer getRemOccur() {
		return remOccur;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#isAutoCreate()
	 */
	@Override
	public Boolean isAutoCreate() {
		return autoCreate;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#isAutoNotify()
	 */
	@Override
	public Boolean isAutoCreateNotify() {
		return autoNotify;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#getAdvCreation()
	 */
	@Override
	public short getAdvanceCreateDays() {
		return advanceCreateDays;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#getAdvNotify()
	 */
	@Override
	public short getAdvanceRemindDays() {
		return advanceRemindDays;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#getInstanceCount()
	 */
	@Override
	public short getInstanceCount() {
		return instanceCount;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#getTemplateAct()
	 */
	@Override
	public AccountJDO getTemplateAccount() {
		return templateAccount;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#getRecurrences()
	 */
	@Override
	public Collection<RecurrenceJDO> getRecurrences() {
		return recurrences;
	}

}
