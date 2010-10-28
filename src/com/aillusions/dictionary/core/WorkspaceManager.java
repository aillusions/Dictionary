package com.aillusions.dictionary.core;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.aillusions.dictionary.model.Dictionary;
import com.aillusions.dictionary.model.Workspace;

public class WorkspaceManager {
	
	private Workspace workspace;
	private Dictionary currentDictionary;
	private XmlFileManager pdao;
	
	public WorkspaceManager(String fName) {
		this.pdao = new XmlFileManager(fName);
	}

	public void setCurrentDict(Dictionary dict) {
		if (dict == null) {
			throw new RuntimeException("Dictionary can not be null.");
		}
		if (workspace.getDictioanries().contains(dict)) {
			currentDictionary = dict;
		}
	}
	
	public boolean addNewDictionary() {

		String paramString = null;
		
		if ((paramString == null) || (paramString.trim().equals(""))){
			paramString = JOptionPane.showInputDialog(new JFrame("FrameDemo"), "Input dictionary name:", "Create new dictionary", 3);
		}
		
		if ((paramString != null) && (paramString.length() > 0)	&& (getDictionaryByName(paramString) == null)) {
			Dictionary res = new Dictionary();
			res.setDisplayName(paramString);
			workspace.getDictioanries().add(res);
			currentDictionary = res;
			return true;
		}else{
			return false;
		}
	}
	
	public Dictionary getDictionaryByName(String dictName) {
		Dictionary res = null;
		for (Dictionary dict : workspace.getDictioanries()) {
			if (dict.getDisplayName().equals(dictName)) {
				res = dict;
			}
		}
		return res;
	}
	
	public void load() {
		workspace = pdao.load();

		if (workspace.getDictioanries().size() > 0) {
			currentDictionary = workspace.getDictioanries().get(0);
		} else {
			while(!addNewDictionary()){
				//Have to specify at least one dictionary!
			}
		}	
	}
	
	public void selectDictionary(String name) {
		setCurrentDict(getDictionaryByName(name));
	}
	
	public void saveInFile() {
		this.pdao.save(workspace);
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public Dictionary getCurrentDictionary() {
		return currentDictionary;
	}
	
	
}
