package com.aillusions.dictionary.model;

import java.util.LinkedList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("correspondence")
public class Pair {

	@XStreamAlias("english")
	private String english;
	@XStreamAlias("russian")
	private String russian;
	@XStreamAlias("transcription")
	private String transcription;

	@XStreamImplicit(itemFieldName = "sample")
	private List<String> samples;

	@XStreamAsAttribute()
	private boolean inuse;
	
	@XStreamAlias("asksQuantity")
	private int asksQuantity; 
	
	@XStreamAlias("rightAnswerQuantity")
	private int rightAnswerQuantity;
	
	@XStreamAlias("attemtpsQuantity")
	private int attemttsQuantity;	


	public int getAttemttsQuantity() {
		return attemttsQuantity;
	}

	public void incrAttemttsQuantity() {
		this.attemttsQuantity++;
	}

	public int getAsksQuantity() {
		return asksQuantity;
	}

	public void incrAsksQuantity() {
		this.asksQuantity++;
	}

	public int getRightAnswerQuantity() {
		return rightAnswerQuantity;
	}

	public void incrRightAnswerQuantity() {
		this.rightAnswerQuantity++;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getRussian() {
		return russian;
	}

	public void setRussian(String russian) {
		this.russian = russian;
	}

	public String getTranscription() {
		return transcription;
	}

	public void setTranscription(String transcription) {
		this.transcription = transcription;
	}

	public String[] getSamples() {
		if(samples == null){
			samples = new LinkedList<String>();
		}
		return samples.toArray(new String[samples.size()]);
	}
	
	public void removeSample(String s){
		if(samples != null){
			samples.remove(s);
		}
	}
	
	public void addSample(String s){
		if(samples == null){
			samples = new LinkedList<String>();
		}
		samples.add(s);
	}	

	public void upSample(String sample){
		String first = samples.get(0);
		int currSampleIndex = samples.indexOf(sample);
		samples.set(0, sample);
		samples.set(currSampleIndex, first);
	}
	
	public void downSample(String sample){
		
	}
	
	public boolean isInuse() {
		return inuse;
	}

	public void setInuse(boolean inuse) {
		this.inuse = inuse;
	}

	public Pair() {
		super();
	}

	public void clearStatus() {
		 //inuse = false;
		 asksQuantity = 0;  
		 rightAnswerQuantity = 0; 
		 attemttsQuantity = 0;	
	}



}
