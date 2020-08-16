import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class SudokuPuzzleSolver {
	
	public static int [][] inputQuestion;
	public static String solution;
	public static int zero = 0;
	public static String filePath;
	
	public static void main(String args[]) throws Exception 
	{
		System.out.println("Please enter input txt file path");
		System.out.println("Example: C:/Users/d/Desktop/Test.txt \n");
		Scanner input = new Scanner(System.in);
		filePath = input.nextLine();
		
		fileReader( filePath ); // read input file
		
		//Print solution
		if( SudokuSolver() )
		{
			System.out.println("Solution found \n\n");
			printMatrix(inputQuestion);
		}
		else
		{
			System.out.println("No solution found");
		}
		
	}
	
	//The back tracking method for sodoku
	public static boolean SudokuSolver() {
		      		
		for (int row = 0; row < 9; row++) {        
			for (int col = 0; col < 9; col++) {           	 
				//If current box is still 0 we need to test for solution       	 
				if (inputQuestion[row][col] == 0) {             		 
					//Test 1 to 9 and see which one is save to use        		 
					for (int number = 1; number <= 9; number++) 
					{      
						//Check row, col, and 3x3 square to make sure no repeat nubmer
						if (checkRow(row, number) && checkCol(col, number) && checkSquare(row, col, number))         			 
						{                                    				 
							inputQuestion[row][col] = number;                    				         				 
							if ( SudokuSolver() ) // If no solution found then we need to backtracking   
							{
								return true;
							}								                     				       				 
							else    
							{
								inputQuestion[row][col] = zero;  
							}								                      				 							                     			 
						}                   		
					}               		 
					return false;                  	 
				}                
			}        		
		}
         return true; 
	}
	
	//This method is use to check row has no repeat number
	public static boolean checkRow(int row, int numbers)
	{
		for (int i = 0; i < 9; i++) {
			if(inputQuestion[row][i] == numbers) {
				return false;
			}
		}
		
		return true;
	}
	
	//This method is use to check row has no repeat number
	public static boolean checkCol(int col, int numbers)
	{
		for (int i = 0; i < 9; i++) {
			if(inputQuestion[i][col] == numbers) {
				return false;
			}
		}
		
		return true;
	}
	
	//This method is use to check the 3x3 box has no repeat number
	private static boolean checkSquare(int row, int col, int numbers) {
		
		int rowRange = row - row % 3;
		int colRange = col - col % 3;
		
		for (int i = rowRange; i < rowRange + 3; i++) {
			for (int j = colRange; j < colRange + 3; j++)  {
				if (inputQuestion[i][j] == numbers)
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	//This method will read txt file and copy it to array
	public static void fileReader( String path ) throws Exception {
	      
		inputQuestion = new int[9][9];
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
	
	//This method is use to print solution matrix
	public static void printMatrix(int[][] matrix) {        
		
		solution = ""; //To store array 
				
        if (matrix != null) 
        {
            for (int row = 0; row < matrix.length; row++) 
            {
            	solution += " ";
                
                for (int col = 0; col < matrix.length; col++) 
                {
                	solution += String.format("%2d",  matrix[row][col]);
                    
                    if (col < matrix.length - 1) 
                    {
                    	solution += " | ";
                    }
                }
                if (row < matrix.length - 1) 
                {
                	solution += "\n";
                    for (int col = 0; col < matrix.length; col++) 
                    {
                        for (int i = 0; i < 4; i++) 
                        {
                        	solution += "-";
                        }
                        if (col < matrix.length - 1) 
                        {
                        	solution += "+";
                        }
                    }
                    solution += "\n";
                } else {
                	solution += "\n";
                }
            }
        }
        System.out.println(solution);    
    }	
}


