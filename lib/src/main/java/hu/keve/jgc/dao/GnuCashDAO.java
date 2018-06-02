package hu.keve.jgc.dao;

public interface GnuCashDAO {
	Book getBook();

	Book createBook(String rootCommoditySpace, String rootCommodityMnemonic);
}
