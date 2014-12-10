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

    protected int[] initializeArray(int size, int value)
    {
        int array[] = new int[size];

        for (int index = 0; index < array.length; index++)
            array[index] = value;

        return array;
    }

    protected int checkQueueWait(int[] previous)
    {
        int shortest = 0;

        for (int index = 0; index < previous.length; index++)
            if (previous[index] < previous[shortest])
                shortest = index;

        return shortest;
    }
}
