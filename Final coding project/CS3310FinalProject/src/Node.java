import java.util.Arrays;

//Node for TSP
class Node {

	int[] path;
	int reducedMatrix[][];
	int bound;
	int vertex;
	int level;

	public Node(int[][] parentMatrix, int[] parentPath, int level, int i, int j) {

		path = Arrays.copyOf(parentPath, level+1);
		path[level] = j;
		vertex = j;
		this.level = level;
		reducedMatrix = new int[parentMatrix.length][parentMatrix.length];
		
		//Copy the new reduced matrix value 
		for (int k = 0; k < parentMatrix.length; k++) {
			reducedMatrix[k] = Arrays.copyOf(parentMatrix[k], parentMatrix.length);
		}

		if (level > 0) 
		{			
			for (int k = 0; k < parentMatrix.length; k++) 
			{
				reducedMatrix[i][k] = TSP.INF; //Set outgoing vertex row to all infinite 
				reducedMatrix[k][j] = TSP.INF; //Set next vertex col to all infinite
			}
		}

		reducedMatrix[j][0] = TSP.INF;
	}

}