import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;


public class TSP {

	public static int INF = Integer.MAX_VALUE; //Use integer max value as my infinity value.
	public static int [] length ;
    public static int best_cost; // cost of the shortest path
    public static int max_cost = INF; 
    public static int [][] inputQuestion; //Use to store input txt 2x2 matrix 
    public static int [][] reducedMap; //Use to calculate reduced map
    public static String filePath;
    
    public static void main(String [] args )throws Exception 
    {
    	System.out.println("Please enter input txt file path");
		System.out.println("Example: C:/Users/d/Desktop/Test3.txt \n" );
		Scanner input = new Scanner(System.in);
		filePath = input.nextLine();
    	
    	fileReader(filePath);
        TSPsolve();

        System.out.println("length: "+best_cost);
        System.out.println("Optimal tour: "+Arrays.toString(length));
    	
    }
    
    public static void TSPsolve () {
    	
    	NodeComparator nc = new NodeComparator();
    	PriorityQueue<Node> pq = new PriorityQueue<Node>(6,nc);
    	
    	Node v = new Node(inputQuestion, new int[]{}, 0, -1, 0); //Intialize node 
    	
    	v.bound = bound(v); //Calculate root node's minimum value and use that to subtract from matrix table to get reduced map
    	
    	pq.add(v);
    	
    	while(!pq.isEmpty()) {
    		
    		Node min = pq.poll(); //Remove node with largest bound
            int i = min.vertex;
            
            if (min.level == inputQuestion.length-1) //If all node are visted
            {
            	length = Arrays.copyOf(min.path, inputQuestion.length+1); //Copy all the path that is recored 
            	
            	length[inputQuestion.length] = 0; //Set location back to start
          	
                best_cost = min.bound; //Set the final bound as our cost
                return;
            }

            //Checking each child's bound.
            for (int j = 0; j < inputQuestion.length; j++) {
                if (min.reducedMatrix[i][j] != INF) {

                	//create new node can calculate it's bound
                    Node child = new Node(min.reducedMatrix, min.path, min.level + 1, i, j);

                    child.bound = min.bound + min.reducedMatrix[i][j] + bound(child);

                    if (child.bound < max_cost) {// add child to list if it's bound is lower than the max cost
                        pq.add(child);
                    }
                }
            }
    		
    	}
    		
    }
    
    //Calculate each rows and cols min value and add them together
    public static int bound(Node u) {
		
    	//First we need to find the min value from each row and col
		int[] row = rowReduction(u.reducedMatrix );
		int[] col = colReduction(u.reducedMatrix );
		
		int cost = 0;
		
		//Adding all min value together and not include one that is already visited
		for (int i = 0; i < inputQuestion.length; i++) {
			
			if (row[i] != INF) {
				cost += row[i];
			}
			
			if (col[i] != INF) {
				cost += col[i];
			}
		}
		return cost;
	}
    
    //Find each rows min value and later subtract it to make new reduced matrix
    private static int[] rowReduction(int reducedMatrix [][]) {
		
    	int[] row = new int[inputQuestion.length];
		Arrays.fill(row, INF);
		
		//Looking for the min value from each rows 
		for (int i = 0; i < inputQuestion.length; i++) 
		{
			for (int j = 0; j < inputQuestion.length; j++) 
			{
				if (reducedMatrix[i][j] < row[i]) {
					row[i] = reducedMatrix[i][j];
				}
			}
		}

		//Subtract new min value to make new reduced matrix
		for (int i = 0; i < inputQuestion.length; i++) 
		{
			for (int j = 0; j < inputQuestion.length; j++) 
			{
				if (reducedMatrix[i][j] != INF && row[i] != INF) {
					reducedMatrix[i][j] -= row[i];
				}
			}
		}
		return row;
	}
    
    //Find each col min value and later subtract it to make new reduced matrix
	private static int[] colReduction(int reducedMatrix [][] ) {
		int[] col = new int[inputQuestion.length];
		Arrays.fill(col, INF);

		//Looking for the min value from each col 
		for (int i = 0; i < inputQuestion.length; i++) 
		{
			for (int j = 0; j < inputQuestion.length; j++) 
			{
				if (reducedMatrix[i][j] < col[j]) {
					col[j] = reducedMatrix[i][j];
				}
			}
		}

		//Subtract new min value to make new reduced matrix
		for (int i = 0; i < inputQuestion.length; i++) 
		{
			for (int j = 0; j < inputQuestion.length; j++) 
			{
				if (reducedMatrix[i][j] != INF && col[j] != INF) {
					reducedMatrix[i][j] -= col[j];
				}
			}
		}

		return col;
	}
    
    public static class NodeComparator implements Comparator 
	{
	    @Override
	    public int compare(Object s1, Object s2) {
	        Node n1 = (Node) s1;
	        Node n2 = (Node) s2;
	        if (n1.bound > n2.bound) {
	            return 1;
	        }
	        if (n1.bound < n2.bound) {
	            return -1;
	        } else 
	        {
	            return 0;
	        }
	    }
	}
	  
    public static void fileReader(String path) throws Exception {
	      
		inputQuestion = new int[5][5];
		//Scan txt file 
		Scanner sc = new Scanner(new BufferedReader(new FileReader(path)));
     	    
		//Read while the scan still has next integers 
		while(sc.hasNextLine()) 
	    {         	    	  
			for (int i=0; i< inputQuestion.length; i++) 	    	  
			{	            
				String[] line = sc.nextLine().trim().split(" ");	            
				for (int j=0; j<line.length; j++) 
	            {
					inputQuestion[i][j] = Integer.parseInt(line[j]);
	            }	         
	    	  }	      
	    }
		sc.close();
	}
       
}