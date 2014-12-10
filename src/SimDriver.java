//Steven Ross
//smr77@pitt.edu
//3638819

import java.io.*;
import java.util.*;

public class SimDriver
{
	private static final int QZ_OPERATING_HOURS = 13;

	public static void main(String args[])
	{
		SimulationEngine simulationEngine = new SimulationEngine();
		simulationEngine.runSimulation(1, 1, 2);
		//simulationEngine.runSimulation(QZ_OPERATING_HOURS, 1, 2);

/*		RandomGenerator randomGenerator = new RandomGenerator(1421897149542322L);

		for (int x = 0; x < 60; x++)
			System.out.print(randomGenerator.nextPoisson(3.52) + " ");
		System.out.println();

		int[] poissonProcess = randomGenerator.poissonProcess(60, 210);
		for (Integer number : poissonProcess)
			System.out.print(number + " ");
		System.out.println();

		for (int x = 0; x < 60; x++)
			System.out.print(randomGenerator.nextGaussian(97.79, 79.80) + " ");
		System.out.println();*/
	}
}