import java.util.Random;
import java.util.*;

/**
 * This class generates random variables for distributions not part of the Java API
 */
public class RandomGenerator extends Random
{
	public RandomGenerator(long seed)
	{
		super(seed);
	}

	/**
	 * Returns the next integer value following a Poisson Distribution
	 *
	 * @param lambda the average rate of the even of interest occurring
	 *
	 * @return the next integer value following a Poisson Distribution
	 */
	public int nextPoisson(double lambda)
	{
  		double elambda = Math.exp(-1 * lambda);
		double product = 1;
		int count  = 0;
		int result = 0;

		while (product >= elambda)
		{
			product *= nextDouble();
			result = count;
			count++;
		}

		return result;
	}

	/**
	 * Returns an integer array containing the Poisson Arrival Process over a given time
	 *
	 * @param T the upper time interval bound
	 * @param n the number of arrivals in a given time
	 *
	 * @return an integer array containing the Poisson Arrival Process over a given time
	 */
	public int[] poissonProcess(int T, int n)
	{
		int array[] = new int[n];

		for (int x = 0; x < array.length; x++)
			array[x] = (int)nextDouble() * T;
		Arrays.sort(array);

		return array;
	}

	/**
	 * Returns the next Gaussian ("normally") distributed integer value with a given mean and standard deviation
	 *
	 * @param mean the mean
	 * @param standardDeviation the standard deviation
	 *
	 * @return the next Gaussian ("normally") distributed integer value
	 */
	public int nextGaussian(double mean, double standardDeviation)
	{
		int value;

		do
		{
			value = (int) ((nextGaussian() * standardDeviation) + mean);
		} while(value <= 0);

		return value;
	}
}