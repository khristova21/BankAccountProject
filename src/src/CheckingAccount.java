
public class CheckingAccount extends BankAccount
{
	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;
	private final double FREE_TRANS;
	private int          numTransaction;
	
	//constructor with initial deposit inputted by the user
	CheckingAccount(String n, double b, double odf, double tf, int freeTrans)
	{
		super(n,b);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
		
	}
	
	//constructor for no initial deposit
	CheckingAccount(String n, double odf, double tf, int freeTrans)
	{
		super(n);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	
	
	/**
	 * deposits an amount the user inputs 
	 * does not allow deposited value to be negative
	 * charges transaction fee if numTransaction is greater then the FREE_TRANS
	 * 
	 */
	@Override
	public void deposit(double amt)
	{
		if(amt < 0)
		{
			throw new IllegalArgumentException();
		}
		numTransaction ++;
		if(numTransaction >  FREE_TRANS)
			super.withdraw(TRANSACTION_FEE);
		super.deposit(amt);	
	}
	
	
	/**
	 * withdraws the amount(amt) inputted by the user
	 * does not allow transaction if the balance or amt is negative
	 * charges transaction fee if its greater than the FREE_TRANS
	 * charges the OVER_DRAFT_FEE if the balance is negative
	 */
	@Override
	public void withdraw(double amt)
	{
		if(this.getBalance() < 0 || amt < 0)
			throw new IllegalArgumentException();
		
		super.withdraw(amt);

		numTransaction ++;
		
		if(numTransaction > FREE_TRANS)
			super.withdraw(TRANSACTION_FEE);
		
		if(this.getBalance() < 0)
			super.withdraw(OVER_DRAFT_FEE);
	}
	
	/**
	 * withdraws an inputted amount of money(amt) from the bank account and deposits to another 
	 * does not transfer if the current account has a balance less than the amount being transferred,
	 */
	
	@Override
	public void transfer(BankAccount other, double amt)
	{
		if(this.getBalance() < amt)
		{
			throw new IllegalArgumentException();	
		}
		
		if(this.getName().equals(other.getName()))
		{
			if(this.getBalance() < 0)
				throw new IllegalArgumentException();	
			super.transfer(other, amt);
		}
		else
			throw (new IllegalArgumentException());
	}
	
	/**
	 * sets the number of transactions to 0
	 */
	public void endOfMonthUpdate()
	{
		numTransaction = 0;
	}
	
}
