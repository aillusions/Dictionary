package com.aillusions.dictionary.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.aillusions.dictionary.model.Dictionary;
import com.aillusions.dictionary.model.Pair;
import com.aillusions.dictionary.model.Workspace;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlFileManager{

	private XStream xstream;
	private String fName;

	public XmlFileManager(String fName) {

		this.fName = fName;
		
		xstream = new XStream(new DomDriver());
		xstream.processAnnotations(Pair.class);
		xstream.processAnnotations(Dictionary.class);
		xstream.processAnnotations(Workspace.class);
	}
	
	public Workspace load() {
		
		Workspace workspace = null;
		
		try {
			if (new File(fName).exists()) {
				FileInputStream is = new FileInputStream(fName);

				workspace = (Workspace) xstream.fromXML(is);
				is.close();
			} else
				workspace = new Workspace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return workspace;
	}

	public void save(Workspace workspace) {
		try {
			FileOutputStream fos = new FileOutputStream(fName);
			fos.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>".getBytes());
			fos.write("\r\n<?xml-stylesheet type=\"text/xsl\" href=\"words_prn.xsl\"?>\r\n".getBytes());
			fos.write(xstream.toXML(workspace).getBytes("UTF-8"));
			fos.flush();
			fos.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
