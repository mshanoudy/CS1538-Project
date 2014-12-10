import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

public class MTGServer extends ServerQueue
{
    public MTGServer(int numberOfServers)
    {
        random    = new Random();
        customers = new ArrayList<Customer>();
        this.numberOfServers = numberOfServers;
    }
    public ArrayDeque<Customer> processQueue()
    {
        int QAT;    // Queue arrival time
        int QWT;    // Queue wait time
        int SAT;    // Server arrival time
        int SWT;    // Service wait time
        int QET;    // Queue exit time

        int itemSelectionTime;
        int[] previousQET = new int[numberOfServers];
        for(int i = 0; i < numberOfServers; i++)
        {
            previousQET[i] = -1;
        }

        int items = 1;

        ArrayDeque<Customer> queue = new ArrayDeque<>();
        for (Customer customer : customers)
        {
            itemSelectionTime = random.nextInt(300) + 60;   // 1 to 6 min
            QAT = customer.getSystemArrivalTime();
            //determine which server will finish next, and update that server and the new customer.
            int serverID = 0;
            int earliestPreviousQET = previousQET[0];
            for(int i = 1; i < numberOfServers; i++)
            {
                if(previousQET[i] < earliestPreviousQET)
                {
                    earliestPreviousQET = previousQET[i];
                    serverID = i;
                }
            }
            QWT = previousQET[serverID] - QAT;
            if (QWT < 0) QWT = 0;
            SAT = QAT + QWT;
            SWT = items * random.nextInt(180) + 60;         // 1 to 4 min
            QET = SAT + SWT;
            previousQET[serverID] = QET;

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
