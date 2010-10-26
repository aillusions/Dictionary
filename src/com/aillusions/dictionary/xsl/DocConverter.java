package com.aillusions.dictionary.xsl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class DocConverter {
	
	private static String ALL_WORDS_XSL = "com/aillusions/dictionary/xsl/TransSheet.xsl";
	private static String INUSE_WORDS_XSL = "com/aillusions/dictionary/xsl/TransSheetInUse.xsl";
	private static String ALL_SAMPLES_XSL = "com/aillusions/dictionary/xsl/TransSheetSamples.xsl";
	private static String w1 = "c:\\Program Files\\MSOffice\\OFFICE11\\WINWORD.EXE";
	private static String w2 = "c:\\Program Files\\Microsoft Office\\OFFICE11\\WINWORD.EXE";

	public void runWord(boolean paramBoolean1, boolean paramBoolean2) {

		File localFile = new File("Words.xml");
		TransformerFactory localTransformerFactory = TransformerFactory
				.newInstance();
		Transformer localTransformer = null;
		try {
			InputStream localInputStream = null;
			if (!(paramBoolean1))
				localInputStream = super.getClass().getClassLoader().getResourceAsStream(ALL_WORDS_XSL);
			else if (!(paramBoolean2))
				localInputStream = super.getClass().getClassLoader().getResourceAsStream(INUSE_WORDS_XSL);
			else
				localInputStream = super.getClass().getClassLoader().getResourceAsStream(ALL_SAMPLES_XSL);
			if (localInputStream != null)
				localTransformer = localTransformerFactory.newTransformer(new StreamSource(localInputStream));
			else
				System.out.println(localInputStream);
		} catch (TransformerConfigurationException localTransformerConfigurationException) {
			localTransformerConfigurationException.printStackTrace();
		}
		FileOutputStream localFileOutputStream = null;
		try {
			localFileOutputStream = new FileOutputStream("print.doc");
		} catch (FileNotFoundException localFileNotFoundException) {
			localFileNotFoundException.printStackTrace();
		}
		try {
			localTransformer.transform(new StreamSource(localFile),	new StreamResult(localFileOutputStream));
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		try {
			localFileOutputStream.close();
		} catch (IOException localIOException1) {
			localIOException1.printStackTrace();
		}
		try {
			if (new File(w1).exists())
				Runtime.getRuntime().exec(w1 + " print.doc");
			else if (new File(w2).exists())
				Runtime.getRuntime().exec(w2 + " print.doc");
			else
				Runtime.getRuntime().exec("cmd /C print.doc");
		} catch (IOException localIOException2) {
			localIOException2.printStackTrace();
		}
	}
}
