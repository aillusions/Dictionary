package com.aillusions.dictionary.model;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("dictionary")
public class Dictionary {
	
	@XStreamAlias("working-list")
	private List<Pair> pairs;
	
	@XStreamAlias("removed-list")
	private List<Pair> trash;
	
	@XStreamAlias("display-name")
	private String displayName;
	
	@XStreamAsAttribute()
	private String id;
	
	public List<Pair> getPairs() {
		if(pairs == null){
			pairs = new ArrayList<Pair>();
		}
		return pairs;
	}
	public void setPairs(List<Pair> pairs) {
		this.pairs = pairs;
	}
	public List<Pair> getTrash() {
		
		if(trash == null){
			trash = new ArrayList<Pair>();
		}
		return trash;
	}
	public void setTrash(List<Pair> trash) {
		this.trash = trash;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
}
