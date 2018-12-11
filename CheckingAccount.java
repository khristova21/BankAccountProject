
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
	
	
	
	
	
}
