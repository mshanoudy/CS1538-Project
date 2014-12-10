import java.util.ArrayList;
import java.util.Random;

public class ServerQueue
{
    protected Random random;
    protected ArrayList<Customer> customers;
    protected int numberOfServers;

    public ServerQueue()
    {
        random    = new Random();
        customers = new ArrayList<Customer>();
        numberOfServers = 1;
    }

    public ServerQueue(int numberOfServers)
    {
        random    = new Random();
        customers = new ArrayList<Customer>();
        this.numberOfServers = numberOfServers;
    }

    public void add(Customer customer)
    {
        customers.add(customer);
    }

    public void add(ArrayList<Customer> customers)
    {
        this.customers = customers;
    }
}
