package hu.keve.jgc;

public interface Visitable {
	void visit(JgcVisitor visitor, Object context);
}
