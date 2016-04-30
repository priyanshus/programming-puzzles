
/**
 * Puzzle solution to find out the cost to reach the destination
 */
public class CostPuzzle {

	/**
	 * Test for valid input
	 * In case input does pass the test will stop the further execution with 
	 * console output "NA"
	 * 
	 * 
	 * @param inputMatrix Input Matrix
	 * @param numberOfRows Number of rows in input matrix
	 */
	public static void testValidInput(String[] inputMatrix, int numberOfRows) {
		int[][] matrix = splitIpnutIntoArray(inputMatrix);

		// If number of rows less than or equal to 1, test fails and stops execution
		if (numberOfRows <= 1) {
			System.out.println("NA");
			System.exit(0);
		}

		// If input matrix is null or length zero, test fails and stops execution
		if ((inputMatrix == null) || (inputMatrix.length == 0)) {
			System.out.println("NA");
			System.exit(0);
		}

		// If input matrix length is not equal to input number of rows, test fails
		if (matrix.length != numberOfRows) {
			System.out.println("NA");
			System.exit(0);
		}
		
		// If all rows of input matrix is not same, test fails
		for (int i =1;i<matrix.length;i++) {
			if(matrix[i].length != matrix[0].length) {
				System.out.println("NA");
				System.exit(0);
			}
		}
	}

	/**
	 * Converts input matrix into double dimension array of integers
	 */
	public static int[][] splitIpnutIntoArray(String[] inputMatrix) {
		String[] firtRow = inputMatrix[0].split("\\#");

		int[][] matrix = new int[inputMatrix.length][firtRow.length];

		for (int i = 0; i < inputMatrix.length; i++) {
			String[] temp = inputMatrix[i].split("\\#");
			
			if(temp.length != firtRow.length) {
				System.out.println("NA");
				System.exit(0);
			}

			for (int j = 0; j < temp.length; j++) {
				matrix[i][j] = Integer.parseInt(temp[j]);
			}
		}
		return matrix;
	}

	/**
	 * Dynamic programming algorithm to find the cost to reach (m,n)th element 
	 * of double dimension array
	 * 
	 * The new matrix will get generated calculating the minimum cost
	 *
	 *
	 * 
	 * @param inputMatrix Input matrix
	 * @param m No of rows
	 * @param n No of column
	 * @return New double dimension array calculating the minimum cost
	 */
	public static int[][] additionOfElements(int[][] inputMatrix, int m, int n) {
		int[][] minimumCostMatrix = new int[m + 1][n + 1];
		minimumCostMatrix[0][0] = inputMatrix[0][0];

		// Calculate addition of first row
		for (int i = 1; i <= m; i++) {
			minimumCostMatrix[i][0] = minimumCostMatrix[i - 1][0]
					+ inputMatrix[i][0];
		}

		// Calculate addition of first column
		for (int j = 1; j <= n; j++) {
			minimumCostMatrix[0][j] = minimumCostMatrix[0][j - 1]
					+ inputMatrix[0][j];
		}

		// Calculate addition of rest of the element
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				minimumCostMatrix[i][j] = inputMatrix[i][j]
						+ minimum(minimumCostMatrix, i, j);
			}
		}

		return minimumCostMatrix;
	}

	/**
	 * Utility function to find the minimum of three integers
	 * 
	 * @param inputMatrix
	 * @param i Current row
	 * @param j Current column
	 * @return Minimum of three integers
	 */
	public static int minimum(int[][] inputMatrix, int i, int j) {
		int a = inputMatrix[i - 1][j - 1];
		int b = inputMatrix[i - 1][j];
		int c = inputMatrix[i][j - 1];

		return Math.min(a, Math.min(b, c));
	}

	/**
	 * Calculates the path
	 * 
	 * @param minimumMatrix Minimum matrix to calculate the path
	 * @param m Destination row
	 * @param n Destination column
	 * @return Path string
	 */
	public static String findPath(int[][] minimumMatrix, int m, int n) {
		String path = "";
		int a = 0, b = 0, c = 0;

		for (int i = 0; i <= m - 1;) {
			for (int j = 0; j <= n - 1;) {
			

				if ((i == m) & (j == n - 1)) {
					path = path + "R";
					return path;
				}

				if ((i == m - 1) & (j == n - 1)) {
					path = path + "D";
					return path;
				}

				if ((i == m - 1) & (i == n)) {
					path = path + "B";
					return path;
				}

				if (j < n) {
					a = minimumMatrix[i][j + 1];
				}

				if (i < m) {
					b = minimumMatrix[i + 1][j];
					c = minimumMatrix[i + 1][j + 1];
				}

				int minium = Math.min(a, Math.min(b, c));

				if (a == minium) {
					j++;
					path = path + "R";
				} else if (b == minium) {
					i++;
					path = path + "B";
				} else if (c == minium) {
					i++;
					j++;
					path = path + "D";
				}

			}
		}
		return path;
	}

	public static void main(String[] args) {
		String[] inputMatrix = {"5#7#2#4","1#8#1#3","6#2#9#5","1#6#2#8"};
		int numberOfRows = 4;

		testValidInput(inputMatrix, numberOfRows);
		int[][] originalMatrix = splitIpnutIntoArray(inputMatrix);
		//System.out.println(Arrays.deepToString(costMatrix));

		int m = originalMatrix.length - 1;
		int n = originalMatrix[0].length - 1;

		int[][] minimumMatrix = additionOfElements(originalMatrix, m, n);
		String cost = String.valueOf(minimumMatrix[m][n]);
		
		//System.out.println(Arrays.deepToString(costMatrix));
		String path = findPath(minimumMatrix, m, n);
		String answer = cost + "," + path;
		System.out.println(answer);
	}
}
