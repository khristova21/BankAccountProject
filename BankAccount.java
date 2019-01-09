/**
 * Bank Account & Polymorphism 
 * 
 * @author Krisi Hristova Pd 6
 *
 */
public abstract class BankAccount 
{
	private static int nextAccNum;
	private String name;
	private int accNum;
	private double balance;
	
	BankAccount(String n)
	{
		name = n;
		accNum = nextAccNum++;
		balance = 0;
	}
	
	BankAccount(String n, double b)
	{
		name = n;
		accNum = nextAccNum++;
		balance  = b;
		
	}
	
	
	/**
	 * @param amt inputs a certain amount from the user
	 * adds that amount into the user's balance
	 */
	public void deposit(double amt)
	{
		balance += amt;
	}
	
	/**
	 * @param amt inputs an amount
	 * withdraws (subtracts) that amount from the balance
	 */
	public void withdraw(double amt)
	{
		balance -= amt;
	}
	
	/**
	 * @return the name of the account
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * @return the balance of the account 
	 */
	public double getBalance()
	{
		return balance;
	}
	
	/**
	 * abstract method that will run the endOfMonthUpdate
	 */
	public abstract void endOfMonthUpdate();
	
	/**
	 * transfers money between accounts
	 * @param other is the other account that money will be deposited into 
	 * @param amt money that will be withdrawn from original account and deposited into the other account 
	 */
	public void transfer(BankAccount other,  double amt)
	{
		this.withdraw(amt);
		other.deposit(amt);
	}
	
	/**
	 * @return the account number of the user
	 */
	public int getAccountNumber()
	{
		return accNum;
	}
	
	/**
	 *returns account number, name, and balance
	 */
	public String toString()
	{
		return ""+ accNum + "\t" + name +"\t$" + balance; 
	}
}
