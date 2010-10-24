package com.aillusions.dictionary.dao;

import com.aillusions.dictionary.model.Pair;

public interface PairDao {
	void load();
	void save();	
	Pair[] getAllPairs();
	String[] getAllEnglish();
	void addNew(Pair pair);
	Pair addNew(String eng);
	Pair getPair(String eng);
	Pair remove(Pair pair);
	void shuffle();
}
