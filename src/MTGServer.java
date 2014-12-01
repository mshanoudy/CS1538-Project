import java.util.ArrayDeque;

public class MTGServer extends ServerQueue
{
    public ArrayDeque<Customer> processQueue()
    {
        int QAT;    // Queue arrival time
        int QWT;    // Queue wait time
        int SAT;    // Server arrival time
        int SWT;    // Service wait time
        int QET;    // Queue exit time

        int itemSelectionTime;
        int previousQET = -1;
        int items = 1;

        ArrayDeque<Customer> queue = new ArrayDeque<>();
        for (Customer customer : customers)
        {
            itemSelectionTime = 60; // TODO: Change hardcoded value of 60sec to a random selection time
            QAT = customer.getSystemArrivalTime();
            QWT = previousQET - QAT;
            if (QWT < 0)
                QWT = 0;
            SAT = QAT + QWT;
            SWT = items * itemSelectionTime; // TODO: Change this to include how long it takes server to serve customer
            QET = SAT + SWT;
            previousQET = QET;

            customer.setItemTotal(items);
            customer.setItemSelectTime(itemSelectionTime);
            customer.setQueueArrivalTime(QAT);
            customer.setQueueWaitTime(QWT);
            customer.setServerArrivalTime(SAT);
            customer.setServiceWaitTime(SWT);
            customer.setCheckoutQueueArrivalTime(QET);

            queue.add(customer);
        }

        return queue;
    }
}
