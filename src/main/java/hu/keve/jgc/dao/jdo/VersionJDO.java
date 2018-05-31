package hu.keve.jgc.dao.jdo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(table = "versions")
public class VersionJDO {
	@PrimaryKey
	String tableName;
	int tableVersion;
}