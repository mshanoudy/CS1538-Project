
public class Customer implements Comparable<Customer>
{
    private int ID;
    private String customerType;
    private int systemArrivalTime;
    private int itemTotal;
    private int itemSelectTime;
    private int queueArrivalTime;
    private int queueWaitTime;
    private int serverArrivalTime;
    private int serviceWaitTime;
    private int checkoutQueueArrivalTime;
    private int checkoutQueueWaitTime;
    private int checkoutServerArrivalTime;
    private int checkoutServiceWaitTime;
    private int systemTotalTime;
    private int systemExitTime;

    public Customer(int ID)
    {
        this.ID = ID;
    }

    public Customer(int ID, String customerType, int systemArrivalTime)
    {
        this.ID = ID;
        this.customerType = customerType;
        this.systemArrivalTime = systemArrivalTime;
    }

    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public String getCustomerType()
    {
        return customerType;
    }

    public void setCustomerType(String customerType)
    {
        this.customerType = customerType;
    }

    public int getSystemArrivalTime()
    {
        return systemArrivalTime;
    }

    public void setSystemArrivalTime(int systemArrivalTime)
    {
        this.systemArrivalTime = systemArrivalTime;
    }

    public int getItemTotal()
    {
        return itemTotal;
    }

    public void setItemTotal(int itemTotal)
    {
        this.itemTotal = itemTotal;
    }

    public int getItemSelectTime()
    {
        return itemSelectTime;
    }

    public void setItemSelectTime(int itemSelectTime)
    {
        this.itemSelectTime = itemSelectTime;
    }

    public int getQueueArrivalTime()
    {
        return queueArrivalTime;
    }

    public void setQueueArrivalTime(int queueArrivalTime)
    {
        this.queueArrivalTime = queueArrivalTime;
    }

    public int getQueueWaitTime()
    {
        return queueWaitTime;
    }

    public void setQueueWaitTime(int queueWaitTime)
    {
        this.queueWaitTime = queueWaitTime;
    }

    public int getServerArrivalTime()
    {
        return serverArrivalTime;
    }

    public void setServerArrivalTime(int serverArrivalTime)
    {
        this.serverArrivalTime = serverArrivalTime;
    }

    public int getServiceWaitTime()
    {
        return serviceWaitTime;
    }

    public void setServiceWaitTime(int serviceWaitTime)
    {
        this.serviceWaitTime = serviceWaitTime;
    }

    public int getCheckoutQueueArrivalTime()
    {
        return checkoutQueueArrivalTime;
    }

    public void setCheckoutQueueArrivalTime(int checkoutQueueArrivalTime)
    {
        this.checkoutQueueArrivalTime = checkoutQueueArrivalTime;
    }

    public int getCheckoutQueueWaitTime()
    {
        return checkoutQueueWaitTime;
    }

    public void setCheckoutQueueWaitTime(int checkoutQueueWaitTime)
    {
        this.checkoutQueueWaitTime = checkoutQueueWaitTime;
    }

    public int getCheckoutServerArrivalTime()
    {
        return checkoutServerArrivalTime;
    }

    public void setCheckoutServerArrivalTime(int checkoutServerArrivalTime)
    {
        this.checkoutServerArrivalTime = checkoutServerArrivalTime;
    }

    public int getCheckoutServiceWaitTime()
    {
        return checkoutServiceWaitTime;
    }

    public void setCheckoutServiceWaitTime(int checkoutServiceWaitTime)
    {
        this.checkoutServiceWaitTime = checkoutServiceWaitTime;
    }

    public int getSystemTotalTime()
    {
        return systemTotalTime;
    }

    public void setSystemTotalTime(int systemTotalTime)
    {
        this.systemTotalTime = systemTotalTime;
    }

    public int getSystemExitTime()
    {
        return systemExitTime;
    }

    public void setSystemExitTime(int systemExitTime)
    {
        this.systemExitTime = systemExitTime;
    }

    @Override
    public int compareTo(Customer thatCustomer)
    {
        return this.getCheckoutQueueArrivalTime() - thatCustomer.getCheckoutQueueArrivalTime();
    }
}