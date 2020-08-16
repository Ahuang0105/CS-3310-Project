import java.util.Arrays;
import java.util.Scanner;


public class project2 {
	
	static int capacity;
	static int n;	
	private static Scanner input;
	
	public static void main(String[] args) {
		
		int w, p;
		
		//Ask knapsack capacity
		System.out.print("Please enter knapsack capacity: ");
		input = new Scanner(System.in);		
		capacity = input.nextInt();
		
		//Ask total n number
		System.out.print("Please enter total object number: ");
		n = input.nextInt();
		
		objects[] items = new objects[n];  //Create array object that hold weight and value
		
	    //Ask user for input
		for(int i = 0; i < n; i++) {
			System.out.print("Please enter object " + (i+1) + " weight: ");
			w = input.nextInt();
		
			System.out.print("Please enter object " + (i+1) + " profit: ");
			p = input.nextInt();
			
			items[i] = new objects(w, p, i+1); 				
		}
		
		System.out.println("\n");
	
		//Calling dp fractional knapsack
		fractionalKnapsack(items, capacity, n);
		//Calling dp knapsack
		knapsackDynamicProgramming(items, capacity, n);
		
	}
	
	//Fractional knapsack
	public static void fractionalKnapsack(objects[] items, int capacity, int n)
	{
		
		//use bubble sort to sort item by their ratio
		for (int i = 0; i < n-1; i++) 
		{ 
            for (int j = 0; j < (n-i-1); j++) 
            {
                if (items[j].ratio < items[j+1].ratio) 
                {                    
                	objects temp = items[j]; 
                    items[j] = items[j+1]; 
                    items[j+1] = temp; 
                }
            }
		}
		
		int weightSubtr = capacity;
		int totalProfit = 0;
		int [] itemInKnapsack = new int [n];
		
		for(int i = 0; i < n; i++)
		{					
			if(weightSubtr-items[i].weight >= 0)
			{
				weightSubtr = weightSubtr - items[i].weight; // Subtract weight form capacity
				totalProfit += items[i].profit; // Adding total profit
				itemInKnapsack[i] = items[i].index; //Save item number 
			}
			else 
			{
				double partOfItem = ((double)weightSubtr/(double)items[i].weight); //Current weight / current items weight
				totalProfit += (items[i].profit * partOfItem); // Calculate parts of profit
                capacity = (int)(capacity - (items[i].weight*partOfItem));  //Calculate new weight
                itemInKnapsack[i] = items[i].index; //Save item number 
                break; 
			}
		}
		
		printResult(itemInKnapsack, totalProfit, 1 ); //Call print result
	}
	
	// 0/1 knapsack dynamic programming approach
	public static void knapsackDynamicProgramming(objects[] items, int capacity, int n) 
    { 
		//use bubble sort to sort item by their weight				
		for (int i = 0; i < n-1; i++) 				
		{ 		           
			for (int j = 0; j < (n-i-1); j++) 		            
			{		                
				if (items[j].weight < items[j+1].weight) 		               
				{                    		                	
					objects temp = items[j]; 		                    
					items[j] = items[j+1]; 		                    
					items[j+1] = temp; 		                					
				}		            
			}				
		}
		
    	if(n <= 0 || capacity <= 0)
    	{
    		System.out.print("Error capacity and object numbers can't be 0");
    	}
     
    	int matrixTable[][] = new int[n+1][capacity+1]; 
           
    	for (int i = 0; i <= n; i++)     
    	{         
    		for (int j = 0; j <= capacity; j++)          
    		{     
    			//Preset row 0 value and column value to 0
    			if (i==0 || j==0) 
            	 {
    				matrixTable[i][j] = 0; 
            	 }
             
    			//If current row number is greater than current weight number
    			else if (items[i-1].weight <= j) 
    			{
    				//Compare the top value with top value plus value(use weight as the value to go left)result
    				//and use the greatest value for the current one.
    				matrixTable[i][j] = Math.max(items[i-1].profit + matrixTable[i-1][j-items[i-1].weight],  matrixTable[i-1][j]);   
    			}
    			//If row number is less than current weight number just use the top value
    			else 
    			{      	 
    				matrixTable[i][j] = matrixTable[i-1][j];
    			}
    		}         	
    	} 
    	
		int totalProfit = matrixTable[n][capacity]; 		
		int temp = matrixTable[n][capacity];
		int [] itemInKnapsack = new int [n];
		
		//solving which item is in the knapsack
        for (int i = n; i > 0 && temp > 0; i--) { 
  
           
        	if (temp == matrixTable[i - 1][capacity]) 
        	{
                continue;
        	}
        	//if the top value is different than it is include in the knapsack
            else 
            {   
                itemInKnapsack[i-1] = items[i-1].index; //Save item number
                temp = temp - items[i - 1].profit; 
                capacity = capacity - items[i - 1].weight; 
            } 
        } 
        
        printResult(itemInKnapsack, totalProfit, 2); //Call print result
    }
	
	//Method to print result
	public static void printResult(int[] index, int profit, int token)
	{
		Arrays.sort(index);
		
		if(token == 1) 
		{					
			System.out.println("Fractional KnapsackTotal profit is " + profit);		
			System.out.print("Object in fractional Knapsack is ");
		}
		else
		{
			System.out.println("Dynamic programming knapsack profit is " + profit);
			System.out.print("Object in dp Knapsack is ");
		}
		
		for(int i =0; i < n; i++)
		{
			if(index[i] > 0) // only print items that are saved in index
			{
				System.out.print( "'" + index[i]+"'" +" ");
			}
		}	
		
		System.out.println("\n");
					
	}
	
	//object class
	static class objects  
    { 
		double ratio; 
        int weight;
        int profit;
        int index; 
          
        // item value function 
        public objects(int weight, int profit, int index) 
        { 
            this.weight = weight; 
            this.profit = profit; 
            this.index = index; 
            ratio = ((double)profit/(double)weight ); 
        } 
    
    } 

}



