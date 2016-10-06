import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



/**
 * First Name: Stefan
 * Last Name: Cao
 *
 * Filename : Jug.java
 */ 

public class Jug {
	
	private static final int INFINITY = Integer.MAX_VALUE;
	
	private int Ca;		// capacity of jug 'a'
	private int Cb;		// capacity of jug 'b'
	
	private int N;		// goal
	
	private int fA;		// cost of filling A
	private int fB;		// cost of filling B
	private int eA;		// cost of emptying A
	private int eB;		// cost of emptying B
	private int pAB;	// cost to pour A to B
	private int pBA;	// cost to pour B to A
	
	private Vertex[] vertexArray;
	private Vertex sourceVertex;

	// setting the sourceVertex
	public void setSourceVertex(Vertex v){
		sourceVertex = v;
	}
	
	// getting the sourceVertex
	public Vertex getSourceVertex(){
		return sourceVertex;
	}
	
	// default constructor taking in 9 parameters
	public Jug(int Ca, int Cb, int N, int fA, int fB, int eA, int eB, int pAB, int pBA){
	
		assert (Ca > 0) && (Ca <= Cb) : "Ca has to be greater than 0 and less than or equal to Cb";
		assert (Cb >= N) && (Cb <= 1000) : "Cb has to be greater or equal to N and Cb has to be less than or equal to Cb";
		
		this.Ca = Ca;
		this.Cb = Cb;
		this.N = N;
		this.fA = fA;
		this.fB = fB;
		this.eA = eA;
		this.eB = eB;
		this.pAB = pAB;
		this.pBA = pBA;
		
		int vertexArraySize = (Ca+1)*(Cb+1);
		//System.out.println("vertexArraySize = " + vertexArraySize);
		
		vertexArray = new Vertex[vertexArraySize];
	
		// creating the states/nodes/vertices
		int i = 0;
		for(int a = 0; a <= Ca; a++){
			for(int b = 0; b <= Cb; b++){
				vertexArray[i] = new Vertex(a, b);
				
				// setting the node a=0 and b=0 as the sourceVertex
				if((a == 0) && (b == 0)){
					setSourceVertex(vertexArray[i]);
				}
				i++;
			}
		}
		
		
		// finding neighbors of vertices
		
		// fA - fill A
		for(int index = 0; index < vertexArray.length; index++){
			//int a = vertexArray[index].A;
			int a = Ca;
			
			int b = vertexArray[index].getB();
			
			// going through each list again
			for(int index2 = 0; index2 < vertexArray.length; index2++){
				if(index == index2){
					continue;
				}
				
				// find the neighbors of that vertex
				else if((vertexArray[index2].getA() == a) && (vertexArray[index2].getB() == b)){
					
					// if found, add that vertex to its neighborList
					vertexArray[index].getNeighborList().add(vertexArray[index2]);
					
					// adding an edge
					vertexArray[index].getEdgeList().add(new Edge(vertexArray[index], vertexArray[index2], "fA"));		
				}
			}
		}	// end of fA - fill A
		
		// fB - fill B
		for(int index = 0; index < vertexArray.length; index++){
			//int b = vertexArray[index].B;
			int b = Cb;
			
			
			int a = vertexArray[index].getA();
		
			// going through each list again
			for(int index2 = 0; index2 < vertexArray.length; index2++){
				if(index == index2){
					continue;
				}
				
				// find the neighbors of that vertex
				else if((vertexArray[index2].getB() == b) && (vertexArray[index2].getA() == a)){
					
					// if found, add that vertex to its neighborList
					vertexArray[index].getNeighborList().add(vertexArray[index2]);
					
					// adding an edge
					vertexArray[index].getEdgeList().add(new Edge(vertexArray[index], vertexArray[index2], "fB"));	
				}
			}
		}	// end of fB - fill B
		
		
		// eA - empty A
		for(int index = 0; index < vertexArray.length; index++){
			int a = 0;
			int b = vertexArray[index].getB();
		
			// going through each list again
			for(int index2 = 0; index2 < vertexArray.length; index2++){
				if(index == index2){
					continue;
				}
				
				// find the neighbors of that vertex
				else if((vertexArray[index2].getA() == a) && (vertexArray[index2].getB() == b)){
					
					// if found, add that vertex to its neighborList
					vertexArray[index].getNeighborList().add(vertexArray[index2]);
					
					// adding an edge
					vertexArray[index].getEdgeList().add(new Edge(vertexArray[index], vertexArray[index2], "eA"));	
				}
			}
		}	// end of eA - empty A
		
		
		// eB - empty B
		for(int index = 0; index < vertexArray.length; index++){
			int a = vertexArray[index].getA();
			int b = 0;
		
			// going through each list again
			for(int index2 = 0; index2 < vertexArray.length; index2++){
				if(index == index2){
					continue;
				}
				
				// find the neighbors of that vertex
				else if((vertexArray[index2].getA() == a) && (vertexArray[index2].getB() == b)){
					
					// if found, add that vertex to its neighborList
					vertexArray[index].getNeighborList().add(vertexArray[index2]);
					
					// adding an edge
					vertexArray[index].getEdgeList().add(new Edge(vertexArray[index], vertexArray[index2], "eB"));	
				}
			}
		}	// end of eB - empty B
		
		
		// pAB - pour A into B
		for(int index = 0; index < vertexArray.length; index++){
			
			int a = vertexArray[index].getA();
			int b = vertexArray[index].getB();
			
			// trying to pour a into b
			for(int j = 0; j < vertexArray[index].getA(); j++){
				if(b < Cb){
					b++;
					a--;
				}
			}
			
			// going through each list again
			for(int index2 = 0; index2 < vertexArray.length; index2++){
				if(index == index2){
					continue;
				}
				
				// find the neighbors of that vertex
				else if((vertexArray[index2].getA() == a) && (vertexArray[index2].getB() == b)){
					
					// if found, add that vertex to its neighborList
					vertexArray[index].getNeighborList().add(vertexArray[index2]);
					
					// adding an edge
					vertexArray[index].getEdgeList().add(new Edge(vertexArray[index], vertexArray[index2], "pAB"));	
				}
			}
		}	// end of pAB - pour A into B
		
		
		// pBA - pour B into A
		for(int index = 0; index < vertexArray.length; index++){
			
			int a = vertexArray[index].getA();
			int b = vertexArray[index].getB();
				
			// trying to pour b into a
			for(int j = 0; j < vertexArray[index].getB(); j++){
				if(a < Ca){
					a++;
					b--;
				}
			}
			
			// going through each list again
			for(int index2 = 0; index2 < vertexArray.length; index2++){
				if(index == index2){
					continue;
				}
				
				// find the neighbors of that vertex
				else if((vertexArray[index2].getA() == a) && (vertexArray[index2].getB() == b)){
					
					// if found, add that vertex to its neighborList
					vertexArray[index].getNeighborList().add(vertexArray[index2]);
					
					// adding an edge
					vertexArray[index].getEdgeList().add(new Edge(vertexArray[index], vertexArray[index2], "pBA"));	
				}
			}
		}	// end of pBA - pour B into A
	}	// end of constructor Jug
	
	// method - prints out the graph's adjacency list 
	public void display(){

		for(int i = 0; i < vertexArray.length; i++){
			System.out.print(vertexArray[i].getA() + "" + vertexArray[i].getB() + " -> ");
			
			// check if the node has NO neighbors
			if(vertexArray[i].getNeighborList().isEmpty()){
				System.out.print("empty");
			}
			
			else{
				for(int j = 0; j < vertexArray[i].getNeighborList().size(); j++){
					System.out.print(vertexArray[i].getEdgeList().get(j).action + "|" + vertexArray[i].getNeighborList().get(j).getA() + "" + vertexArray[i].getNeighborList().get(j).getB() + ", ");
				}
			}
			
			System.out.println("");
		}
		System.out.println("");
		
	}
	
	
	
	
	// method solve
	public int solve(){
		
		if((Ca < 0) ||(Ca > Cb) || (Cb < N) || (Cb > 1000)){
			return -1;
		}
		
		
		// BFS
		if(isBFS()){
			BFS();
		}
		
		// Dijkstra
		else{
			Dijkstra();
		}
		
		Vertex tempVertex = null;
		
		// going through the vertexArray to find the vertex that is the goal
		for(int n = 0; n < vertexArray.length; n++){
			if((vertexArray[n].getA() == 0) && vertexArray[n].getB() == N){
				tempVertex = vertexArray[n];
			}
		}
		
		if(tempVertex.getDistance() == INFINITY){
			return -1;
		}
		

		// going through the vertexArray to find the vertex that is the goal
		for(int n = 0; n < vertexArray.length; n++){
			if((vertexArray[n].getA() == 0) && vertexArray[n].getB() == N){
				tempVertex = vertexArray[n];
			}
		}

		
		
		Deque<String> stack = new ArrayDeque<String>();
		
		// stop if the distance = 0 (reaching the source vertex)
		while(tempVertex.getDistance() != 0){
			Vertex oldVertex = tempVertex;
			tempVertex = tempVertex.getPrev();
			
			for(int i = 0; i < tempVertex.getEdgeList().size(); i++){
	
				if(tempVertex.getEdgeList().get(i).to == oldVertex){
					stack.push(tempVertex.getEdgeList().get(i).action);
				}
			}
		}
		
		
		int cost = 0;
		while(!stack.isEmpty()){
			String tempString = stack.pop();
			if(tempString.equals("fA")){
				System.out.println("fill A");
				cost += fA;
			}
			else if(tempString.equals("fB")){
				System.out.println("fill B");
				cost += fB;
			}
			else if(tempString.equals("eA")){
				System.out.println("empty A");
				cost += eA;
			}
			else if(tempString.equals("eB")){
				System.out.println("empty B");
				cost += eB;
			}
			else if(tempString.equals("pAB")){
				System.out.println("pour A B");
				cost += pAB;
			}
			else if(tempString.equals("pBA")){
				System.out.println("pour B A");
				cost += pBA;
			}
		}
		
		System.out.println("success " + cost);

		return 1;
	}	// end of method solve
	
	
	public boolean isBFS(){
		if((fA == 1) && (fB == 1) && (eA == 1) && (eB == 1) && (pAB == 1) && (pBA == 1)){
			return true;
		}
		return false;
	}
	
	public void BFS(){
		Vertex source_vertex = getSourceVertex();
		
		// for every node n in graph, initialize the distance to be infinity
		for(int n = 0; n < vertexArray.length; n++){
			vertexArray[n].setDistance(INFINITY);
		}
		
		// creating an empty queue
		Queue<Vertex> Q = new LinkedList<Vertex>();
		
		source_vertex.setDistance(0);

		Q.add(source_vertex);
		
		while(!Q.isEmpty()){
			Vertex u = Q.remove();

			for(int v = 0; v < u.getNeighborList().size(); v++){
				if(u.getNeighborList().get(v).getDistance() == INFINITY){		
					u.getNeighborList().get(v).setDistance(u.getDistance() + 1);
					
					// record previous for the path
					u.getNeighborList().get(v).setPrev(u);
					
					Q.add(u.getNeighborList().get(v));
				}
			}
		}
	}	// end of BFS
	
	
	public void Dijkstra(){
		
		//initialize all vertex to INFINITY
		for (int i = 0; i < vertexArray.length; i++){
			vertexArray[i].setDistance(INFINITY);
		}

		// initialize source distance to 0
		PriorityQueue pq = new PriorityQueue();
		
		// initialize source distance to 0
		vertexArray[0].setDistance(0);
		pq.add(vertexArray[0]);
		
		//dijkstra algorithm
		while(pq.peek() != null){
			Vertex temp = pq.poll();
			for(int i = 0; i < temp.getNeighborList().size(); i++){
				int dist = temp.getDistance() + getWeight(temp, i);
				if(dist < temp.getNeighborList().get(i).getDistance()){
					temp.getNeighborList().get(i).setDistance(dist);
					temp.getNeighborList().get(i).setPrev(temp);
					
					pq.add(temp.getNeighborList().get(i));
				}
			}
		}
		
		
		
		
		
	}
	
	private int getWeight(Vertex from, int x){
		if(from.getEdgeList().get(x).action.equals("fA")){
			return fA;
		}
		else if(from.getEdgeList().get(x).action.equals("fA")){
			return fB;
		}
		else if(from.getEdgeList().get(x).action.equals("eA")){
			return eA;
		}
		else if(from.getEdgeList().get(x).action.equals("eB")){
			return eB;
		}
		else if(from.getEdgeList().get(x).action.equals("pAB")){
			return pAB;
		}
		else if(from.getEdgeList().get(x).action.equals("pBA")){
			return pBA;
		}
		else{
			return 0;
		}
	}

}	// end of class Jug
