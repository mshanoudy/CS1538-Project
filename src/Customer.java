

public class Customer
{
    private String ID;
    private String customerType;
    private int systemArrivalTime;
    private int itemTotal;
    private int itemSelectTime;
    private int MTGQueueArrivalTime;
    private int MTGQueueTotalWaitTime;
    private int MTGServiceArrivalTime;
    private int MTGServiceTotalWaitTime;
    private int checkoutQueueArrivalTime;
    private int checkoutQueueTotalWaitTime;
    private int checkoutServiceArrivalTime;
    private int checkoutServiceTotalWaitTime;
    private int systemTotalWaitTime;
    private int systemExitTime;

    public Customer()
    {

    }

    public String getID()
    {
        return ID;
    }

    public void setID(String ID)
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

    public int getMTGQueueArrivalTime()
    {
        return MTGQueueArrivalTime;
    }

    public void setMTGQueueArrivalTime(int MTGQueueArrivalTime)
    {
        this.MTGQueueArrivalTime = MTGQueueArrivalTime;
    }

    public int getMTGQueueTotalWaitTime()
    {
        return MTGQueueTotalWaitTime;
    }

    public void setMTGQueueTotalWaitTime(int MTGQueueTotalWaitTime)
    {
        this.MTGQueueTotalWaitTime = MTGQueueTotalWaitTime;
    }

    public int getMTGServiceArrivalTime()
    {
        return MTGServiceArrivalTime;
    }

    public void setMTGServiceArrivalTime(int MTGServiceArrivalTime)
    {
        this.MTGServiceArrivalTime = MTGServiceArrivalTime;
    }

    public int getMTGServiceTotalWaitTime()
    {
        return MTGServiceTotalWaitTime;
    }

    public void setMTGServiceTotalWaitTime(int MTGServiceTotalWaitTime)
    {
        this.MTGServiceTotalWaitTime = MTGServiceTotalWaitTime;
    }

    public int getCheckoutQueueArrivalTime()
    {
        return checkoutQueueArrivalTime;
    }

    public void setCheckoutQueueArrivalTime(int checkoutQueueArrivalTime)
    {
        this.checkoutQueueArrivalTime = checkoutQueueArrivalTime;
    }

    public int getCheckoutQueueTotalWaitTime()
    {
        return checkoutQueueTotalWaitTime;
    }

    public void setCheckoutQueueTotalWaitTime(int checkoutQueueTotalWaitTime)
    {
        this.checkoutQueueTotalWaitTime = checkoutQueueTotalWaitTime;
    }

    public int getCheckoutServiceArrivalTime()
    {
        return checkoutServiceArrivalTime;
    }

    public void setCheckoutServiceArrivalTime(int checkoutServiceArrivalTime)
    {
        this.checkoutServiceArrivalTime = checkoutServiceArrivalTime;
    }

    public int getCheckoutServiceTotalWaitTime()
    {
        return checkoutServiceTotalWaitTime;
    }

    public void setCheckoutServiceTotalWaitTime(int checkoutServiceTotalWaitTime)
    {
        this.checkoutServiceTotalWaitTime = checkoutServiceTotalWaitTime;
    }

    public int getSystemTotalWaitTime()
    {
        return systemTotalWaitTime;
    }

    public void setSystemTotalWaitTime(int systemTotalWaitTime)
    {
        this.systemTotalWaitTime = systemTotalWaitTime;
    }

    public int getSystemExitTime()
    {
        return systemExitTime;
    }

    public void setSystemExitTime(int systemExitTime)
    {
        this.systemExitTime = systemExitTime;
    }
}