package hu.keve.jgc.dao.jdo;

import java.time.LocalDate;
import java.util.Collection;

import hu.keve.jgc.dao.Schedxaction;

public final class SchedxactionJDO extends AbstractGuidTypeJDO implements Schedxaction {
	String name;
	boolean enabled;

	LocalDate startDate;
	LocalDate endDate;
	LocalDate lastOccur;
	int numOccur;
	int remOccur;
	boolean autoCreate;
	boolean autoNotify;
	short advanceCreateDays;
	short advanceRemindDays;
	short instanceCount;
	AccountJDO templateAccount;
	Collection<RecurrenceJDO> recurrences;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#isEnabled()
	 */
	@Override
	public Boolean isEnabled() {
		return enabled;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#getStartDate()
	 */
	@Override
	public LocalDate getStartDate() {
		return startDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#getEndDate()
	 */
	@Override
	public LocalDate getEndDate() {
		return endDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#getLastOccur()
	 */
	@Override
	public LocalDate getLastOccur() {
		return lastOccur;
	}

	public int getNumOccur() {
		return numOccur;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#getRemOccur()
	 */
	@Override
	public Integer getRemOccur() {
		return remOccur;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#isAutoCreate()
	 */
	@Override
	public Boolean isAutoCreate() {
		return autoCreate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#isAutoNotify()
	 */
	@Override
	public Boolean isAutoCreateNotify() {
		return autoNotify;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#getAdvCreation()
	 */
	@Override
	public short getAdvanceCreateDays() {
		return advanceCreateDays;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#getAdvNotify()
	 */
	@Override
	public short getAdvanceRemindDays() {
		return advanceRemindDays;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#getInstanceCount()
	 */
	@Override
	public short getInstanceCount() {
		return instanceCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#getTemplateAct()
	 */
	@Override
	public AccountJDO getTemplateAccount() {
		return templateAccount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Schedxaction#getRecurrences()
	 */
	@Override
	public Collection<RecurrenceJDO> getRecurrences() {
		return recurrences;
	}

}
