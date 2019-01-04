
public class SavingsAccount extends BankAccount
{
	private double intRate;
	private final double MIN_BAL;
	private final double MIN_BAL_FEE;
	

	//constructor with and input initial deposit
	 public SavingsAccount(String n, double b, double r, double mb, double mbf)
	{
		super(n,b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	//constructor with no balance
	public SavingsAccount(String n, double r, double mb, double mbf)
	{
		super(n);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	
	/**
	 * deposits an amount and does not allow a negative to be deposited 
	 * 
	 */
	@Override
	public void deposit(double amt)
	{
		if(amt< 0)
			throw new IllegalArgumentException();
		super.deposit(amt);
	}

	
	/**
	 * withdraws amount that is positive, greater than the balance and only when the balance is positive
	 * charges MIN_BAL_FEE if the balance has less than the MIN_BAL
	 * 
	 */
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
	
	
	/**
	 * transfers amount to chosen account and makes sure the names match to transfer
	 * Does not allow transfer if the original account has a negative balance
	 */
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
	
	
	/**
	 * calculates and adds rate of the balance
	 */
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
