package hu.keve.jgc.dao.jdo;

public class VersionJDO {
	String tableName;
	int tableVersion;

	public VersionJDO() {

	}

	public VersionJDO(String tableName, int tableVersion) {
		this.tableName = tableName;
		this.tableVersion = tableVersion;
	}
}