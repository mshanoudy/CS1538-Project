import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class ProjectServer
{// TODO: Handle multiple servers
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
            if (previousExitTime - customer.getMTGQueueArrivalTime() <= 0)
                customer.setMTGQueueTotalWaitTime(0);
            else
                customer.setMTGQueueTotalWaitTime(previousExitTime - customer.getMTGQueueArrivalTime()); // QWT
            customer.setMTGServiceArrivalTime(customer.getMTGQueueArrivalTime() + customer.getMTGQueueTotalWaitTime());
            customer.setMTGServiceTotalWaitTime(5); // TODO this should be random distribution
            customer.setCheckoutQueueArrivalTime(customer.getMTGServiceArrivalTime() + customer.getMTGServiceTotalWaitTime()); // QET
            previousExitTime = customer.getCheckoutQueueArrivalTime();
            MTGQueue.add(customer);
        }

        return MTGQueue;
    }

    private ArrayDeque<Customer> processQZ()
    {
        for (Customer customer : customerList)
        {// TODO: Make these random values
            customer.setItemTotal(5);
            customer.setItemSelectTime(5 * 60);
            customer.setCheckoutQueueArrivalTime(customer.getSystemArrivalTime() + customer.getItemSelectTime());
        }

        Collections.sort(customerList);
        return new ArrayDeque<>(customerList);
    }

    private ArrayDeque<Customer> processCheckout()
    {// TODO: change this so it returns a list
        int previousExitTime = -1;

        for (Customer customer : customerList)
        {
            if (previousExitTime - customer.getCheckoutQueueArrivalTime() <= 0)
                customer.setCheckoutQueueTotalWaitTime(0);
            else
                customer.setCheckoutQueueTotalWaitTime(previousExitTime - customer.getCheckoutQueueArrivalTime());
            customer.setCheckoutServiceArrivalTime(customer.getCheckoutQueueArrivalTime() + customer.getCheckoutQueueTotalWaitTime());
            customer.setCheckoutServiceTotalWaitTime(customer.getItemTotal() * 30); // TODO: change 30 to a random number
            customer.setSystemExitTime(customer.getCheckoutServiceArrivalTime() + customer.getCheckoutServiceTotalWaitTime());
            customer.setSystemTotalTime(customer.getSystemExitTime() - customer.getSystemArrivalTime());
            previousExitTime = customer.getSystemExitTime();
        }

        return new ArrayDeque<>(customerList);
    }
}
