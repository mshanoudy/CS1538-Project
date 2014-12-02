import java.util.ArrayList;
import java.util.Random;

public class ServerQueue
{
    protected Random random;
    protected ArrayList<Customer> customers;


    public ServerQueue()
    {
        random    = new Random();
        customers = new ArrayList<>();
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
