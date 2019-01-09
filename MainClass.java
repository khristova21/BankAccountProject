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
				
				
				while(!(MainClass.isNumeric(accNum)) || Integer.parseInt(accNum) < 0 || Integer.parseInt(accNum) > accounts.size())
				{
					System.out.print("Sorry that is not a valid number. Please enter your Account Number:");
					accNum = in.nextLine();
				}				
				//go through a loop to find the account through the account number
				int correctNum = 0;

				if(accounts.size()>= 0)    //there's at least one account
				{
				
					for(int i = 0; i < accounts.size(); i++)
					{			
						if(accounts.get(i).getAccountNumber() == Integer.parseInt(accNum))
						
							correctNum = Integer.parseInt(accNum);
					}
					while(correctNum == 0)    //for loop has sampled through all the accounts and was not able to find and change to the correct Account Number
						{
							System.out.print("That is an invalid number. You could re-enter your account number(ac) or get your account number by entering your name(name)");
							String try2 = in.nextLine();
							
							while(try2.equals("ac") == false && try2.equals("name") == false)
							{  
								System.out.print("That is not an option. Either choose to re-enter your number (ac) or use your name (name).");
								try2 = in.nextLine();
							}
							switch(try2)
							{
								case("ac"):
								{
								
									System.out.print("Re-enter your account number:");
									accNum = in.nextLine();
									while(!(MainClass.isNumeric(accNum)) || Integer.parseInt(accNum) < 0 || Integer.parseInt(accNum) > accounts.size())
									{
										System.out.print("Sorry that is not a number. Please enter your Account Number:");
										accNum = in.nextLine();
									}				
									
										//go through accounts again
										for(int j = 0; j < accounts.size(); j++)
										{
											if(accounts.get(j).getAccountNumber() == Integer.parseInt(accNum))
												correctNum = Integer.parseInt(accNum);
										}
								}
								case("name"):
								{
									//USE THE NAME INPUTTED TO FIND THE ACCOUNT NUMBER AND RETURN IT 
									
									
									
									/**
									 * enter their name and return each of their accounts 
									 * and whether it is checking or savings). 
									 * use the instanceof operator to display properly.
									 * 
									 * 
									 */
								
									System.out.print("What is your name?");
									String name = in.nextLine();
																	
									for(int j = 0; j < accounts.size(); j++)
									{
										if(accounts.get(j).getName().equals(name))
										{
											correctNum  =  accounts.get(j).getAccountNumber();
											System.out.print("Your account number is "  + correctNum);
											
											
											//checkingAccount instanceOf bankAccount
											
											
											//idk WHAT TO DO HEREEEEEE!
											
											
											
											
											
											
											
											
											
											
											
											
											
										}
									}
									if(correctNum == 0)
										System.out.print("ERROR: Your name was not found.");
								}
							
							}
						}		
				}
				else
					System.out.print("There are currently no accounts. You need to make an account first.");
				
				
				
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
							System.out.print("Sorry that is not a number. How much would you like to withdraw:");
							amount = in.nextLine();
						}						
						try 
						{
							accounts.get(correctNum).withdraw(Double.parseDouble(amount));
						
						}
						catch(IllegalArgumentException e)
						//DO NOT REPROMPT JUST SAY IT IS INVALID AND DOES NOT WORK
						{
							System.out.print("ERROR: TRANSACTION NOT AUTHORIZED");
						
							
							
							//DO I NEED A BREAK HERE?????????????
						}
						System.out.println("Withdraw: FINISHED");
					
					}
				
				//deposit
				case("dep"):
				{
					System.out.print("How much would you like to deposit into your acccount?");
					String amount = in.nextLine();

					while(!(MainClass.isNumeric(amount)))
					{
						System.out.print("Sorry that is not a number. Please enter how much you would like to deposit to your balance:");
						amount = in.nextLine();
					}
					try 
					{
						accounts.get(correctNum).deposit(Double.parseDouble(amount));
					
					}
					catch(IllegalArgumentException e)
					{
						System.out.print("ERROR: TRANSACTION NOT AUTHORIZED");
				
					}
					System.out.println("Deposit: FINISHED");
				}
				
				//transfer
				case("t"):
				{
					System.out.print("How much money would you like to transer out from your account?");
					String amount = in.nextLine();
					
					while(!(MainClass.isNumeric(amount)))
					{
						System.out.print("That is not an a number. Please enter the amount you'd like to transfer from your acccount:");
						amount = in.nextLine();
					}
				
					//GET OTHER ACCOUNT NUMBER
					
					System.out.print("What is the number of the account you'd like to transfer $" + Double.parseDouble(amount)+ " to?" );
					String otherAccount = in.nextLine();
					
					//cycle through accounts to find that number and make sure it is there
					int otherCorrectNum = 0;
					for(int i = 0; i < accounts.size(); i++)
					{			
						if(accounts.get(i).getAccountNumber() == Integer.parseInt(otherAccount))
						
							otherCorrectNum = Integer.parseInt(otherAccount);
					}
					
					while(!(MainClass.isNumeric(otherAccount)) || otherCorrectNum == 0)
					{
						System.out.print("That is not a valid existing account number.");
						
						//DO I NEED TO ASK FOR THE ACCOUNT NUMBER OF THE OTHER ACCOUNT IN THE TRANSFER?????????????????/
						
						//otherAccount = in.nextLine();
					}
					try
					{
						accounts.get(correctNum).transfer(accounts.get(otherCorrectNum), Double.parseDouble(amount));
					}
					catch(IllegalArgumentException e)
					{
						System.out.print("ERROR: TRANSACTION NOT AUTHORIZED");

					}
				}
					
					
					
					
					
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