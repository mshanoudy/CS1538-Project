import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * SimulationEngine is responsible for running the basic QuickZone simulation
 */
public class SimulationEngine
{
    private final int HOUR_OFFSET = 3600;

    DistributionGenerator distributionGenerator; // Used for storing and utilizing statistical functions

    /**
     * Default Constructor
     */
    public SimulationEngine()
    {
        distributionGenerator = new DistributionGenerator();
    }

    /**
     * Runs the basic QuickZone simulation
     *
     * @param totalHours The total number of hours this simulation is to run for
     */
    public void runSimulation(int totalHours)
    {
        ArrayList<Customer>  customers;     // List of customers as they arrive in system
        ArrayList<Customer>  checkoutList;  // List of customers as they leave the system
        ArrayDeque<Customer> mtgQueue;      // Processed queue from MTG
        ArrayDeque<Customer> qzQueue;       // Processed queue from QZ

        MTGServer mtgServer = new MTGServer();
        QZServer  qzServer  = new QZServer();
        CheckoutServer checkoutServer = new CheckoutServer();

        customers = createArrivalList(totalHours); // Populate customers list

        for (Customer customer : customers)
        {// Send customers to respective servers, MTG or QZ
            if (customer.getType().equals("MTG"))
                mtgServer.add(customer);
            else
                qzServer.add(customer);
        }

        mtgQueue = mtgServer.processQueue();
        qzQueue  = qzServer.processQueue();

        checkoutServer.add(mergeQueues(mtgQueue, qzQueue)); // Merge queues into a a single list

        checkoutList = checkoutServer.processList();

        saveToFile(checkoutList);
    }

    private void saveToFile(ArrayList<Customer> customers)
    {
        try
        {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "utf-8"));

            int index = 0;
            for (Customer customer : customers)
            {
                String type  = customer.getType();
                int ID       = customer.getID();
                int sysAT    = customer.getSystemArrivalTime();
                int items    = customer.getItemTotal();
                int select   = customer.getItemSelectTime();
                int QAT      = customer.getQueueArrivalTime();
                int QWT      = customer.getQueueWaitTime();
                int SAT      = customer.getServerArrivalTime();
                int SWT      = customer.getServiceWaitTime();
                int CQAT     = customer.getCheckoutQueueArrivalTime();
                int CQWT     = customer.getCheckoutQueueWaitTime();
                int CSAT     = customer.getCheckoutServerArrivalTime();
                int CSWT     = customer.getCheckoutServiceWaitTime();
                int sysTT    = customer.getSystemTotalTime();
                int sysET    = customer.getSystemExitTime();

                writer.write("[" + index + "]: " + ID + " " + type + " " + sysAT + " " + items + " " + select + " " +
                            QAT + " " + QWT + " " + SAT + " " + SWT + " " + CQAT + " " + CQWT + " " + CSAT + " " +
                            CSWT + " " + sysTT + " " + sysET + "\n");

                index++;
            }
            writer.close();
        }
        catch (IOException e)
        {
            System.out.println("** ERROR WRITING TO FILE **");
            e.printStackTrace();
        }
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
     * @param totalHours The total number of hours the simulation is to run for
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
                currentCustomer.setType(randomCustomerType());
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
