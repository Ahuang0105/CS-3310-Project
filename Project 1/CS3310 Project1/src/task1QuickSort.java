import java.util.Random;
import java.util.Scanner;

/**
 * This class will demonstrates the quick sort implementation
 * for Task 1.
 * @author Alan Huang
 */
public class task1QuickSort {
	
	static int[] num; // My random number array
	static int n = 1; // The n number user choose
	static long nano_startTime;
	static long nano_endTime;
	private static Scanner input;

	/**
	 * This method will run the code
	 */
	public void run( ) 
	{
		System.out.print("Enter 0 to stop program " +"\n");
		
		while(n != 0)
		{		
			System.out.print("Please enter new n number or 0 to stop: ");
		
			input = new Scanner(System.in); //Input from the user       
			n = input.nextInt();                
			randomArray(n); //Call randomArray to create random size array
			
			nano_startTime = System.nanoTime(); // By calling nano_startTime it will count the execution time
			quickSort(num, 0, num.length-1);         
	        nano_endTime = System.nanoTime();  // By calling nano_endTime it will stop counting the execution time
			
			if(n > 1)
			{			
				printResult();
			}
			
			System.out.println(" ");
		}  
		
		System.out.print("Quick sort program end\n");
	
	}
	
	
	/**
	 * The recursive quick sort
	 * @param inputArry is the random n size array
	 * @param low is the first element of array
	 * @param high is the last element of array
	 */	
	static void quickSort(int inputArry[], int low, int high) 
    { 
        if (low < high) 
        {  
            int pi = partition(inputArry, low, high); 
            quickSort(inputArry, low, pi-1); 
            quickSort(inputArry, pi+1, high); 
        } 
    }
	
	/**
	 * This method will use the first element as pivot to sort the array
	 * and return the final location of the pivot
	 * @param inputArry random n size array
	 * @param low is the first element of array
	 * @param high is the last element of array
	 * @return pivot final location index
	 */
	static int partition(int inputArry[], int low, int high) 
    { 
        int pivotItem = inputArry[low];  // choose first item as pivot 
        int j = (low+1);  
        int i;
        
        for (i = (low+1); i <= high; i++) 
        { 
            // If current element is smaller than the pivot 
            if (inputArry[i] < pivotItem) 
            { 

            	//Exchange s[i] and s[j]
                int temp = inputArry[i]; 
                inputArry[i] = inputArry[j]; 
                inputArry[j] = temp; 
                j++;
            } 
        } 
     
        //Exchange pivotItem at pivot point
        int temp = inputArry[low]; 
        inputArry[low] = inputArry[j-1]; 
        inputArry[j-1] = temp;
 
        //Return pivot index
        return j-1; 
    }
	
	/**
	 * This method will create random n size array 
	 * @param n size enter by the user
	 */
	public static void randomArray(int n) 	
	{			
		num = new int[n];			
		Random r = new Random();			
		int Low = 1;			
		int High = n;						
		for (int i = 0; i < n; i++) {				
			num[i] = r.nextInt(High - Low) + Low;
		}
	}
		
	/**
	 *  This method will print out the sorted array
	 */
	public static void printResult ()
	{				                     
        for(int i = 0; i < n ; i++) 
        {
        	System.out.print(num[i] +" "); // Print out sorted array
        }      
        System.out.println("\nTime taken by quick sort: " + (nano_endTime - nano_startTime)+ " ns"); //Display execution time
	}
}
