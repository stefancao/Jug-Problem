/**
 * First Name: Stefan
 * Last Name: Cao
 *
 * Filename : Main.java
 */ 

public class Main {

	public static void main(String[] args) {
		
		// with BFS
		System.out.println("Testing BFS first ");
		System.out.println("Jug newJug = new Jug(3, 5, 4, 1, 1, 1, 1, 1, 1);");
		Jug newJug = new Jug(3, 5, 4, 1, 1, 1, 1, 1, 1);
		
		System.out.println("Here is the graph...");
		newJug.display();
		
		System.out.println("\n");
		System.out.println("Now solving it...");
		System.out.println("newJug.solve();");
		newJug.solve();
		System.out.println("\n\n\n");
		
		// with dijkstra's
		System.out.println("Testing Dijkstra's ");
		System.out.println("Jug newJug2 = new Jug(3, 5, 4, 6, 5, 4, 3, 2, 1);");
		Jug newJug2 = new Jug(3, 5, 4, 6, 5, 4, 3, 2, 1);
		
		System.out.println("Here is the graph...");
		newJug2.display();
		
		System.out.println("\n");
		System.out.println("Now solving it...");
		System.out.println("newJug2.solve();");
		newJug2.solve();
		System.out.println("\n\n\n");
	
		
	}

}
