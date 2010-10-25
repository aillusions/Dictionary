package com.aillusions.dictionary.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Collections;

import com.aillusions.dictionary.model.Dictionary;
import com.aillusions.dictionary.model.Pair;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class PairXmlDao implements PairDao{

	//List<Pair> pairs = null;
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
		
		//pairs =  dictionary.getPairs();

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
		return dictionary.getPairs().toArray(new Pair[dictionary.getPairs().size()]);		
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
			dictionary.getPairs().add(fw);
		}
		return fw;
	}

	public String[] getAllEnglish() {//TODO ADD sort options 
		String[] res = new String[dictionary.getPairs().size()];
		int i = 0;
		for (Pair word : dictionary.getPairs()) {
			res[i] = word.getEnglish();
			i++;
		}
		
		return res;
	}

	public Pair getPair(String eng) {
		Pair res = null;
		for (Pair word : dictionary.getPairs()) {
			if (word.getEnglish().equals(eng)) {
				res = word;
			}
		}
		return res;
	}

	public Pair remove(Pair pair) {
		Pair res = null;
		int indexDel = dictionary.getPairs().indexOf(pair);
		int indexSibl = -1; 
		dictionary.getPairs().remove(pair);	
		dictionary.getTrash().add(pair);
		if(dictionary.getPairs().size() > indexDel){
			indexSibl = indexDel;
		}
		else if(dictionary.getPairs().size() > 0 && dictionary.getPairs().size() <= indexDel){
			indexSibl = dictionary.getPairs().size()-1;
		}
		if(indexSibl != -1)
			res =  dictionary.getPairs().get(indexSibl); 
		return res;
	}
	
	  public void shuffle()
	  {
	    Collections.shuffle(this.dictionary.getPairs());
	  }
}
