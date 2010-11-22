package com.aillusions.dictionary.core;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.aillusions.dictionary.model.Dictionary;
import com.aillusions.dictionary.model.Workspace;

public class WorkspaceManager {

	public static class DictionaryHasToBeCreated extends Exception {
		private static final long serialVersionUID = 1L;
	}

	private boolean loaded;
	private Workspace workspace;
	private Manager manager;

	private XmlFileManager xmlFileManager;

	public boolean addNewDictionary() {

		String paramString = null;

		if ((paramString == null) || (paramString.trim().equals(""))) {
			paramString = JOptionPane.showInputDialog(new JFrame("FrameDemo"), "Input dictionary name:", "Create new dictionary", 3);
		}

		if ((paramString != null) && (paramString.length() > 0) && (getDictionaryByName(paramString) == null)) {
			Dictionary res = new Dictionary();
			res.setDisplayName(paramString);
			workspace.getDictioanries().add(res);
			manager.getCurrentStateManager().setCurrentDict(res);
			return true;
		} else {
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

	public void load() throws DictionaryHasToBeCreated {

		workspace = xmlFileManager.load();
		loaded = true;

		if (workspace.getDictioanries().size() > 0) {
			manager.getCurrentStateManager().setCurrentDict(workspace.getDictioanries().get(0));
		} else {
			throw new DictionaryHasToBeCreated();
		}
	}

	public void selectDictionary(String name) {
		manager.getCurrentStateManager().setCurrentDict(getDictionaryByName(name));
	}

	public void saveInFile() {
		this.xmlFileManager.save(workspace);
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public boolean isLoaded() {
		return loaded;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public XmlFileManager getXmlFileManager() {
		return xmlFileManager;
	}

	public void setXmlFileManager(XmlFileManager xmlFileManager) {
		this.xmlFileManager = xmlFileManager;
	}

}
