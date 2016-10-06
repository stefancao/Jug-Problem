

import java.util.ArrayList;
import java.util.List;

/**
 * First Name: Stefan
 * Last Name: Cao
 *
 * Filename : Vertex.java
 */ 

public class Vertex{
		
	//List of neighbors
	private List<Vertex> neighborList; 
	private int distance;
	
	private int A;
	private int B;
	
	private List<Edge> edgeList;
	
	private Vertex Prev;
	
	private int PQkey;

	public Vertex(){
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vertex(int JarA, int JarB){
		this.A = JarA;
		this.B = JarB;
		neighborList = new ArrayList();
		edgeList = new ArrayList();	
	}
	
	public List<Vertex> getNeighborList(){
		return neighborList;
	}
	
	public int getDistance(){
		return distance;
	}
	
	public int getA(){
		return A;
	}
	
	public int getB(){
		return B;
	}
	
	public List<Edge> getEdgeList(){
		return edgeList;
	}
	
	public Vertex getPrev(){
		return Prev;
	}
	
	public int getPQKey(){
		return PQkey;
	}
	
	public void setDistance(int d){
		this.distance = d;
	}
	
	public void setPrev(Vertex p){
		this.Prev = p;
	}
	
	public void setPQKey(int k){
		this.PQkey = k;
	}
	
}	