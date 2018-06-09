package hu.keve.jgc.dao.jdo;

import java.util.Objects;

public class GncLockJDO {
	String hostname;
	Integer pid;

	public GncLockJDO() {
	}

	public GncLockJDO(String hostname, long pid) {
		this.hostname = hostname;
		this.pid = (int) pid;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (null == obj) {
			return false;
		}
		if (obj instanceof GncLockJDO) {
			return Objects.equals(hostname, ((GncLockJDO) obj).hostname) && Objects.equals(pid, ((GncLockJDO) obj).pid);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hostname, pid);
	}
}