//Steven Ross
//smr77@pitt.edu
import java.util.*;

public class Customer
{
	private static int custInst = 0;			//Used to give unique id to each created cust--Could just use id but more clear to have different vars
	private int id;
	private double arrivalTime;
	private double exitTime;
	private String location;							//TBD-
	private boolean custType;					//True-market to go, false-quickzone
	private final double custTypeP= .7;					//P defined as going to quickzone
	

	public Customer()
	{
		this.id = custInst;
		custInst++;
		calculateType();
		
	}
	
	public Customer(double time)
	{
		this.id = custInst;
		custInst++;
		
		this.arrivalTime = time;
		calculateType();
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
	
	//True = mtg
	//false = qz
	public boolean getCustType()
	{
		return this.custType;
	}
	
	public void setCustType(boolean x )
	{
		this.custType = x;
	}
	
	public double calcTotalTimeSys()
	{
		return this.exitTime - this.arrivalTime;
	}
	private void calculateType()
	{
		Random rg = new Random();
		double R = rg.nextDouble();
		
		if( R <= custTypeP)
		{
			custType = false;
		}
		else
		{
			custType = true;
		}
	}
}