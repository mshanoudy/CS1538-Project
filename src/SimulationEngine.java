import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * SimulationEngine is responsible for running the basic QuickZone simulation
 */
public class SimulationEngine
{
    private final int SECONDS_IN_HOUR = 3600;
    private final long SEED = 1421897149542322L;
    private final double ARRIVAL_RATE = 3.52;    // per min

    private RandomGenerator randomGenerator;

    /**
     * Default Constructor
     */
    public SimulationEngine()
    {
        randomGenerator = new RandomGenerator(SEED);
    }

    /**
     * Runs the basic QuickZone simulation
     *
     * @param totalHours The total number of hours this simulation is to run for
     */
    public void runSimulation(int totalHours, int mtgServers, int checkoutServers)
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

        mtgQueue = mtgServer.processQueue(mtgServers);
        qzQueue  = qzServer.processQueue();

        checkoutServer.add(mergeQueues(mtgQueue, qzQueue)); // Merge queues into a a single list

        checkoutList = checkoutServer.processList(checkoutServers);

        saveToFile(checkoutList);
    }


    /**
     * Saves output to a file
     *
     * @param customers The list of customers to print
     */
    public void saveToFile(ArrayList<Customer> customers)
    {
        try
        {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "utf-8"));


            writer.write(String.format("%-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s\n\n",
                             "INDEX", "TYPE", "ID", "SYS AT", "# ITEMS", "SELECT T", "QAT", "QWT", "SAT", "SWT", "CQAT",
                             "CQWT", "CSAT", "CSWT", "SYS TT", "SYS ET"));

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

                writer.write(String.format("%-8d %-8s %-8d %-8d %-8d %-8d %-8d %-8d %-8d %-8d %-8d %-8d %-8d %-8d %-8d %-8d\n",
                                 index, type, ID, sysAT, items, select, QAT, QWT, SAT, SWT, CQAT, CQWT, CSAT, CSWT, sysTT, sysET));

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
        int customersThisHour = 0;
        int arrivalsThisHour[];
        Customer currentCustomer;

        ArrayList<Customer> customerArrayList = new ArrayList<>();

        for (int hour = 0; hour < totalHours; hour++)
        {
            for (int minute = 0; minute < 60; minute++)
                customersThisHour += randomGenerator.nextPoisson(ARRIVAL_RATE);

            arrivalsThisHour  = randomGenerator.poissonProcess(3600, customersThisHour);

            for (int index = 0; index < customersThisHour; index++)
            {
                currentCustomer = new Customer(customerID);

                // Is MTG open?
                if (hour >= 2 && hour <= 9)
                    currentCustomer.setType(getRandomCustomerType());
                else
                    currentCustomer.setType("QZ");

                currentCustomer.setSystemArrivalTime((hour * SECONDS_IN_HOUR) + arrivalsThisHour[index]);
                customerArrayList.add(currentCustomer);
                customerID++;
            }

            customersThisHour = 0;
        }

        return customerArrayList;
    }

    /**
     * Picks a random customer type based on the p value
     *
     * @return The type of customer
     */
    private String getRandomCustomerType()
    {
        if (randomGenerator.nextDouble() > 0.69)
            return "MTG";
        else
            return "QZ";
    }

    public static void main(String args[])
    {
        SimulationEngine simulationEngine = new SimulationEngine();
        simulationEngine.runSimulation(1,1,2);
    }
}
