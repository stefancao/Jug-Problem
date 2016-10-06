/**
 * First Name: Stefan
 * Last Name: Cao
 *
 * Filename : PriorityQueue.java
 */ 

public class PriorityQueue {

	// inner class
	private class ItemPriorityPair<T>{
		
		// internal fields
		private T item;
		private int priority;
		
		// Default constructor
		private ItemPriorityPair(T item, int key){
			this.item = item;
			this.priority = key;
		}
		
		// get methods
		public int getPriority(){
			return priority;
		}
		public T getItem(){
			return item;
		}
		
	}	// end of inner class
	
	
	// Private fields for Priority Queue class
	private int currentSize;	// number of items currently in the queue	
	
	@SuppressWarnings("rawtypes")
	private ItemPriorityPair [] h;	//internal array to hold queue items
	
	// Setting Max Capacity of Heap
	public static final int CAPACITY = 100000000;
	
	// Default Constructor - constructs an empty priority queue
	public PriorityQueue(){
		h = new ItemPriorityPair[CAPACITY];		
		currentSize = 0;	
	}
	
	// build a MinHeap 
	public void buildMinHeap(){
		
		// starting with the last parent in heap
		for(int i = (currentSize/2) - 1; i >= 0; i--){
			
			// call trickleDown which maintains the heap property
			trickleDown(i);
		}	
	}
	
	// Returning the vertex with the minimum key in heap
	public Vertex peek(){
		if(isEmpty()){
			return null;
		}
		
		// if the heap is not empty, return the first element which is the min
		else{
			return (Vertex) h[0].getItem();
		}
	}
	
	// Used for testing with integers
	public int peekInteger(){
		if(isEmpty()){
			throw new IndexOutOfBoundsException("The Heap is Empty!");
		}
		
		// if the heap is not empty, return the first element which is the min
		else{
			return (Integer) h[0].getItem();
		}
	}
	
	// Removing the vertex with the minimum key in the heap
	public Vertex poll(){
		if(isEmpty()){
			throw new IndexOutOfBoundsException("The Heap is Empty!");
		}
		else{
			// overwrite the last element in array to first element in heap
			Vertex temp = (Vertex) h[0].getItem();
			h[0] = h[currentSize-1];
			currentSize--;
			
			// call buildMinHeap to maintain the heap property
			buildMinHeap();
			return temp;
		}
	}
	
	// Removing the integer (used for testing) with the minimum key in the heap
	public int pollInteger(){
		if(isEmpty()){
			throw new IndexOutOfBoundsException("The Heap is Empty!");
		}
		else{
			// overwrite the last element in array to first element in heap
			int temp = (Integer) h[0].getItem();
			h[0] = h[currentSize-1];
			currentSize--;
			
			// call buildMinHeap to maintain the heap property
			buildMinHeap();
			return temp;
		}
	}
	
	// Insert a vertex with priority key into the heap
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean add(Vertex v){
		if(size() == CAPACITY){
			return false;
		}else{
			ItemPriorityPair temp = new ItemPriorityPair(v, v.getDistance());
			boolean isEqual = false;
			for(int i = 0; i < size(); i++){
				if(h[i].getItem().equals(temp.getItem())){
					isEqual = true;
				}	
			}
			if(isEqual){
				for(int i = 1; i < size(); i++){
					h[i] = h[i+1];
				}
				currentSize--;
			}
			currentSize++;
			h[currentSize - 1] = temp;
			trickleUp(currentSize - 1);
			return true;
		}
	
	}
	
	// adding used for testing with integers
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean addInteger(int item, int key){
		if(size() == CAPACITY){
			return false;
		}else{
			ItemPriorityPair temp = new ItemPriorityPair(item, key);
			boolean isEqual = false;
			int counter = 0;
			for(int i = 0; i < currentSize; i++){
				if(h[i].getItem().equals(temp.getItem())){
					isEqual = true;
					counter = i;
				}	
			}
			if(isEqual){
				for(int i = counter; i < currentSize; i++){
					h[i] = h[i+1];
				}
				currentSize--;
			}
			currentSize++;
			h[currentSize - 1] = temp;
			trickleUp(currentSize - 1);
			return true;
		}	
	}
	
	
	// Trickling down - Maintains the heap property between 
	//		a parent node located at index in array, and its children. 
	@SuppressWarnings("rawtypes")
	public void trickleDown(int index){
		
		// defining smaller child which is either left or right child
		int smallerChild;
		
		// set the key of parent 
		int parentKey = h[index].getPriority();
	
		while(index < currentSize/2){
		
			// getting the left child and right child
			int leftChild = 2*index + 1;
			int rightChild = 2*index + 2;
		
			// if rightChild <  currentSize test if there is a right child 
			// check if leftChild's key is greater, if so but smallerChild as right Child
			if((rightChild < currentSize) && (h[leftChild].getPriority() > h[rightChild].getPriority())){
				smallerChild = rightChild;
			}
			
			// else leftChild is smaller child
			else{
				smallerChild = leftChild;
			}
			
			// if the parent key is smaller than the key of smallerChild then break
			// 		don't need to trickle down anymore
			if(parentKey <= h[smallerChild].getPriority()){
				break;
			}
			
			// replace smallerChild's key with parent's key
			ItemPriorityPair temp = h[index];
			h[index] = h[smallerChild];

			// current index is now smaller child's index
			index = smallerChild;
			h[index] = temp;
		}	
	}

	// Trickle Up maintains the heap property between a node located in index, and its parent
	@SuppressWarnings("rawtypes")
	public void trickleUp(int index){
		if(index != 0){
			int parentIndex = (index - 1)/2;
			if (h[parentIndex].getPriority() > h[index].getPriority()){
				ItemPriorityPair temp = h[parentIndex];
				h[parentIndex] = h[index];
				h[index] = temp;
				trickleUp(parentIndex);
			}
		}
	}
	
	// check if queue is empty 
	public boolean isEmpty(){
		if(currentSize == 0){
			return true;
		}
		return false;
	}
	
	// checking the size if queue
	public int size(){
		return currentSize;
	}
	
	
	
	//printing method 
	public void display(){
		
		int x = 0;
		int y = 0;
		if(size() == 0){
			System.out.println("Priority Queue is Empty!");
		}
		else{
			for(int i = 0; i < currentSize; i++){
				
				System.out.print("|" + h[i].getItem() + ":" + h[i].getPriority() + "| ");
				if(i + 1 - y == Math.pow(2, x)){
					System.out.print("\n");
					x++;
					y = i + 1;
				}
			
			}
			System.out.print("");
		}

	}

}	// end of PriorityQueue


