import java.util.Random;
import java.util.*;

public class DistGen extends Random
{
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

	public int[] poissonProcess(int T, int n)
	{
		int array[] = new int[n];

		for (int x = 0; x < array.length; x++)
			array[x] = (int)nextDouble() * T;
		Arrays.sort(array);

		return array;
	}

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