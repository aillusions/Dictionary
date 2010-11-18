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
	
	public static String xslLocationPkg = "com/aillusions/dictionary/xsl";
	
	private static String ALL_WORDS_XSL_ = "TransSheet.xsl";
	private static String ALL_SAMPLES_XSL_ = "TransSheetSamples.xsl";
	private static String WORD_TRANSL_SAMPLE_ = "WordTranscrTranslSample.xsl";
	
	public enum DocViewMode{
		
		ALL_WORDS_XSL(ALL_WORDS_XSL_), 
		ALL_SAMPLES_XSL(ALL_SAMPLES_XSL_), 
		WORD_TRANSL_SAMPLE(WORD_TRANSL_SAMPLE_);
		
		private String path;
		
		DocViewMode(String path){
			this.path = path;
		}	
		
		public String getPath(){
			return path;
		}
	}
	
	
	private static String w1 = "c:\\Program Files\\MSOffice\\OFFICE11\\WINWORD.EXE";
	private static String w2 = "c:\\Program Files\\Microsoft Office\\OFFICE11\\WINWORD.EXE";

	public void runWord(DocViewMode mode) {

		File localFile = new File("Words.xml");
		TransformerFactory localTransformerFactory = TransformerFactory
				.newInstance();
		Transformer localTransformer = null;
		try {
			InputStream localInputStream = null;
			
			localInputStream = super.getClass().getClassLoader().getResourceAsStream(xslLocationPkg + "/" + mode.getPath());
			
/*			if (!(paramBoolean1))
				localInputStream = super.getClass().getClassLoader().getResourceAsStream(ALL_WORDS_XSL);
			else if (!(paramBoolean2))
				localInputStream = super.getClass().getClassLoader().getResourceAsStream(INUSE_WORDS_XSL);
			else
				localInputStream = super.getClass().getClassLoader().getResourceAsStream(ALL_SAMPLES_XSL);*/
			
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
