import java.util.Scanner;

public class Main {
	
	static int n = 1;
	private static Scanner input;

	/**
	 * This is the main class to test my project
	 */
	public static void main(String[] args) {
		
		task1MergeSort task1 = new task1MergeSort();
		task1QuickSort task1_2 = new task1QuickSort();		
		task2towerOfHanoi task2 = new task2towerOfHanoi();		
		task3Matrix task3 = new task3Matrix();
	
		while(n != 0)
		{
			
			System.out.println("Please enter 1 for MergeSort");
			System.out.println("             2 for QuiclSort");
			System.out.println("             3 for Tower Of Hanoi");
			System.out.println("             4 for Matrix multiplication");
			System.out.println("             0 to exit the program\n");
			
			System.out.print("Please enter your choose: ");
		
			input = new Scanner(System.in);
			n = input.nextInt();                
			
			if(n == 1)
			{
				task1.run();
			}
			else if (n == 2)
			{
				task1_2.run();
			}
			else if (n == 3)
			{
				task2.run();
			}
			else if (n == 4)
			{
				task3.run();
			}
					
			System.out.println(" ");
		} 
		
		System.out.print("Program end\n");		
	}

}
