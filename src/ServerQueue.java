import java.util.ArrayList;

public class ServerQueue
{
    protected ArrayList<Customer> customers;

    public ServerQueue()
    {
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
