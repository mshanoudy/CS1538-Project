import java.util.ArrayDeque;
import java.util.Collections;

public class QZServer extends ServerQueue
{
    public ArrayDeque<Customer> processQueue()
    {
        int QAT;    // Queue arrival time
        int QWT;    // Queue wait time
        int SAT;    // Server arrival time
        int SWT;    // Service wait time
        int QET;    // Queue exit time

        int itemSelectionTime;
        int items;

        for (Customer customer : customers)
        {
            itemSelectionTime = 60; // TODO: Change hardcoded value of 60sec to a random selection time
            items = 5;              // TODO: Change hardcoded value of 5 items to a random value
            QAT = customer.getSystemArrivalTime();
            QWT = 0;
            SAT = QAT + QWT;
            SWT = itemSelectionTime * items;
            QET = SAT + SWT;

            customer.setItemTotal(items);
            customer.setItemSelectTime(itemSelectionTime);
            customer.setQueueArrivalTime(QAT);
            customer.setQueueWaitTime(QWT);
            customer.setServerArrivalTime(SAT);
            customer.setServiceWaitTime(SWT);
            customer.setCheckoutQueueArrivalTime(QET);
        }
        Collections.sort(customers);

        return new ArrayDeque<>(customers);
    }
}
