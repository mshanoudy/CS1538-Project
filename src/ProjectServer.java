import java.util.ArrayDeque;
import java.util.ArrayList;

public class ProjectServer
{
    private String serverType;
    private ArrayList<Customer> customerList;

    public ProjectServer(String serverType, ArrayList<Customer> customerList)
    {
        this.serverType   = serverType;
        this.customerList = customerList;
    }

    public ArrayDeque<Customer> process()
    {// TODO: this method
        if (serverType.equals("MTG"))
            return processMTG();
        else if (serverType.equals("QZ"))
            return processQZ();
        else
            return processCheckout();
    }

    private ArrayDeque<Customer> processMTG()
    {
        int previousExitTime = -1;
        ArrayDeque<Customer> MTGQueue = new ArrayDeque<>();

        for (Customer customer : customerList)
        {
            customer.setMTGQueueTotalWaitTime(previousExitTime - customer.getMTGQueueArrivalTime()); // QWT
            customer.setMTGServiceArrivalTime(customer.getMTGQueueArrivalTime() + customer.getMTGQueueTotalWaitTime());
            customer.setMTGServiceTotalWaitTime(5); // TODO this should be random distribution
            customer.setCheckoutQueueArrivalTime(customer.getMTGQueueArrivalTime() + customer.getMTGQueueTotalWaitTime() + customer.getMTGServiceTotalWaitTime()); // QET
            previousExitTime = customer.getCheckoutQueueArrivalTime();
            MTGQueue.add(customer);
        }

        return MTGQueue;
    }

    private ArrayDeque<Customer> processQZ()
    {

    }

    private ArrayDeque<Customer> processCheckout()
    {

    }
}
