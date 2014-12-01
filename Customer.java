//Steven Ross
//smr77@pitt.edu


public class Customer
{
	private static int custInst = 0;			//Used to give unique id to each created cust--Could just use id but more clear to have different vars
	private int id;
	private double arrivalTime;
	private double exitTime;
	private String location;				//TBD-
	private String custType;				//"mtg" or "qz"

	public Customer()
	{
		this.id = custInst;
		custInst++;
	}
	
	public Customer(double time)
	{
		this.id = custInst;
		custInst++;
		
		this.arrivalTime = time;
	}

	public double getArrivalTime()
	{
		return this.arrivalTime;
	}
	
	public void setArrivalTime(double time)
	{
		this.arrivalTime = time;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setExitTime(double time)
	{
		this.exitTime = time;
	}
	
	public double getExitTime()
	{
		return this.exitTime;
	}
	
	public String getLocation()
	{
		return this.location;
	}
	
	public void setLocation(String l)
	{
		this.location = l;
	}
	
	public String getCustType()
	{
		return this.custType;
	}
	
	public void setCustType(String x )
	{
		this.custType = x;
	}
	
	public double calcTotalTimeSys()
	{
		return this.exitTime - this.arrivalTime;
	}

}