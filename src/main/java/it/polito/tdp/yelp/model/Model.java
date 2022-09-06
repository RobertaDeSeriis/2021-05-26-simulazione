package it.polito.tdp.yelp.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.yelp.db.YelpDao;

public class Model {
	YelpDao dao;
	List<String> city; 
	List<Adiacenza> vertici; 
	List<Business> locali;
	Map<String, Business> idMap;
	Graph<Adiacenza, DefaultWeightedEdge> grafo;
	double peso;
	List<Adiacenza>migliore;
	
	public Model() {
		this.dao= new YelpDao(); 
	}
	
	public List<String> getCity(){
		return dao.getCity();
	}
	
	
	public String creaGrafo(String c, int d) {
		this.grafo= new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		idMap= new HashMap<>(); 
		vertici= dao.getArchi(c, d, idMap);
		peso=0;
		
		Graphs.addAllVertices(this.grafo, vertici);
		
		for(Adiacenza b1: vertici) {
			for (Adiacenza b2: vertici) {
					peso=b1.getRec()-b2.getRec();
					if(peso>0) {
					Graphs.addEdge(this.grafo,b2, b1, peso);
				}
			}
		} 
		return "Grafo creato!\n# Vertici:"+grafo.vertexSet().size()+ "\n# Archi: "+grafo.edgeSet().size();	
	}
	
public Adiacenza getLocaleMigliore() {
	double max=0;
	//calcola locale migliore, il cui peso degli archi entranti 
	//meno il peso degli archi uscenti è pari al peso sia max
	Adiacenza localeMigliore=null;
	List<Adiacenza> entranti= new LinkedList<>();
	List<Adiacenza> uscenti= new LinkedList<>(); 
	
	
	for(Adiacenza a: grafo.vertexSet()) {
		double val=0.0;
		entranti= Graphs.predecessorListOf(this.grafo, a);
		uscenti= Graphs.successorListOf(this.grafo, a);
		
		for(Adiacenza e: entranti) {
			val+=this.grafo.getEdgeWeight(grafo.getEdge(e, a));
		}
		for(Adiacenza e: uscenti) {
			val-= this.grafo.getEdgeWeight(grafo.getEdge(a, e));
		}
    
    if(val>max) {
    	max=val;
    	localeMigliore=a;
    	}
	}
    return localeMigliore;
	
	}

public List<Adiacenza> getVertici() {
	return vertici;
}


public List<Adiacenza> calcolaPercorso(Adiacenza sorg, Adiacenza dest, double x)
{
	migliore = new LinkedList<>();
	List<Adiacenza> parziale = new LinkedList<>();
	parziale.add(sorg);
	cercaRicorsiva(parziale, dest, x);
	return migliore;
}

private void cercaRicorsiva(List<Adiacenza> parziale, Adiacenza dest, double x) {
	
			//condizione di terminazione
			if(parziale.get(parziale.size()-1).equals(dest)) //finisce quando in parziale c'è la destinazione
			{
				
				if(parziale.size() < migliore.size()) //percorso con minor numero di vertici
				{
					migliore = new LinkedList<>(parziale);
				}
				return;
			}
			Adiacenza ultimo=parziale.get(parziale.size()-1);
			for(Adiacenza v:Graphs.successorListOf(this.grafo, parziale.get(parziale.size()-1))) //scorro sui vicini dell'ultimo nodo sulla lista
			{
				if(!parziale.contains(v))
				{
					if(grafo.getEdgeWeight(grafo.getEdge(ultimo,v))>=x) 
						//il peso dell'arco tra ultimo e v, deve essere maggiore della soglia x
					{
						parziale.add(v);
						cercaRicorsiva(parziale, dest, x);
						parziale.remove(parziale.size()-1);
					}
					
				}
				
			}
	
}



	
}
