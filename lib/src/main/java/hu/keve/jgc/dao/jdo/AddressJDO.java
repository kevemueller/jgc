package hu.keve.jgc.dao.jdo;

import hu.keve.jgc.dao.Address;

public class AddressJDO implements Address {
	String name;
	String addr1;
	String addr2;
	String addr3;
	String addr4;
	String phone;
	String fax;
	String email;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Address#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Address#getAddr1()
	 */
	@Override
	public String getAddr1() {
		return addr1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Address#getAddr2()
	 */
	@Override
	public String getAddr2() {
		return addr2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Address#getAddr3()
	 */
	@Override
	public String getAddr3() {
		return addr3;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Address#getAddr4()
	 */
	@Override
	public String getAddr4() {
		return addr4;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Address#getAddrPhone()
	 */
	@Override
	public String getPhone() {
		return phone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Address#getAddrFax()
	 */
	@Override
	public String getFax() {
		return fax;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Address#getAddrEmail()
	 */
	@Override
	public String getEmail() {
		return email;
	}

}