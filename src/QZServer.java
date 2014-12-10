import java.util.ArrayDeque;
import java.util.Collections;

public class QZServer extends ServerQueue
{
    private final double MEAN    = 97.79;
    private final double STD_DEV = 79.80;

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
            itemSelectionTime = randomGenerator.nextGaussian(MEAN, STD_DEV); // Selection time is normally distributed
            QAT = customer.getSystemArrivalTime();
            QWT = 0;
            SAT = QAT + QWT;
            SWT = itemSelectionTime;
            QET = SAT + SWT;

            customer.setItemSelectTime(itemSelectionTime);
            customer.setQueueArrivalTime(QAT);
            customer.setQueueLineID(0);
            customer.setQueueWaitTime(QWT);
            customer.setServerArrivalTime(SAT);
            customer.setServiceWaitTime(SWT);
            customer.setCheckoutQueueArrivalTime(QET);
        }
        Collections.sort(customers);

        return new ArrayDeque<>(customers);
    }
}
