package com.aillusions.dictionary.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.aillusions.dictionary.model.Dictionary;
import com.aillusions.dictionary.model.Pair;
import com.aillusions.dictionary.model.Workspace;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlFileManager {
	private final XStream xstream;
	private final String fName;

	public XmlFileManager(final String fName) {

		this.fName = fName.trim();

		this.xstream = new XStream(new DomDriver());
		this.xstream.processAnnotations(Pair.class);
		this.xstream.processAnnotations(Dictionary.class);
		this.xstream.processAnnotations(Workspace.class);
	}

	public Workspace load() {

		Workspace workspace = null;

		try {
			if (new File(this.fName).exists()) {
				FileInputStream is = new FileInputStream(this.fName);

				workspace = (Workspace) this.xstream.fromXML(is);
				is.close();
			} else
				workspace = new Workspace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return workspace;
	}

	public void save(final Workspace workspace) {
		try {
			FileOutputStream fos = new FileOutputStream(this.fName);
			fos.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>".getBytes());
			fos.write("\r\n<?xml-stylesheet type=\"text/xsl\" href=\"words_prn.xsl\"?>\r\n".getBytes());
			fos.write(this.xstream.toXML(workspace).getBytes("UTF-8"));
			fos.flush();
			fos.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
