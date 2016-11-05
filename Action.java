import java.util.Random;

public class Action {

	public  final int NUM_ACTIONS = Abstractions.NUM_ACTIONS;
	public  final int NUM_PLAYERS = 2;
	public static final Random random = new Random();
	double[][] regretSum = new double[NUM_PLAYERS][NUM_ACTIONS], 
			strategy = new double[NUM_PLAYERS][NUM_ACTIONS], 
			strategySum = new double[NUM_PLAYERS][NUM_ACTIONS];

	public double[] getStrategy(int player) {
		double normalizingSum = 0;
		for (int a = 0; a < NUM_ACTIONS; a++) {
			strategy[player][a] = regretSum[player][a] > 0 ? regretSum[player][a] : 0;
			normalizingSum += strategy[player][a];
		}
		for (int a = 0; a < NUM_ACTIONS; a++) {
			if (normalizingSum > 0)
				strategy[player][a] /= normalizingSum;
			else
				strategy[player][a] = 1.0 / NUM_ACTIONS;
			strategySum[player][a] += strategy[player][a];
		}
		return strategy[player];
	}


	public int getAction(double[] strategy) {
		double r = random.nextDouble();
		int a = 0;
		double cumulativeProbability =  0;
		while (a < NUM_ACTIONS - 1) {
			cumulativeProbability += strategy[a];
			if (r < cumulativeProbability)
				break;
			a++;
		}
		return a;
	}

	public double[] getAverageStrategy(int player) {
		double[] avgStrategy = new double[NUM_ACTIONS];
		double normalizingSum = 0;
		for (int a = 0; a < NUM_ACTIONS; a++)
			normalizingSum += strategySum[player][a];
		for (int a = 0; a < NUM_ACTIONS; a++) 
			if (normalizingSum > 0)
				avgStrategy[a] = strategySum[player][a] / normalizingSum;
			else
				avgStrategy[a] = 1.0 / NUM_ACTIONS;
		return avgStrategy;
	}

}