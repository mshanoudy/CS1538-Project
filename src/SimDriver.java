
public class SimDriver
{
	private static final int QZ_OPERATING_HOURS = 13;

	public static void main(String args[])
	{
		SimulationEngine simulationEngine = new SimulationEngine();
		//simulationEngine.runSimulation(1, 1, 2);
		simulationEngine.runSimulation(QZ_OPERATING_HOURS, 1, 4);
	}
}