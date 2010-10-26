package com.aillusions.dictionary.model;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("workspace")
public class Workspace {
	
	@XStreamAlias("dictionaries")
	private List<Dictionary> dictioanries;
	
	@XStreamAlias("default-dict-id")
	private  String defaultDictId;

	public List<Dictionary> getDictioanries() {
		if(dictioanries == null){
			dictioanries = new ArrayList<Dictionary>();
		}
		return dictioanries;
	}

	public void setDictioanries(List<Dictionary> dictioanries) {
		this.dictioanries = dictioanries;
	}

	public String getDefaultDictId() {
		return defaultDictId;
	}

	public void setDefaultDictId(String defaultDictId) {
		this.defaultDictId = defaultDictId;
	}	
	
	
	
	
}
