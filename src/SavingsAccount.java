
public class SavingsAccount extends BankAccount
{
	private double intRate;
	private final double MIN_BAL;
	private final double MIN_BAL_FEE;
	
	 public SavingsAccount(String n, double b, double r, double mb, double mbf)
	{
		super(n,b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	public SavingsAccount(String n, double r, double mb, double mbf)
	{
		super(n);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	
	@Override
	public void deposit(double amt)
	{
		if(amt< 0)
			throw new IllegalArgumentException();
		super.deposit(amt);
	}

	@Override
	public void withdraw(double amt)
	{
		if(amt <= 0 || this.getBalance() < amt || this.getBalance() < 0)
		{
			throw new IllegalArgumentException();
		}
		super.withdraw(amt);
		if(this.getBalance() < MIN_BAL) 
		{
			super.withdraw(MIN_BAL_FEE);
		}
	}
	
	@Override
	public void transfer(BankAccount other, double amt)
	{
		if(this.getName().equals(other.getName()))
			super.transfer(other, amt);
		else
			throw new IllegalArgumentException();
		
		if(this.getBalance() < 0) 
			throw new IllegalArgumentException();
		
	}
	
	public void addInterest()
	{
		super.deposit(intRate*this.getBalance()); 
	}
	
	/**
	 *adds interest 
	 */
	@Override
	public void endOfMonthUpdate()
	{	
		addInterest();
	}
	
	
}
