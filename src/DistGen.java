import java.io.*;
import java.util.Random;
import java.util.*;

public class DistGen
{
	Random rg;
	
	/**
     * Default Constructor
     */
	public DistGen()
	{
		rg = new Random();
	}
	
	public DistGen(long seed)
	{
		rg = new Random(seed);
	
	}
	
	/**
     * Generates random sorted arrival times from the possion process dist using exponential interarrival times in seconds
     * The time is in seconds
     *
     * @param arrivalRate the arrival used to gen the random numbers
     * @param T the total time to gen times for(use seconds 3600 for hour)
	 *
     * @return a sorted int[]
     */
	public int[] genPoissonProc( int arrivalRate, int T)
	{
		int t = 0;
		int x = 0;
		
		int[] times = new int[T];
		
		while ( t < T)
		{
			t += (int)genExp(arrivalRate,false,0.0);		
			
			if(t <= T )
			{
				times[x] = t;
				x++;
			}
		
		}
		
		times = Arrays.copyOf(times,x);
		
		return times;
	}
	
	/**
     * Generates random numbers from the exponential dist
     *
     * @param lambda the lamba/arrival rate to use in the inverse transform method
     * @param unitFlag true = R with decimals, false = R scaled to seconds from uniform
	 * @param limiter for use when needed unit is seconds, will cause output of a second time value from [0,limiter)--use 0.0 for no range
	 *
     * @return a single double from the exponential dist(if secs needed cast int)
     */
	public double genExp( double lambda, boolean unitFlag, double limiter)
	{
		boolean flag = false;				//Flag for generating a ran number in a range
		double R;
		double RSec;
				
		//Generate a ran num useing inverse transform
		//If a random number in unit of seconds needs to be generated in a range
		//it will continue generating a random time unit untill it is in the range
		while(true)
		{
			R = rg.nextDouble();
			
			R = 1.0 - R;
			
			R = Math.log(R) *-1.0;
			R = R/lambda;
			
			if(unitFlag == true)
				break;
			
			R = R*60.0* 60.00;
			
			//Trim needless decimals
			R = (int)R;
			
			if(limiter == 0.0)
				break;
			
			if(R <= limiter)
				break;
		}
		
		return R;
	}

	/**
     * Generates random numbers from the normal dist wil the specified mean and std
     * @param mean values will cluster around this
     * @param stD the standard deviation
     * @return an int from the normal
     */
	public int genNormal( int mean, int stD)
	{

		
		int value = (int)((rg.nextGaussian()*stD) + mean);
		
		//Assumption: no 0 or negative items or time
		while (value <= 0)
		{
			value = (int)((rg.nextGaussian()*stD) + mean);
		}
		
		return value;
	}
	
	
	/**
     * Generates random numbers from the uniform dist to check against a p value
     * @param p the p value to check against
     * 
     * @return if(R <= p) true(qz), if R>p return false(mtg)
     */
	public boolean genBern(double p)
	{

		
		return rg.nextDouble() <= p;
	
	}
	
	/**
     * Generates arrival times following a poisson process using uniform interarrival times(USE THIS ONE!) in seconds
     * @param arrivalRate the lambda
	 * @param T The time period
     * 
     * @return arrival times in the specified time period
     */
	public int[] genPoissonProc2(double arrivalRate, double T)
	{
		//ArrayList<Integer> arrivalTimes =  new ArrayList<Integer>();
		
		double N = genPoissonDist(arrivalRate,T);
		
		int[] arrivalTimes = new int[(int)N];
		
		for(int i=0; i < N; i++)
		{		
			arrivalTimes[i] = (int)(rg.nextDouble()*T);	
		}
	
		Arrays.sort(arrivalTimes);
		
		return arrivalTimes;
	}
	
	/**
     * Generates random number of arrivals from the poisson dist
     * @param arrivalRate the lambda
	 * @param T The time period
     * 
     * @return the number of arrivals in that time period
     */
	public double genPoissonDist(double arrivalRate, double T)
	{
		
		double L = Math.exp(-1.0*arrivalRate);
		double K = 0.0;
		
		double p = 1.0;
		
		//System.out.println(L);
		
		do
		{
			K = K +1.0;

			p = p*rg.nextDouble();
			
			//System.out.println(p);
		
		}while( p > L);
	
		System.out.println( K-1.0);
	
		return K-1.0;
		
	}
	
	
	
	
	
}