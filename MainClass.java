import java.util.ArrayList;
import java.util.Scanner;
/**
 * MAIN CLASS
 * @author khristova21
 *
 */
public class MainClass 
{

	public static void main(String[] args)
	{

		ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
		Scanner in = new Scanner(System.in);
		final int OVER_DRAFT_FEE = 15;
		final double RATE = 0.0025;
		final double TRANSACTION_FEE = 1.5;
		final double MIN_BAL = 300;
		final double MIN_BAL_FEE = 10;
		final int FREE_TRANSACTIONS = 10;
		
		boolean loop = true;
		while(loop) 
		{
			System.out.print("Would you like to make a/n Bank Account (acc) /nTransaction (trans) /nTerminate  (term)? ");
			String input = in.nextLine();
			if(input.equals("acc"))
			{
				System.out.print("What is your name?");
				String name = in.nextLine();
				
				System.out.print("Do you want a Checking(check) Account or a Savings (save) account?");
				String type = in.nextLine();
				System.out.print("Would you like to make an initial deposit? (y/n)");
				char 
				
				if(type.equals("check"))
					accounts.add(new CheckingAccount (name, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS ));
			
				
				
				
				/**
				 * 
				 * accNum
				 * localVariable = null;
				 * for ( accNum == localVariable  )
				 * 
				 * 		check for accNum 
				 * 
				 * 
				 * localVariable = null; 
				 * if (localVariable ==null)
				 * 		prompt the user to enter correct number
				 * 
				 * 
				 */

			}
			else if(input.equals("trans")) 
			{
				
			}
			else if(input.equals("term"))
			{
				
			}
			else
				System.out.println("Please enter either acc, trans, or term: ");
		}
		
		
		
	}

}