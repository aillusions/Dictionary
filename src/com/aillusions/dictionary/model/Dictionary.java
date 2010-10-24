package com.aillusions.dictionary.model;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("dictionary")
public class Dictionary {
	
	@XStreamAlias("working-list")
	List<Pair> pairs;
	
	@XStreamAlias("removed-list")
	List<Pair> trash;
	
	public List<Pair> getPairs() {
		return pairs;
	}
	public void setPairs(List<Pair> pairs) {
		this.pairs = pairs;
	}
	public List<Pair> getTrash() {
		return trash;
	}
	public void setTrash(List<Pair> trash) {
		this.trash = trash;
	}
	
	
}
