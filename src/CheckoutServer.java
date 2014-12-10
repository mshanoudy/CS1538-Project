import java.util.ArrayList;
import java.util.Random;

public class CheckoutServer extends ServerQueue
{
   public CheckoutServer(int numberOfServers)
    {
        random    = new Random();
        customers = new ArrayList<Customer>();
        this.numberOfServers = numberOfServers;
    }
    public ArrayList<Customer> processList()
    {
        //initialize the individual servers.
        CheckoutServerIndividual[] checkoutServers = new CheckoutServerIndividual[numberOfServers];
        for(int i = 0; i < this.numberOfServers; i++)
        {
            checkoutServers[i] = new CheckoutServerIndividual();
        }
        ArrayList<Customer> list = new ArrayList<Customer>();

        for (Customer customer : customers)
        {
            int serverID = 0; //ID of server with smallest line
            int smallestLineSize = checkoutServers[0].NumberInLine(customer.getCheckoutQueueArrivalTime());
            for(int i = 1; i < numberOfServers; i++)
            {
                int lineSize = checkoutServers[i].NumberInLine(customer.getCheckoutQueueArrivalTime());
                if(lineSize < smallestLineSize)
                {
                    smallestLineSize = lineSize;
                    serverID = i;
                    System.out.println("ID Updated, CID + CAT " + customer.getID() + " " + customer.getCheckoutQueueArrivalTime() + " ");
                }
            }
            checkoutServers[serverID].processCustomer(customer);
            list.add(customer);
        }
    //int numberInLine = 0
    //int index = thisCustomerIndex - 1
    //while customers[index].CQET > thisCustomer.CQAT {
        //index--; numberInLine++;
        return list;
    }
}
class CheckoutServerIndividual
{
    //optional: a function to write out line length data.

    ArrayList<Customer> listIndividual;
    int previousCQET;
    int CQAT;       // Checkout queue arrival time
    int CQWT;       // Checkout queue wait time
    int CSAT;       // Checkout server arrival time
    int CSWT;       // Checkout service wait time
    int CQET;       // Checkout queue exit time
    int totalTime;  // Total time spent in system
    CheckoutServerIndividual()
    {
        listIndividual = new ArrayList<Customer>();
        previousCQET = -1;
    }
    int NumberInLine(int arrivalTime) {
        int numberInLine = 0; //running total of number of customers in line
        int index = listIndividual.size() - 1; //index of next most recent customer
        int CQETCurr = arrivalTime + 1; //exit time of next most recent customer
        //increment numberInLine for each customer until we find one that already left.
        while(index >= 0)
        {
            Customer customer = listIndividual.get(index);
            CQETCurr = customer.getCheckoutServerArrivalTime() + customer.getCheckoutServiceWaitTime();
            if(CQETCurr > arrivalTime)
                numberInLine++;
            else
            {
                break;
            }
            index--;
        }
        return numberInLine;
    }
    //Acts as if a customer approached the line of this cashier, and
    // updates a customers statistics to reflect what is known.
    void processCustomer(Customer customer)
    {
        CQAT = customer.getCheckoutQueueArrivalTime();
        CQWT = previousCQET - CQAT;
        if (CQWT < 0) CQWT = 0;
        CSAT = CQAT + CQWT;
        CSWT = customer.getItemTotal() * 30; // TODO Change hardcoded value of 30sec to a random processing time
        CQET = CSAT + CSWT;
        totalTime = CQET - customer.getSystemArrivalTime();
        previousCQET = CQET;
        this.listIndividual.add(customer);
        customer.setCheckoutQueueWaitTime(CQWT);
        customer.setCheckoutServerArrivalTime(CSAT);
        customer.setCheckoutServiceWaitTime(CSWT);
        customer.setSystemExitTime(CQET);
        customer.setSystemTotalTime(totalTime);
    }
}
