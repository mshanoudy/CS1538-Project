import java.util.ArrayDeque;

public class MTGServer extends ServerQueue
{
    public ArrayDeque<Customer> processQueue(int numberOfLines)
    {
        int QAT;    // Queue arrival time
        int lineID; // Which server customer chose
        int QWT;    // Queue wait time
        int SAT;    // Server arrival time
        int SWT;    // Service wait time
        int QET;    // Queue exit time

        int itemSelectionTime;
        int previousQET[] = initializeArray(numberOfLines, -1);
        int items = 1;

        ArrayDeque<Customer> queue = new ArrayDeque<>();
        for (Customer customer : customers)
        {
            itemSelectionTime = randomGenerator.nextInt(120) + 30;
            QAT = customer.getSystemArrivalTime();
            lineID = checkQueueWait(previousQET);   // Customer checks to see which line is shorter
            QWT = previousQET[lineID] - QAT;
            if (QWT < 0) QWT = 0;
            SAT = QAT + QWT;
            SWT = 30;                               // Constant 1min service time
            QET = SAT + SWT;
            previousQET[lineID] = QET;

            customer.setItemTotal(items);
            customer.setItemSelectTime(itemSelectionTime);
            customer.setQueueArrivalTime(QAT);
            customer.setQueueLineID(lineID);
            customer.setQueueWaitTime(QWT);
            customer.setServerArrivalTime(SAT);
            customer.setServiceWaitTime(SWT);
            customer.setCheckoutQueueArrivalTime(QET);

            queue.add(customer);
        }

        return queue;
    }
}
