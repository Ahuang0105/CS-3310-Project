import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Knapsack 
{
	public static int[] profit = new int[4]; 
	public static int[] weight = new int[4];
	public static int[] itemInclude = new int[4];
	public static int W = 16; //Max weight
	public static int n = 4;  //Total items
	public static int s = 0;  //Count how many item is in knapsack
	public static String filePath;
	
	public static void main(String[] args) throws Exception 
	{
		System.out.println("Please enter input txt file path");
		System.out.println("Example: C:/Users/d/Desktop/Test2.txt \n" );
		Scanner input = new Scanner(System.in);
		filePath = input.nextLine();
		
		//Read input txt file
		fileReader( filePath );

	    System.out.println("\n\nMax profit is " +knapsack(n, W));
	    System.out.print("Item included is ");

	    for(int i=0; i < s; i++)
	    {
	    	System.out.print( itemInclude[i] +" ");
	    }
	}
	
	//The Best First search with Branch and Bound implementation with java priority queue 
	//from lecture slide's pseudocode.
	public static int knapsack(int n, int maxWeight) 
	{
	    NodeComparator nc = new NodeComparator(); //Need to use a custom comparator for priority queue	    
	    PriorityQueue<Node> pq = new PriorityQueue(n, nc);
	    Node u = new Node();
	    Node v = new Node();
	    int index = 0;
	    	    
	    //Initialize root value
	    v.level = -1;
	    v.profit = 0;
	    v.weight = 0;
	    v.bound = bound(v);	//Calculate root's bound
	    
	    int maxprofit = 0; // To store total profit
	    int temp = 0;
	    	     	    
	    pq.add(v); //enqueue
	    
	    System.out.println("****************** ");
        System.out.println("Visted node " + (v.level +1) +", "+ index);
        System.out.println("profit " +v.profit);
        System.out.println("weight " +v.weight);
        System.out.println("Bound " +v.bound);
        System.out.println("Maxium profit is " + maxprofit);
   
	    while ( !pq.isEmpty() ) 
	    {
  		    		    	
	        v = (Node) pq.poll(); // remove node
	        u = new Node();
	        
	        if(temp == v.level) //For print out index
	        {
	        	index = 0;
	        }
	        	        
	        if (v.bound > maxprofit) 
	        {
	        	//This child includes the parent weight and profit
	            u.level = v.level + 1;
	            u.weight = v.weight + weight[u.level];
	            u.profit = v.profit + profit[u.level];
	            
	            //If weight is less then capacity and profit is greater then max profit add it the the knapsack
	            if (u.weight <= W && u.profit > maxprofit) 
	            {
	                maxprofit = u.profit; 
	                itemInclude[s] = (u.level +1 );
	                s++;
	            }
	            
	            u.bound = bound(u);
	            
	            //if left child's bound is greater than max profit enqueue the node
	            if (u.bound > maxprofit) 
	            {
	                pq.add(u);	  
	                
	            }
	            index += 1;
	            System.out.println("****************** ");
	            System.out.println("Visted node " + (v.level +2) +", "+ index );
	            System.out.println("profit " +u.profit);
	            System.out.println("weight " +u.weight);
	            System.out.println("Bound " +u.bound); 
	            System.out.println("Maxium profit is " + maxprofit);
	            
	            
	            //This child dose not include the parent weight and profit
	            u = new Node();
	            u.level = v.level + 1;
	            u.weight = v.weight;
	            u.profit = v.profit;
	            u.bound = bound(u);
	            	
	            //if right child's bound is greater than max profit enqueue the node
	            if (u.bound > maxprofit) 
	            {
	                pq.add(u);	 
	            }
	            index += 1;
	            System.out.println("****************** ");
	            System.out.println("Visted node " + (v.level +2) +", "+ index);
	            System.out.println("profit " +u.profit);
	            System.out.println("weight " +u.weight);
	            System.out.println("Bound " +u.bound);
	            System.out.println("Maxium profit is " + maxprofit);
	        
	            temp = v.level+1;
	        }
	        
	        
	    }
	    System.out.println("****************** ");
	    return maxprofit;
	}

	//This method will calculate the bound value
	//from lecture slide's pseudocode.
	public static float bound (Node u) 
	{
	    int j , k;
	    int totalWeight;
	    float result;
	    if (u.weight >= W) 
	    {
	        return 0;
	    } 
	    else 
	    {
	        result = u.profit;
	        j = u.level + 1;
	        totalWeight = u.weight;

	        while (j < n && totalWeight + weight[j] < W) 
	        {
	            totalWeight += weight[j];
	            result += profit[j];
	            j++;
	        }
	        
	        k = j;

	        if (k < n) 
	        {
	            result += ((W - totalWeight) * (profit[k] / weight[k]));
	        }        
	        return result;
	    }
	}
	
	//This method will read txt file and copy it to array
	public static void fileReader( String path ) throws Exception 
	{	      
		//Scan txt file 
		Scanner sc = new Scanner(new BufferedReader(new FileReader(path)));
     	    
		//Read while the scan still has next integers 
		while(sc.hasNextLine()) 
	    {         	    	  
			for (int i=0; i< 2; i++) 	    	  
			{	            
				String[] line = sc.nextLine().trim().split(" ");
				
				for (int j=0; j < 4; j++) 
	            {
					//First line for profit
					if(i == 0)
					{
						profit[j] = Integer.parseInt(line[j]);
					}
					else
					{
						weight[j] = Integer.parseInt(line[j]);
					}
	            }	         
	    	  }	      
	    }
		sc.close();
	}
	
	//Custom comparator to compare node's bound value
	public static class NodeComparator implements Comparator 
	{
	    @Override
	    public int compare(Object s1, Object s2) {
	        Node n1 = (Node) s1;
	        Node n2 = (Node) s2;
	        if (n1.bound < n2.bound) {
	            return 1;
	        }
	        if (n1.bound > n2.bound) {
	            return -1;
	        } else 
	        {
	            return 0;
	        }
	    }
	}
	
	//My node object class
	//from lecture slide's pseudocode.
	private static class Node 
	{
	    int level;
	    int weight;
	    int profit;
	    double bound;
	    int vertex;
	}
	
	
}