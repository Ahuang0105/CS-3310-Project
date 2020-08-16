import java.util.Random;
import java.util.Scanner;

/**
 * This class will demonstrates the merge sort implementation
 * for Task 1.
 * @author Alan Huang
 */
public class task1MergeSort {
	
	static int[] num; // My random number array
	static int n = 1; // The n number user choose
	static long nano_startTime;
	static long nano_endTime;
	private static Scanner input;
	
	/**
	 * This method will run the code
	 */
	public void run( ) {

		System.out.print("Enter 0 to stop program " +"\n");
		
		while(n != 0)
		{		
			System.out.print("Please enter new n number or 0 to stop: ");
		
			input = new Scanner(System.in); //Input from the user       
			n = input.nextInt();                
			randomArray(n); //Call randomArray to create random size array
			
			nano_startTime = System.nanoTime(); // By calling nano_startTime it will count the execution time
	        mergeSort2(0, num.length-1, num);         
	        nano_endTime = System.nanoTime();  // By calling nano_endTime it will stop counting the execution time
			
			if(n > 1)
			{			
				printResult();
			}
			
			System.out.println(" ");
		}  
		
		System.out.print("merge sort program end\n");
	}
		
	/** 
	 * The recursive merge sort
	 * @param low is the first element of array
	 * @param high is the last element of array
	 * @param inputArry is the random n size array
	 */
	static void mergeSort2(int low, int high, int inputArry[]) 
    { 
        if (low < high) // make sure there is more than 1 element in array
        {           
            int mid = (low+high)/2; //Calculate mid value          
            mergeSort2( low, mid, inputArry); 
            mergeSort2( mid+1, high, inputArry); 
            merge2(low, mid, high, inputArry); 
        } 
    } 
	
	/**
	 * This method will merge and sort the the array
	 * @param low is the first element of array
	 * @param mid is the mid element of array
	 * @param high is the last element of array
	 * @param inputArry is the random n size array
	 */
	static void merge2(int low, int mid, int high, int inputArry[]) 
    { 
				
		int k = low;
        int left1 = mid - low + 1; //Total left side value
        int right2 = high - mid;   //Total right side value 
        int Left[] = new int [left1]; //Create left and right array to temporary save number into those array
        int Right[] = new int [right2]; 
  
        //Copy value from s to left array
        for (int i=0; i<left1; ++i) 
        { 
        	Left[i] = inputArry[low + i]; 
        }
        //Copy value from s to right array
        for (int j=0; j<right2; ++j)
        {
        	Right[j] = inputArry[mid + 1+ j]; 
        }
  

        int i = 0;
        int	j = 0; 
        
        //Start sorting array in increasing order
        while (i < left1 && j < right2) 
        { 
            if (Left[i] <= Right[j]) 
            { 
            	inputArry[k] = Left[i]; 
                i++; 
            } 
            else
            { 
            	inputArry[k] = Right[j]; 
                j++; 
            } 
            k++; 
        } 
  
        //Copy remaining value to s
        while (i < left1) 
        { 
        	inputArry[k] = Left[i]; 
            i++; 
            k++; 
        } 
        
        while (j < right2) 
        { 
        	inputArry[k] = Right[j]; 
            j++; 
            k++; 
        } 
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
        System.out.println("\nTime taken by merge sort: " + (nano_endTime - nano_startTime)+ " ns"); //Display execution time
	}

  
}
	
