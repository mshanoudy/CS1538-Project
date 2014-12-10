import java.io.*;
import java.util.*;

public class DistGenDriver 
{
	public static void main(String[] args) throws IOException
	{
		DistGen testGen = new DistGen();
		DistGen testGen2 = new DistGen();
		double test2;
		
		
		///////////////////////////////////////////////////////////////
		// ArrayList<Integer> allArrivalTimes = new ArrayList<Integer>();
		
		// int hours = 1;
		
		// int totalMins = hours*60;
		
		// int[] test = new int[0];
		
		// boolean done = false; 
		
		// for(int x = 0; x < totalMins; x++)
		// {
			// test = testGen.genPoissonProc2(3.52, 60.0);
			
			// for(int i = 0; i < test.length; i++)
			// {
				// allArrivalTimes.add(test[i]+ (x*60));	
					
			// }
		// }
		
		// for(int i = 0; i < allArrivalTimes.size(); i++)
		// {
			// System.out.println(allArrivalTimes.get(i));
		// }
		
		
		//////////////////////////////////////////////////////////////
		
		////// Poisson processs arrival times lambda 3.52 per min
		//// Normal item selecet dist mean = 97.78 secs std = 79.8 secs
		/// Check out server times mean 12.02 std = 5.69 secs
		
		///Poisson
		PrintWriter writer = new PrintWriter("PossionDistOut.txt", "UTF-8");
		
		
		ArrayList<Integer> allArrivalTimes = new ArrayList<Integer>();
		
		int hours = 1;
		
		int totalMins = hours*60;
		
		int[] test = new int[0];
		
		boolean done = false; 
		
		for(int x = 0; x < totalMins; x++)
		{
			test = testGen.genPoissonProc2(3.52, 60.0);
			
			for(int i = 0; i < test.length; i++)
			{
				allArrivalTimes.add(test[i]+ (x*60));	
					
			}
		}
		
		for(int i = 0; i < allArrivalTimes.size(); i++)
		{
			writer.println(allArrivalTimes.get(i));
		}
		
		writer.close();
		////////////////
		
		
		////// Item selection
		PrintWriter writer2 = new PrintWriter("NormalItemSelect.txt", "UTF-8");
		
		int runCount = 1000;
		
		for(int x = 0; x < runCount; x++)
		{
			writer2.println(testGen.genNormal( 97.78, 79.8));
		}
		writer2.close();
		///////
		
		
		///Check out selection 
		PrintWriter writer3 = new PrintWriter("NormalCheckOut.txt", "UTF-8");
		
		for(int x = 0; x < runCount; x++)
		{
			writer3.println(testGen.genNormal( 12.02, 5.69));
		}
		
		writer3.close();
		////
		
		
		
		
		
		
		//test = (int)testGen.genExp(3.0, false, 100.0);
		//test = testGen.genNormal(10,3);
		//test = testGen.genPoissonProc2(3.52, 60.0);
		//System.out.println(test);
		
		
		// ArrayList<Double> arrivalTimes = new ArrayList<Double>();
		
		// ArrayList<Double> arrivalTimes2 = new ArrayList<Double>();
		
		// arrivalTimes2 = testGen.genPoissonProc(30.00, 3600.0);
		
		
		
		// arrivalTimes = testGen2.genPoissonProc(30.00, 3600.0);
		
		// //Generates arrival queue
		// for(int x = 0;	x < arrivalTimes.size(); x++)
		// {	
		
			// System.out.println(x + "   " + arrivalTimes.get(x));
			
		// }
		
		
		//System.out.println(testGen2.genBern(.7));
		
		
		// int[] test2 = testGen.genPoissonProc(30, 3600);
			// //Generates arrival queue
		// for(int x = 0;	x < test2.length; x++)
		// {	

			// System.out.println(x + "   " + test2[x]);
			
		// }
		
		
	
	}
}