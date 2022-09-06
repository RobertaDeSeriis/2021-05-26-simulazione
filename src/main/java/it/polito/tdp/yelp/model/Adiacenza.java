package it.polito.tdp.yelp.model;

public class Adiacenza {

	String bId;
	String bN;
	double rec;
	
	public Adiacenza(String bId, String bN, double rec) {
		super();
		this.bId = bId;
		this.bN = bN;
		this.rec = rec;
	}

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
	}

	public String getbN() {
		return bN;
	}

	public void setbN(String bN) {
		this.bN = bN;
	}

	public double getRec() {
		return rec;
	}

	public void setRec(double rec) {
		this.rec = rec;
	}


	@Override
	public String toString() {
		return bN ;
	}
	
	
	
	
	
}
