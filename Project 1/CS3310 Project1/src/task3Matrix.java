import java.util.Random;
import java.util.Scanner;

/**
 * This class will demonstrates the classical and Strassen's matrix multiplication
 * implementation for Task 3.
 * @author Alan Huang
 */
public class task3Matrix {
	
	static int n = 1; // The n number user choose
	static int[][] firstMatrix;
	static int[][] secondMatrix;
	static int[][] resultMatrixClassic;
	static int[][] resultMatrixStrassen;
	
	static long strassen_startTime;
	static long strassen_endTime;
	static long classical_startTime;
	static long classical_endTime;
	private static Scanner input;
	
	/**
	 * This method will run the code
	 */
	public void run( ) {
		
		System.out.print("Enter 0 to stop program " +"\n");		
		while(n != 0)
		{		
			input = new Scanner(System.in);		
			System.out.print("Please enter new n number or 0 to stop: ");		
			n = input.nextInt();
		
			if(n > 1)
			{
				randomArray(n);	// Call randomArray to create my matrix
				strassen_startTime = System.nanoTime(); // By calling nanoTime it will count the execution time		
				resultMatrixStrassen = strassenMatrix(firstMatrix, secondMatrix ); // Call strassenMain to calculate my result	
				strassen_endTime = System.nanoTime();
				
				classical_startTime = System.nanoTime(); // By calling nanoTime it will count the execution time		
				classicalMatrixMultiply(n); // Call classical matrix multiply to calculate my result		
				classical_endTime = System.nanoTime();
					    	   
				printMatrix(n);
			}
			
			System.out.println();
		}
		System.out.print("matrix program end\n");			
	}			
	
	
	/**
	 * This is my classical matrix multiplication
	 * with the in class exercise #6 algorithm
	 * @param n will be the matrix size enter by the user
	 */
	public static void classicalMatrixMultiply(int n ) 
	{	
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				resultMatrixClassic[i][j] = firstMatrix[i][0] * secondMatrix[0][j];
				for (int k = 1; k < n; k++) {
					resultMatrixClassic[i][j] = resultMatrixClassic[i][j] + firstMatrix[i][k] * secondMatrix[k][j];
				}
			}
		}
	}
	
	/**
	 * This method will print our the result matrix and time 
	 * @param n will be the matrix size enter by the user
	 */
	public static void printMatrix(int n)  
	{
		System.out.println("Final Matrix is: \n");
		System.out.println("Stressen");
	   
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
			System.out.print(resultMatrixStrassen[i][j] + " ");	
			}	
			System.out.println();
		}
			
		System.out.println("Time taken by Stressen: " + (strassen_endTime - strassen_startTime)+ " ns" +"\n"); //Display execution time		
		System.out.println();
						
		System.out.println("Classical");
			
		for (int i = 0; i < n; i++) {				
			for (int j = 0; j < n; j++) {					
				System.out.print(resultMatrixClassic[i][j] + " ");				
			}				
			System.out.println();		
		}			
		System.out.println("Time taken by Classical: " + (classical_endTime - classical_startTime)+ " ns" +"\n"); //Display execution time		
	}
	
	/**
	 * //This method will create random n size matrix 
	 * @param n will be the matrix size enter by the user
	 */
	public static void randomArray(int n) 		
	{			
		// Use user input n to create random size matrix			
		Random r = new Random();			
		firstMatrix = new int[n][n];			
		secondMatrix = new int[n][n];			
		resultMatrixStrassen = new int[n][n];				
		resultMatrixClassic = new int [n][n];			
			
		//The domain of my random number is 1 to 100			
		int Low = 1;
		int High = 100;

		//Create the first matrix
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				firstMatrix[i][j] = r.nextInt(High-Low) + Low;
			}
		}
		
		//Create the first matrix
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				secondMatrix[i][j] = r.nextInt(High-Low) + Low;
			}
		}
			
	}
	
	/**
	 * This is my Strassen's Matrix multiplication
	 * The n size has to be a n size of 2
	 * @param firstmatrix random n size matrix
	 * @param secondMatrix random n size matrix
	 * @return the final result of calculation
	 */
	public static int[][] strassenMatrix(int[][] firstmatrix, int[][] secondMatrix) {

		int n = firstmatrix.length;
		int[][] result = new int[n][n];

		// Base case
		if (n == 2) 
		{
			result[0][0] = (firstmatrix[0][0] * secondMatrix[0][0]) + (firstmatrix[0][1] * secondMatrix[1][0]); //C11 = A11 * B11 + A12 * B21 
			result[0][1] = (firstmatrix[0][0] * secondMatrix[0][1]) + (firstmatrix[0][1] * secondMatrix[1][1]); //C12 = A11 * B12 + A12 * B22
			result[1][0] = (firstmatrix[1][0] * secondMatrix[0][0]) + (firstmatrix[1][1] * secondMatrix[1][0]); //C21 = A21 * B11 + A22 * B21
			result[1][1] = (firstmatrix[1][0] * secondMatrix[0][1]) + (firstmatrix[1][1] * secondMatrix[1][1]); //C22 = A21 * B12 + A22 * B22
		}
		else 
		{	
			int[][] A11 = new int[n / 2][n / 2];
			int[][] A12 = new int[n / 2][n / 2];
			int[][] A21 = new int[n / 2][n / 2];
			int[][] A22 = new int[n / 2][n / 2];
			int[][] B11 = new int[n / 2][n / 2];
			int[][] B12 = new int[n / 2][n / 2];
			int[][] B21 = new int[n / 2][n / 2];
			int[][] B22 = new int[n / 2][n / 2];

			// Partition A into 4 submatrices
			split(firstmatrix, A11, 0, 0);
			split(firstmatrix, A12, 0, n / 2);
			split(firstmatrix, A21, n / 2, 0);
			split(firstmatrix, A22, n / 2, n / 2);

			// Partition B into 4 submatrices
			split(secondMatrix, B11, 0, 0);
			split(secondMatrix, B12, 0, n / 2);
			split(secondMatrix, B21, n / 2, 0);
			split(secondMatrix, B22, n / 2, n / 2);


			int[][] P = strassenMatrix(add(A11, A22), add(B11, B22));  // P = (A11 + A22)(B11 + B22)
			int[][] Q = strassenMatrix(add(A21, A22), B11);		       // Q = (A21 + A22) B11
			int[][] R = strassenMatrix(A11, sub(B12, B22));            // R = A11 (B12 - B22)
			int[][] S = strassenMatrix(A22, sub(B21, B11));            // S = A22 (B21 - B11)
			int[][] T = strassenMatrix(add(A11, A12), B22);            // T = (A11 + A12) B22
			int[][] U = strassenMatrix(sub(A21, A11), add(B11, B12));  // U = (A21 - A11) (B11 + B12)
			int[][] V = strassenMatrix(sub(A12, A22), add(B21, B22));  // V = (A12 - A22) (B21 + B22)


			int[][] C11 = add(sub(add(P, S), T), V); // C11 = P + S - T + V
			int[][] C12 = add(R, T);				 // C12 = R + T		
			int[][] C21 = add(Q, S);                 // C21 = Q + S
			int[][] C22 = add(sub(add(P, R), Q), U); // C22 = P + R - Q + U

			//Joint 4 submatrices 
			join(C11, result, 0, 0);
			join(C12, result, 0, n / 2);
			join(C21, result, n / 2, 0);
			join(C22, result, n / 2, n / 2);
		}
		return result;
	}
	
	/**
	 * This method will do matrix subtraction
	 * @param firstElement random n size matrix from strassenMatrix
	 * @param secondElement random n size matrix from strassenMatrix
	 * @return result of subtraction
	 */
	public static int[][] sub(int[][] firstElement, int[][] secondElement) {

		int n = firstElement.length;
		int[][] result = new int[n][n];

		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				result[i][j] = firstElement[i][j] - secondElement[i][j];
			}
		}
		return result;
	}

	/**
	 * This method will do matrix addition
	 * @param firstElement random n size matrix from strassenMatrix
	 * @param secondElement random n size matrix from strassenMatrix
	 * @return result of addition
	 */
	public static int[][] add(int[][] firstElement, int[][] secondElement) {

		int n = firstElement.length;

		int[][] result = new int[n][n];
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				result[i][j] = firstElement[i][j] + secondElement[i][j];
			}
		}
		return result;

	}

	/**
	 * // Partition matrix into submatrices
	 * @param inputMatrix the input matrix that we want to split
	 * @param resultMatrix the result of splitted matrix
	 * @param from start range
	 * @param to end range	
	 */
	public static void split(int[][] inputMatrix, int[][] resultMatrix, int from, int to) {

		for (int i1 = 0, i2 = from; i1 < resultMatrix.length; i1++, i2++)
		{	
			for (int j1 = 0, j2 = to; j1 < resultMatrix.length; j1++, j2++)
			{
				resultMatrix[i1][j1] = inputMatrix[i2][j2];
			}
		}
	}

	/**
	 * // Merge submatrices matrix together
	 * @param inputMatrix the input matrix that we want to joint
	 * @param resultMatrix the result of jointed matrix
	 * @param from start range
	 * @param to end range
	 */
	public static void join(int[][] inputMatrix, int[][] resultMatrix, int from, int to) {

		for (int i1 = 0, i2 = from; i1 < inputMatrix.length; i1++, i2++)
		{
			for (int j1 = 0, j2 = to; j1 < inputMatrix.length; j1++, j2++)
			{
				resultMatrix[i2][j2] = inputMatrix[i1][j1];
			}	
		}
	}

}