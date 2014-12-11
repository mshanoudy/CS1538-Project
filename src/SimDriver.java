import java.io.*;

public class SimDriver
{
	public static void main(String args[]) throws IOException
	{
		if((args.length < 3))
		{
			throw new IOException("User input error. Please use Proper command Line Arguments(See readme file)");
		}
		
		int operatingHours = Integer.parseInt(args[0]);
		
		if( operatingHours < 1)
		{
			throw new IOException("User input error. Please use time greater than or equal to one hour(See readme file)");
		}
		
		//num of checkout cashiers(servers)
		int numOfCS = Integer.parseInt(args[1]);
		
		//Num of mtg servers
		int numOfMTGServer = Integer.parseInt(args[2]);
		
		if(numOfCS < 1 | numOfMTGServer < 1)
		{
			throw new IOException("User input error. One of the server specs is invalid(See readme file)");
		}

		SimulationEngine simulationEngine = new SimulationEngine();
		simulationEngine.runSimulation(operatingHours, numOfMTGServer, numOfCS);
	}
}