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
			System.out.print("Would you like to make a\nBank Account (acc) \nTransaction (trans) \nTerminate  (term)? ");
			String input = in.nextLine();
			while(input.equals("acc") == false && input.equals("trans") == false && input.equals("term") == false)
			{
				System.out.print("That is not an option. Please select either account(acc), transactoin(trans), or termination(term)");
				input = in.nextLine();
			}
			
			
			if(input.equals("acc"))
			{
				System.out.print("What is your name?");
				String name = in.nextLine();
				
				System.out.print("Do you want a Checking(check) Account or a Savings (save) account?");
				String type = in.nextLine();
				while(type.equals("save") == false && type.equals("check") == false)
				{
					System.out.print("That is not an option. Please select either checking(check) or savings(save) account");
					type= in.nextLine();
				}
				
				//asks user if they want an initial deposit 
				System.out.print("Would you like to make an initial deposit? yes(y) or no (n)");
				String initDep = in.nextLine();
				
				while(initDep.equals("y") == false && initDep.equals("n") == false)
				{
					System.out.print("That is not an option. Please select either yes(y) or no(n) regarding the addition of an initial deposit.");
					initDep= in.nextLine();
				}
				
				if(initDep.equals("y"))
					//ask for the initial deposit
				{
					System.out.print("What is your initial deposit?");
					String deposit = in.nextLine();

					while(!(MainClass.isNumeric(deposit)))
					{
						System.out.print("Sorry that is not a number. Please enter your initial deposit:");
						deposit = in.nextLine();
					}
					
					if(type.equals("check"))
						accounts.add(new CheckingAccount (name, Double.parseDouble(deposit), OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS ));
					
					if(type.equals("save"))
						accounts.add(new SavingsAccount(name, Double.parseDouble(deposit), RATE, MIN_BAL, MIN_BAL_FEE ));
					
				}
		
				else if(initDep.equals("n"))	
				{
					//adds checking or savings account with no initial balance
					if(type.equals("check"))
						accounts.add(new CheckingAccount (name, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS ));
					if(type.equals("save"))
						accounts.add(new SavingsAccount(name, RATE, MIN_BAL, MIN_BAL_FEE ));
				}
				
				
			}
			//when user wants a transaction 
			else if(input.equals("trans")) 
			{
				System.out.print("What is your account number?");
				String accNum = in.nextLine();
				
				while(!(MainClass.isNumeric(accNum)))
				{
					System.out.print("Sorry that is not a number. Please enter your Account Number:");
					accNum = in.nextLine();
				}
				Integer.parseInt(accNum);
				
				//ASK TO GET THE ACCOUNT NUMBER IF WHAT IS TYPED IS WRONG
				//IDENTIFY WITH NAME OF THE ACCOUNT
				
			
				
				//go through a loop to find the account through the account number
				for(int i = 0; i < accounts.size();i++)
				{
					//if(accNum )
				}			
				String checker = null;
				//for(accNum == checker)
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
				
				
				
				
				

				System.out.print("Would you like to  Withdraw (wd), deposit (dep), transfer (t), or get an account numbers(get Acc Num");
				String answer = in.nextLine();
				

				while(answer.equals("wd")== false && answer.equals("dep") == false && answer.equals("t") == false && answer.equals("get Acc Num") == false)
				{
					System.out.print("That is not an option. Please select to either withdraw(wd), deposit (dep), transfer (t), or get an account number (get Acc Num).");
					answer= in.nextLine();
				}
				
				switch (answer)
				{
				//withdraw
				case ("wd"):
				{
						
					
						System.out.print("How much would you like to withdraw from your acccount?");
						String amount = in.nextLine();
						
						
						while(!(MainClass.isNumeric(amount)))
						{
							System.out.print("Sorry that is not a number. Please enter your initial deposit:");
							amount = in.nextLine();
						}
						
						//need to have the account number for this 
						//accounts.get(accNum).withdraw(amount);
						
						try 
						{
							accounts.get(accNum).withdraw(amount);
						
						}
						catch(IllegalArgumentException e)
						{
							System.out.print("Sorry you cannot withdraw that amount. Try again");
							amount = in.nextLine();
							
							
							while(!(MainClass.isNumeric(amount)))
							{
								System.out.print("Sorry that is not a number. Please enter your initial deposit:");
								amount = in.nextLine();
							}
						}
						System.out.println("Withdraw: FINISHED");
						
					}
				
				//deposit
				case("dep"):
				{
					System.out.print("How much would you like to deposit your acccount?");
					String amount = in.nextLine();

					while(!(MainClass.isNumeric(amount)))
					{
						System.out.print("Sorry that is not a number. Please enter how much you would like to deposit to your balance:");
						amount = in.nextLine();
					}
					
					
					

				}
				
				//transfer
				case("t"):
					
					
					
					
					
				//case("get Acc Num"):
					
					
					
				}
				
				
				
				
				
			}
			//user wants to terminate
			else if(input.equals("term"))
			{
				System.out.println("Hope to see you soon.");
				loop = false;
			}
		}
		
		

		
	}
	
	private static boolean isNumeric(String str)
	{
		try
		{
			Double.parseDouble(str);
			return true;
		}
	catch(IllegalArgumentException e)
		{
			return false;
		}
	}
}