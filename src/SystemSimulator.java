import java.util.ArrayList;
import java.util.Random;

public class SystemSimulator
{
    private ArrayList<Customer> customerList;   // List of customers in the system
    private ArrayList<Customer> MTGList;
    private ArrayList<Customer> QZList;

    private int totalHours;

    public SystemSimulator(int totalRunTime)
    {
        this.customerList = new ArrayList<>();
        this.totalHours   = totalRunTime * 3600;
        this.MTGList    = new ArrayList<>();
        this.QZList     = new ArrayList<>();
    }

    public void runSimulation()
    {
        generateCustomerArrivalTimes();
        seperateList();

    }

    private void processMTG()
    {
        ProjectServer MTGServer = new ProjectServer("MTG", MTGList);


    }

    private void seperateList()
    {// TODO: make case for BOTH
        for (Customer customer : customerList)
        {
            if (customer.getCustomerType().equals("MTG"))
            {// TODO: Make this random not hardcoded
                customer.setItemTotal(1);
                customer.setItemSelectTime(60);
                customer.setMTGQueueArrivalTime(customer.getSystemArrivalTime());
                MTGList.add(customer);
            }
            else
            {// TODO: Make this random not hardcoded
                customer.setItemTotal(5);
                customer.setItemSelectTime(5 * 60);
                customer.setCheckoutQueueArrivalTime(customer.getSystemArrivalTime() + customer.getItemSelectTime());
                QZList.add(customer);
            }
        }
    }

    private void generateCustomerArrivalTimes()
    {
        int customerID = 0;

        for (int x = 0; x < totalHours; x++)
        {
            int numberOfCustomersThisHour = hourlyArrivalRate();
            int arrivalTimes[] = generateArrivalsTimesWithinHour(numberOfCustomersThisHour);

            for (int y = 0; y < numberOfCustomersThisHour; y++)
            {
                customerList.add(new Customer(customerID, generateCostumerType(), x * 3600 + arrivalTimes[y]));
                customerID++;
            }
        }
    }

    private int hourlyArrivalRate()
    {// TODO: Figure this out and vary by certain times of day
        return 50;
    }

    private String generateCostumerType()
    {// TODO: Add the BOTH case
        Random random = new Random();
        if (random.nextDouble() > 0.7)
            return "MTG";
        else
            return "QZ";
    }

    private int[] generateArrivalsTimesWithinHour(int numberOfArrivals)
    {// TODO: Make this an actual distribution
        int gap = 3600 / numberOfArrivals;
        int arrivalTimes[] = new int[numberOfArrivals];
        for (int x = 0; x < numberOfArrivals; x++)
            arrivalTimes[x] = x * gap;
        return arrivalTimes;
    }
}
