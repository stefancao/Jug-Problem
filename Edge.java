/**
 * First Name: Stefan
 * Last Name: Cao
 *
 * Filename : Edge.java
 */ 

public class Edge{
	
	Vertex from;
	Vertex to;
	String action;
	
	public Edge(Vertex from, Vertex to, String action){
		this.from = from;
		this.to = to;
		this.action = action;
	}
	
	public Vertex getFrom(){
		return from;
	}
	
	public Vertex getTo(){
		return to;
	}
	
	public String getAction(){
		return action;
	}
}