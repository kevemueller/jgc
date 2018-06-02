package hu.keve.jgc.dao;

import java.util.Collection;
import java.util.Date;

public interface Schedxaction {

	String getName();

	Boolean isEnabled();

	Date getStartDate();

	Date getEndDate();

	Date getLastOccur();

//	int getNumOccur();

	Integer getRemOccur();

	Boolean isAutoCreate();

	Boolean isAutoCreateNotify();

	short getAdvanceCreateDays();

	short getAdvanceRemindDays();

	short getInstanceCount();

	Account getTemplateAccount();

	Collection<? extends Recurrence> getRecurrences();
}