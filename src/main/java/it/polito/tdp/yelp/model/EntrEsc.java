package it.polito.tdp.yelp.model;

public class EntrEsc {
	
	Adiacenza a;
	double peso;
	
	public EntrEsc(Adiacenza a, double peso) {
		super();
		this.a = a;
		this.peso = peso;
	}

	public Adiacenza getA() {
		return a;
	}

	public void setA(Adiacenza a) {
		this.a = a;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	} 
	
	

}
