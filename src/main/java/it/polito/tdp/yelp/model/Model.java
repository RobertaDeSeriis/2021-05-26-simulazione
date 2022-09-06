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
	Map<String, Business> idMap;
	Graph<Adiacenza, DefaultWeightedEdge> grafo;
	double peso;
	
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
		
		for(Adiacenza a: vertici) {
			for (Adiacenza a1: vertici) {
				if(a.getRec()<a1.getRec()) {
					peso=a1.getRec()-a.getRec();
					Graphs.addEdge(this.grafo,a, a1, peso);
				}
			}	
		} //errore peso, non salvato correttamente
		
		/*for(Adiacenza a: vertici) {
			for (Adiacenza a1: vertici) {
					peso=a.getRec()-a1.getRec();
					if(peso>0) {
					Graphs.addEdge(this.grafo,a1, a, peso);
				}
					if(peso<0) {
					Graphs.addEdge(this.grafo,a, a1, (-1)*peso);
				}
				
				
			}
		}*/ //funziona ma il peso no 
		
		
		return "Grafo creato!\n# Vertici:"+grafo.vertexSet().size()+ "\n# Archi: "+grafo.edgeSet().size();	
	}
	
public Adiacenza getLocaleMigliore() {
	double max=0;
	Adiacenza result=null;
	
	for(Adiacenza a: grafo.vertexSet()) {
		double val=0.0;
		for(DefaultWeightedEdge e: this.grafo.incomingEdgesOf(a)) {
			val+=this.grafo.getEdgeWeight(e);
		}
    for(DefaultWeightedEdge e: this.grafo.outgoingEdgesOf(a)) {
			val-= this.grafo.getEdgeWeight(e);
		}
    
    if(val>max)
    	max=val;
    result=a;
	}
    return result;
	
}
	
}
