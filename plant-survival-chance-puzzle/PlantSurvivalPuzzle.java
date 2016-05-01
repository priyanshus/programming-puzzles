

public class PlantSurvivalPuzzle {

	/**
	 * Algorithm to iterate through neighborhood cells and
	 * counts live and dead plants. On the basic of counts of
	 * live and dead plants in neighborhood, it decided whether current
	 * plan will survive in next generation or not
	 * 
	 * @param input Double dimension input matrix
	 * @param m X coordinate of the cell for which survival chances need to find out
	 * @param n Y coordinate of the cell for which survival chances need to find out
	 * @param s1 Survival rule i.e. minimum live plants count required in neighborhood
	 * @param s2 Survival rule i.e. maximum live plants count required in neighborhood
	 * @param b1 Survival rule for dead plant
	 * @param b2 Survival rule for dead plant
	 * @return returns 1 or 0 where 1 = Will survive in next generation
	 *         0 = Will be dead in next generation
	 */
	public static int getNextGenerationStatus(int[][] input, int m, int n, int s1, int s2,
			int b1, int b2) {
		int row = input.length - 1;
		int col = input[0].length - 1;
		int currentStatus = 0;
		int livePlantsCount = 0, deadPlantsCount = 0;

		int startPosX = (m - 1 < 0) ? m : m - 1;
		int startPosY = (n - 1 < 0) ? n : n - 1;
		int endPosX = (m + 1 > row) ? m : m + 1;
		int endPosY = (n + 1 > col) ? n : n + 1;

		for (int rowNum = startPosX; rowNum <= endPosX; rowNum++) {
			for (int colNum = startPosY; colNum <= endPosY; colNum++) {

				if (rowNum == m & colNum == n) {
					currentStatus = input[rowNum][colNum];
				}

				int neighbourState = input[rowNum][colNum];

				if (neighbourState == 1) {
					livePlantsCount++;
				} else {
					deadPlantsCount++;
				}

			}
		}

		if (currentStatus == 1) {
			livePlantsCount = livePlantsCount - 1;
		} else {
			deadPlantsCount = deadPlantsCount - 1;
		}

		System.out.println("Initial State: " + currentStatus);
		System.out.println("Survival count: " + livePlantsCount);
		System.out.println("Dead count: " + deadPlantsCount);
		int survivalChance = checkSurvivalChance(currentStatus, livePlantsCount, deadPlantsCount,
				s1, s2, b1, b2);
		System.out.println("Survival Chance: " + survivalChance);
		return survivalChance;
	}

	/**
	 * Algorithm to check survival chance in next generation
	 * 
	 * @param currentStatus Current plant status i.e. 1 = Live 0 = Dead
	 * @param neighbourLivePlants Neighborhood live plants count
	 * @param neighboutDeadPlants Neighborhood dead plants count
	 * @param s1 Minimum live plants required to survive in next generation live plant
	 * @param s2 Maximum live plants required to survive in next generation live plant
	 * @param b1 Minimum live plants required to survive in next generation dead plant
	 * @param b2 Maximum live plants required to survive in next generation dead plant
	 * @return Either 0 or 1 
	 */
	private static int checkSurvivalChance(int currentStatus, int neighbourLivePlants,
			int neighboutDeadPlants, int s1, int s2, int b1, int b2) {
		if (currentStatus == 0) {
			if (neighbourLivePlants >= b1 & neighbourLivePlants <= b2) {
				return 1;
			} else {
				return 0;
			}
		} else {
			if (neighbourLivePlants >= s1 & neighbourLivePlants <= s2) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	/**
	 * Utility function to covert input into double dimension array
	 */
	public static int[][] convertInputIntoDoubleDimensionArray(int row,
			int col, int[] input) {
		int[][] ddArray = new int[row][col];
		int c = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {

				ddArray[i][j] = input[c++];
			}
		}

		return ddArray;
	}

	/**
	 * Utility function to convert output into single dimension array
	 */
	public static int[] convertOutputIntoSingleDimensionArray(int[][] output) {
		int[] sdArray = new int[output.length * output[0].length];
		int c = 0;
		for (int i = 0; i < output.length; i++) {
			for (int j = 0; j < output[0].length; j++) {
				sdArray[c] = output[i][j];
				c++;
			}
		}

		return sdArray;
	}

	public static void main(String[] arg) {
		int[] input1 = { 3, 4, 2, 3, 3, 3, 4 };
		int[] input2 = { 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0 };
		int s1 = input1[2], s2 = input1[3], b1 = input1[4], b2 = input1[5];
		int g = input1[6];
		int[][] nextGenMatrix;

		int[][] inputMatrix = convertInputIntoDoubleDimensionArray(input1[0],
				input1[1], input2);

		// Iterate through generations
		for (int x = 1; x <= g; x++) {
			nextGenMatrix = new int[inputMatrix.length][inputMatrix[0].length];
			
			// Creates a new matrix for next generation
			for (int i = 0; i < inputMatrix.length; i++) {
				for (int j = 0; j < inputMatrix[0].length; j++) {
					nextGenMatrix[i][j] = getNextGenerationStatus(inputMatrix, i, j, s1, s2, b1,
							b2);
				}
			}

			// Replaces input matrix with next generation matrix
			inputMatrix = nextGenMatrix;
		}

		// Final output in single dimension array
		int[] output = convertOutputIntoSingleDimensionArray(inputMatrix);
	}

}
