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
            items = random.nextInt(10) + 1;     // Between 1 and 11
            itemSelectionTime = calculateItemSelectionTime(items);
            QAT = customer.getSystemArrivalTime();
            QWT = 0;
            SAT = QAT + QWT;
            SWT = itemSelectionTime;
            QET = SAT + SWT;

            customer.setItemTotal(items);
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

    private int calculateItemSelectionTime(int items)
    {
        int total = 0;

        for (int x = 0; x < items; x++)
            total += random.nextInt(300) + 30; // 30sec to 5.5min per item

        return total;
    }
}
