package it.polito.tdp.yelp.model;

public class Adiacenza {

	String bId;
	double rec;
	double peso;
	
	
	
	public Adiacenza(String bId, double rec) {
		super();
		this.bId = bId;
		this.rec = rec;
	}
	public String getbId() {
		return bId;
	}
	public void setbId(String bId) {
		this.bId = bId;
	}
	public double getRec() {
		return rec;
	}
	public void setRec(double rec) {
		this.rec = rec;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	
	
}
