import java.util.ArrayList;

public class CheckoutServer extends ServerQueue
{
    private final double MEAN    = 12.02;
    private final double STD_DEV = 5.69;

    public ArrayList<Customer> processList(int numberOfLines)
    {
        int CQAT;       // Checkout queue arrival time
        int lineID;     // Which cashier customer chose
        int CQWT;       // Checkout queue wait time
        int CSAT;       // Checkout server arrival time
        int CSWT;       // Checkout service wait time
        int CQET;       // Checkout queue exit time
        int totalTime;  // Total time spent in system

        int previousCQET[] = initializeArray(numberOfLines, -1);

        ArrayList<Customer> list = new ArrayList<>();
        for (Customer customer : customers)
        {
            CQAT = customer.getCheckoutQueueArrivalTime();      // Customer arrives to checkout area
            lineID = checkQueueWait(previousCQET);              // Customer checks to see which line is shorter
            CQWT = previousCQET[lineID] - CQAT;                 // Calculate wait time
            if (CQWT < 0) CQWT = 0;                             // If line is empty, set wait time to 0
            CSAT = CQAT + CQWT;                                 // Calculate cashier arrival time
            CSWT = randomGenerator.nextGaussian(MEAN, STD_DEV); // Service time is normally distributed
            CQET = CSAT + CSWT;                                 // Time customer finished being checked out
            totalTime = CQET - customer.getSystemArrivalTime(); // Calculate total time in system
            previousCQET[lineID] = CQET;                        // Set the exit time for next person

            customer.setCheckoutQueueLineID(lineID);
            customer.setCheckoutQueueWaitTime(CQWT);
            customer.setCheckoutServerArrivalTime(CSAT);
            customer.setCheckoutServiceWaitTime(CSWT);
            customer.setSystemExitTime(CQET);
            customer.setSystemTotalTime(totalTime);

            list.add(customer);
        }

        return list;
    }
}
