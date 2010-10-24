package com.aillusions.dictionary.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.aillusions.dictionary.model.Dictionary;
import com.aillusions.dictionary.model.Pair;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class PairXmlDao implements PairDao{

	List<Pair> pairs = null;
	Dictionary dictionary;
	XStream xstream = null;
	String fName = "";
	
	public PairXmlDao(String fName) {
		super();
		this.fName = fName;
		xstream = new XStream(new DomDriver());
		
		xstream.processAnnotations(Pair.class);
		xstream.processAnnotations(Dictionary.class);		
	}
	
	@SuppressWarnings( { "unchecked" })
	public void load() {
		try {
			if(new File(fName).exists()){
				FileInputStream is = new FileInputStream(fName);
				
				dictionary = (Dictionary) xstream.fromXML(is);
				is.close();
			}
			else
				dictionary = new Dictionary();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pairs =  dictionary.getPairs();
		
		if(pairs == null){
			pairs = new ArrayList<Pair>();
			dictionary.setPairs(pairs);
		}
		
		
	}
	
	public void save() {
		try {
			FileOutputStream fos = new FileOutputStream(fName);
			fos.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
							.getBytes());
			fos.write("\r\n<?xml-stylesheet type=\"text/xsl\" href=\"Words_prn.xsl\"?>\r\n"
							.getBytes());
			fos.write(xstream.toXML(dictionary).getBytes("UTF-8"));
			fos.flush();
			fos.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public Pair[] getAllPairs(){
		return pairs.toArray(new Pair[pairs.size()]);		
	}

	public void addNew(Pair pair) {		
		
	}

	public Pair addNew(String eng) {
		Pair fw = null;
		if (eng != null) {
			fw = new Pair();
			fw.setEnglish(eng);
			fw.setTranscription("");
			fw.setTranscription("");
			pairs.add(fw);
		}
		return fw;
	}

	public String[] getAllEnglish() {//TODO ADD sort options 
		String[] res = new String[pairs.size()];
		int i = 0;
		for (Pair word : pairs) {
			res[i] = word.getEnglish();
			i++;
		}
		
		return res;
	}

	public Pair getPair(String eng) {
		Pair res = null;
		for (Pair word : pairs) {
			if (word.getEnglish().equals(eng)) {
				res = word;
			}
		}
		return res;
	}

	public Pair remove(Pair pair) {
		Pair res = null;
		int indexDel = pairs.indexOf(pair);
		int indexSibl = -1; 
		pairs.remove(pair);	
		if(pairs.size() > indexDel){
			indexSibl = indexDel;
		}
		else if(pairs.size() > 0 && pairs.size() <= indexDel){
			indexSibl = pairs.size()-1;
		}
		if(indexSibl != -1)
			res =  pairs.get(indexSibl); 
		return res;
	}
	
	  public void shuffle()
	  {
	    Collections.shuffle(this.pairs);
	  }
}
