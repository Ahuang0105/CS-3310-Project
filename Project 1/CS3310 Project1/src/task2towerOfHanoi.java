import java.util.Scanner;

/**
 * This class will demonstrates the Tower of Hanoi implementation
 * for Task 2.
 * @author Alan Huang
 */
public class task2towerOfHanoi {
	
	private static Scanner input;	
	static int n = 1; // The n number user choose

	/**
	 * This method will run the code
	 */
	public void run( ) {

		System.out.print("Enter 0 to stop program " +"\n");		
		while(n != 0)
		{
			
			System.out.print("Please enter new n number or 0 to stop: ");		
			input = new Scanner(System.in); //Input from the user		
			n =  input.nextInt();
			
			if(n > 1)		   
			{			
				long nano_startTime = System.nanoTime(); // By calling nanoTime it will count the execution time	    			
				towerOfHanoi(n, 'A', 'C', 'B');  // A, B and C are names of rods         	    			
				long nano_endTime = System.nanoTime();  // By calling nano_endTime it will stop counting the execution time	    	    	      						    	   
				System.out.println("\nTime taken by towerOfHanoi: " + (nano_endTime - nano_startTime)+ " ns"); //Display execution time		   
			}
			
			System.out.println();		
		}		
		System.out.print("Tower of hanoi program end\n");
	}
	
	/**
	 * The recursive tower of Hanoi method
	 * @param n is how many disks user want
	 * @param from the rod the disk is moving from
	 * @param to the rod the disk is moving to
	 * @param aux the rod that help the process
	 */
	private static void towerOfHanoi(int n, char from, char to, char aux) 
    { 
        if (n == 1) 
        { 
            System.out.println("\nMove Disk 1 from " + from + " to " + to); 
            return; 
        } 
        
        towerOfHanoi(n-1, from, aux, to);         
        System.out.println("\nMove Disk " + n + " from " + from + " to " + to); 
        towerOfHanoi(n-1, aux, to, from);        
    }  

}
