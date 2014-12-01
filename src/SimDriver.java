//Steven Ross
//smr77@pitt.edu
//3638819

import java.io.*;
import java.util.*;

public class SimDriver
{
    /**
	public static void main(String[] args) 
	{
		// Customer one = new Customer(1.0);
		// one.setLocation("mtg");
		
		// Customer two = new Customer(2.0);
		// two.setLocation("qz");
		
		// System.out.println(one.getId()+ "  " + one.getLocation());
		// System.out.println(two.getId()+ "  " + two.getLocation());
		
		int numOfHours = Integer.parseInt(args[0]);
		
		double numOfMins = numOfHours * 60.0;
		
		//Create arrayList of Customers
		ArrayList<Double> arrivalTimes = new ArrayList<Double>();
		
		arrivalTimes = genPoissonProcess(30.00, numOfMins);
		
		ArrayList<Customer> arrivalCust = new ArrayList<Customer>();
		
		//Generates arrival queue
		for(int x = 0;	x < arrivalTimes.size(); x++)
		{
			arrivalCust.add(new Customer(arrivalTimes.get(x)));
			
			System.out.println(arrivalCust.get(x).getId() + "   " + arrivalCust.get(x).getArrivalTime());
		
		}
		
		
	}
	
	//Gens a num from the normal dist with mean and variance specified
	public static double genNormal( double mean, double variance)
	{
		Random rg = new Random();
		return round(rg.nextGaussian()*(Math.sqrt(variance)) + mean);
	}
	
		//Generate poisson arrivals for a full hour or time T.
	public static ArrayList<Double> genPoissonProcess( double arrivalRate, double T)
	{
		double t = 0.0;
		
		int count = 0;
		
		ArrayList<Double> times = new ArrayList<Double>();
		
		while ( t < T)
		{
			t += (genExpRand(arrivalRate)* 60.0);		//60 to scale
			
			if(t <= T )
			{
				//case if arrival times are the same
				if( times.size() >=1 & t == (times.size() -1))
				{
					t += .01;
				}
				times.add(round(t));
				count++;
			}
		
		}
		
		return times;
	
	}
	
	//Generating a random number using expentional and the inverse transform method
	public static double genExpRand( double lambda)
	{
		Random rg = new Random();
		double R = rg.nextDouble();
		
		R = 1.0 - R;
		
		R = Math.log(R) *-1.0;
		R = R/lambda;
		
		return R;
	}
	
	//Round double to 2 decimal points if nesc
	public static double round(double number)
	{
		number = Math.round(number * 100.0);
		number = number/100.0;
		
		return number;
	
	}
	*/
}