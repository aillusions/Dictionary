package com.myjavaserver.aillusions.model;

public interface PairDao {
	void load();
	void save();	
	Pair[] getAllPairs();
	String[] getAllEnglish();
	void addNew(Pair pair);
	Pair addNew(String eng);
	Pair getPair(String eng);
	Pair remove(Pair pair);
}
