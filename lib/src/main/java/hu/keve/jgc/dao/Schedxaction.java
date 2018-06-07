package hu.keve.jgc.dao;

import java.time.LocalDate;
import java.util.Collection;

public interface Schedxaction {

	String getName();

	Boolean isEnabled();

	LocalDate getStartDate();

	LocalDate getEndDate();

	LocalDate getLastOccur();

	// int getNumOccur();

	Integer getRemOccur();

	Boolean isAutoCreate();

	Boolean isAutoCreateNotify();

	short getAdvanceCreateDays();

	short getAdvanceRemindDays();

	short getInstanceCount();

	Account getTemplateAccount();

	Collection<? extends Recurrence> getRecurrences();
}