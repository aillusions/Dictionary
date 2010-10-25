package com.aillusions.dictionary.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.aillusions.dictionary.model.Dictionary;
import com.aillusions.dictionary.model.Pair;
import com.aillusions.dictionary.model.Workspace;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class PairXmlDao implements PairDao{

	private Workspace workspace;
	private Dictionary currentDictionary;
	private XStream xstream = null;
	private String fName = "";
	
	public PairXmlDao(String fName) {
		super();
		this.fName = fName;
		xstream = new XStream(new DomDriver());
		
		xstream.processAnnotations(Pair.class);
		xstream.processAnnotations(Dictionary.class);		
		xstream.processAnnotations(Workspace.class);		
	}
	
	@SuppressWarnings( { "unchecked" })
	public void load() {
		try {
			if(new File(fName).exists()){
				FileInputStream is = new FileInputStream(fName);
				
				workspace = (Workspace) xstream.fromXML(is);
				is.close();
			}
			else
				workspace = new Workspace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(workspace.getDictioanries() != null ){
			if(workspace.getDictioanries().size() > 0){
				currentDictionary = workspace.getDictioanries().get(0);
			}else{
				currentDictionary = new Dictionary();
				workspace.getDictioanries().add(currentDictionary);
			}
		}else{
			List<Dictionary> dicts = new ArrayList<Dictionary>();
			currentDictionary = new Dictionary();
			workspace.getDictioanries().add(currentDictionary);
			dicts.add(currentDictionary);
			workspace.setDictioanries(dicts);
		}
	}
	
	public void save() {
		try {
			FileOutputStream fos = new FileOutputStream(fName);
			fos.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
							.getBytes());
			fos.write("\r\n<?xml-stylesheet type=\"text/xsl\" href=\"Words_prn.xsl\"?>\r\n"
							.getBytes());
			fos.write(xstream.toXML(workspace).getBytes("UTF-8"));
			fos.flush();
			fos.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public Pair[] getAllPairs(){
		return currentDictionary.getPairs().toArray(new Pair[currentDictionary.getPairs().size()]);		
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
			currentDictionary.getPairs().add(fw);
		}
		return fw;
	}

	public String[] getAllEnglish() {//TODO ADD sort options 
		String[] res = new String[currentDictionary.getPairs().size()];
		int i = 0;
		for (Pair word : currentDictionary.getPairs()) {
			res[i] = word.getEnglish();
			i++;
		}
		
		return res;
	}

	public Pair getPair(String eng) {
		Pair res = null;
		for (Pair word : currentDictionary.getPairs()) {
			if (word.getEnglish().equals(eng)) {
				res = word;
			}
		}
		return res;
	}

	public Pair remove(Pair pair) {
		Pair res = null;
		int indexDel = currentDictionary.getPairs().indexOf(pair);
		int indexSibl = -1; 
		currentDictionary.getPairs().remove(pair);	
		currentDictionary.getTrash().add(pair);
		if(currentDictionary.getPairs().size() > indexDel){
			indexSibl = indexDel;
		}
		else if(currentDictionary.getPairs().size() > 0 && currentDictionary.getPairs().size() <= indexDel){
			indexSibl = currentDictionary.getPairs().size()-1;
		}
		if(indexSibl != -1)
			res =  currentDictionary.getPairs().get(indexSibl); 
		return res;
	}
	
	  public void shuffle()
	  {
	    Collections.shuffle(this.currentDictionary.getPairs());
	  }
}
