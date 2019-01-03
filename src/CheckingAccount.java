
public class CheckingAccount extends BankAccount
{
	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;
	private final double FREE_TRANS;
	private int          numTransaction;
	
	CheckingAccount(String n, double b, double odf, double tf, int freeTrans)
	{
		super(n,b);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
		
	}
	
	CheckingAccount(String n, double odf, double tf, int freeTrans)
	{
		super(n);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	
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
	
	public void endOfMonthUpdate()
	{
		numTransaction = 0;
	}
	
}
