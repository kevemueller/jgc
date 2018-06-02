package hu.keve.jgc.dao.jaxb;

import javax.xml.bind.annotation.XmlTransient;

import org.gnucash.xml.IdType;

public abstract class AbstractGuidType implements GWG {
	protected AbstractGnuCashJAXB root;
	private IdType internalGuid = GCUtilJAXB.createGuid();
	
	@XmlTransient
	public final IdType getInternalGuid() {
		return internalGuid;
	}

	@XmlTransient
	public final AbstractGnuCashJAXB getGuidRoot() {
		return root;
	}
	public final void setGuidRoot(AbstractGnuCashJAXB root) {
		assert(null==this.root || this.root==root);
		this.root = root;
	}			
}
