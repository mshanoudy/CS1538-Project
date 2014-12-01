import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class SimulationEngine
{
    private final int HOUR_OFFSET = 3600;

    DistributionGenerator distributionGenerator;

    public SimulationEngine()
    {
        distributionGenerator = new DistributionGenerator();
    }

    public void runSimulation(int totalHours)
    {
        MTGServer mtgServer = new MTGServer();
        QZServer  qzServer  = new QZServer();
        CheckoutServer checkoutServer = new CheckoutServer();

        // Generate customer arrival list
        ArrayList<Customer> customers = createArrivalList(totalHours);

        // Separate customers by type
        for (Customer customer : customers)
        {
            if (customer.getCustomerType().equals("MTG"))
                mtgServer.add(customer);
            else
                qzServer.add(customer);
        }

        // Process the queues
        ArrayDeque<Customer> mtgQueue = mtgServer.processQueue();
        ArrayDeque<Customer> qzQueue  = qzServer.processQueue();

        // Merge queues into a list and process checkout list
        checkoutServer.add(mergeQueues(mtgQueue, qzQueue));
        ArrayList<Customer> checkoutList = checkoutServer.processList();
    }

    /**
     * Merges two queues together into a single list ordered by checkout queue arrival time
     *
     * @param mtgQueue The MTG Queue
     * @param qzQueue The QZ Queue
     *
     * @return A single list ordered by checkout queue arrival time
     */
    private ArrayList<Customer> mergeQueues(ArrayDeque<Customer> mtgQueue, ArrayDeque<Customer> qzQueue)
    {
        ArrayList<Customer> list = new ArrayList<>();

        // While at least one queue is not empty
        while (!mtgQueue.isEmpty() || !qzQueue.isEmpty())
        {
            // Both queues are populated
            if (!mtgQueue.isEmpty() && !qzQueue.isEmpty())
            {
                // MTG arrives first
                if (mtgQueue.peek().getCheckoutQueueArrivalTime() < qzQueue.peek().getCheckoutQueueArrivalTime())
                    list.add(mtgQueue.removeFirst());
                // QZ arrives first
                else
                    list.add(qzQueue.removeFirst());
            }
            // QZ is empty
            else if (!mtgQueue.isEmpty() && qzQueue.isEmpty())
                list.add(mtgQueue.removeFirst());
            // MTG is empty
            else
                list.add(qzQueue.removeFirst());
        }

        return list;
    }

    /**
     * Creates the customer list in chronological order based on arrival times for some number of hours
     *
     * @param totalHours The total number of hours the simulation is run for
     *
     * @return An ArrayList containing the list of customers by arrival time for some number of hours
     */
    private ArrayList<Customer> createArrivalList(int totalHours)
    {
        int customerID = 0;
        int customersThisHour;      // Number of customers this hour
        int arrivalTimesThisHour[]; // Array of arrival times for each customer relative to a certain hour
        Customer currentCustomer;   // The current customer

        ArrayList<Customer> customerArrayList = new ArrayList<>();

        for (int hour = 0; hour < totalHours; hour++)
        {
            customersThisHour = 50; // TODO: Replace hardcoded value of 50 with a distributed value from DistributionGenerator
            arrivalTimesThisHour = createArrivalTimesThisHour(customersThisHour);

            for (int index = 0; index < customersThisHour; index++)
            {
                currentCustomer = new Customer(customerID);
                currentCustomer.setCustomerType(randomCustomerType());
                currentCustomer.setSystemArrivalTime((hour * HOUR_OFFSET) + arrivalTimesThisHour[index]);
                customerArrayList.add(currentCustomer);
                customerID++;
            }
        }

        return customerArrayList;
    }

    /**
     * Creates an array of arrival times relative to an hour
     * Assumes the starting time of this hour is 0
     *
     * @param customersThisHour The number of customers within this hour
     *
     * @return Array of arrival times relative to an hour
     */
    private int[] createArrivalTimesThisHour(int customersThisHour)
    {
        int gap = 3600 / customersThisHour;
        int arrivalTimes[] = new int[customersThisHour];

        for (int x = 0; x < customersThisHour; x++)
            arrivalTimes[x] = x * gap;  // TODO: Replace with a random value for each individual gap
        Arrays.sort(arrivalTimes);

        return arrivalTimes;
    }

    /**
     * Picks a random customer type based on the p value
     *
     * @return The type of customer
     */
    private String randomCustomerType()
    {
        if (distributionGenerator.generateNextUniform() > 0.7) // TODO: Change this p value to something more accurate
            return "MTG";
        else
            return "QZ";
    }

    public static void main(String args[])
    {
        SimulationEngine simulationEngine = new SimulationEngine();
        simulationEngine.runSimulation(1);
    }
}
