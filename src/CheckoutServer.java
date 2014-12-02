import java.util.ArrayList;

public class CheckoutServer extends ServerQueue
{
    public ArrayList<Customer> processList()
    {
        int previousCQET = -1;

        int CQAT;       // Checkout queue arrival time
        int CQWT;       // Checkout queue wait time
        int CSAT;       // Checkout server arrival time
        int CSWT;       // Checkout service wait time
        int CQET;       // Checkout queue exit time
        int totalTime;  // Total time spent in system

        ArrayList<Customer> list = new ArrayList<>();
        for (Customer customer : customers)
        {
            CQAT = customer.getCheckoutQueueArrivalTime();
            CQWT = previousCQET - CQAT;
            if (CQWT < 0) CQWT = 0;
            CSAT = CQAT + CQWT;
            CSWT = customer.getItemTotal() * 30; // TODO Change hardcoded value of 30sec to a random processing time
            CQET = CSAT + CSWT;
            totalTime = CQET - customer.getSystemArrivalTime();
            previousCQET = CQET;

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
