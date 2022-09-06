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
		//Map<EntrEsc, Double> eE= new HashMap<>(); 
		vertici= dao.getArchi(c, d, idMap);
		peso=0;
		
		Graphs.addAllVertices(this.grafo, vertici);
		
		for(Adiacenza a: vertici) {
			for (Adiacenza a1: vertici) {
					peso=a.getRec()-a1.getRec();
					if(peso>0) {
					Graphs.addEdge(this.grafo,a1, a, peso);
				}
					if(peso<0) {
					Graphs.addEdge(this.grafo,a, a1, (-1)*peso);
				}
				
				
			}
		}
		
		
		return "Grafo creato!\n# Vertici:"+grafo.vertexSet().size()+ "\n# Archi: "+grafo.edgeSet().size();	
	}
	
	/*
	public List<Adiacenza> getUscenti() {
		List<Integer> pesoArchi;
		List<Adiacenza> uscenti=new LinkedList<>();  
		List<Adiacenza> entranti=new LinkedList<>(); 
		List<Adiacenza> adiacenti = new LinkedList<>();
		
		int max=0; 
		for (Adiacenza p: vertici) { //ciclo sui vertici 
			uscenti= Graphs.successorListOf(this.grafo, p); //per trovare il vertice con più archi uscenti
			entranti= Graphs.predecessorListOf(this.grafo, p);
			if(uscenti.size()>max) {
				max=uscenti.size(); //salvo il numero di archi uscenti in max
			}
		}
		for (Adiacenza p: vertici) {
			uscenti= Graphs.successorListOf(this.grafo, p);
			entranti= Graphs.predecessorListOf(this.grafo, p);
			if(uscenti.size()==max) {
				for(Adiacenza p1: uscenti) { //ciclo sugli successori (uscenti) per trovare il peso
					adiacenti.add(new Adiacenza(p, p1,grafo.getEdgeWeight(grafo.getEdge(p, p1))));
				} //salvo il peso tra top-player e uscente1, uscente2... in adiacenti
				
				Collections.sort(adiacenti); //ordino gli adiacenti in ordine decrescente di peso
				return adiacenti;
			}
		}
		return adiacenti; 
		
	}
	
	public double getpesoTot() {
		
		List<EntrEsc> uscenti=new LinkedList<>();  
		List<EntrEsc> entranti=new LinkedList<>();
		double pesoTot=0;
		double pesoU=0;
		double pesoE=0;
		//secondo me deve farsi una lista con arco e peso
	}*/
	
}
