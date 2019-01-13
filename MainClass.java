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
			System.out.print("Hello customer. Would you like to create a\nBank Account (acc) \nTransaction (trans)  \nTerminate (term)? ");
			String input = in.nextLine();
			while(input.equals("acc") == false && input.equals("trans") == false && input.equals("term") == false)
			{
				System.out.print("INVALID. Please select either account(acc), transaction(trans), or termination(term)");
				input = in.nextLine();
			}
			
			
			if(input.equals("acc"))
			{
				System.out.print("What is your name?");
				String name = in.nextLine();
				
				System.out.print("Would you like to create a Checking Account (check) or a Savings Acocunt (save)?");
				String type = in.nextLine();
				while(type.equals("save") == false && type.equals("check") == false)
				{
					System.out.print("INVALID. Please select either Checking Account(check) or Savings Account(save).");
					type= in.nextLine();
				}
				
				//asks user if they want an initial deposit 
				System.out.print("Are you interested in making an initial deposit? yes(y) or no(n)");
				String initDep = in.nextLine();
				
				while(initDep.equals("y") == false && initDep.equals("n") == false)
				{
					System.out.print("INVALID. Please select either yes(y) or no(n) regarding the addition of an initial deposit.");
					initDep= in.nextLine();
				}
				
				if(initDep.equals("y"))
					//ask for the initial deposit
				{
					System.out.print("What is your initial deposit?");
					String deposit = in.nextLine();

					while(!(MainClass.isNumeric(deposit)) || Double.parseDouble(deposit) < 0)
					{
						System.out.print("Sorry that is not a valid number. Please enter your initial deposit:");
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
				System.out.print("What is your Account Number?");
				String accNum = in.nextLine();
				int correctNum = 0;
				
				while(!(MainClass.isNumeric(accNum)) || Integer.parseInt(accNum) <= 0 )
				{
					System.out.print("INVALID. Please enter your Account Number:");
					accNum = in.nextLine();
				}				
				//go through a loop to find the account through the account number
				boolean incorrectNum = false;
				boolean cont = true;

				if(accounts.size() > 0)    //there's at least one account
				{
				
					for(int i = 0; i < accounts.size(); i++)
					{			
						if(i+1 == Integer.parseInt(accNum))
						{
							correctNum = i + 1;
							incorrectNum = true;
							System.out.println(accounts.get(i).getName() + ", you have $" + accounts.get(i).getBalance() + " in your account.");
						}
					}	
					while(incorrectNum == false)    //for loop has sampled through all the accounts and was not able to find and change to the correct Account Number
						{
							System.out.print("That is an invalid number. You could re-enter your account number(ac) or get your account number by using your name(name)");
							String try2 = in.nextLine();
							
							while(try2.equals("ac") == false && try2.equals("name") == false)
							{  
								System.out.print("INVALD. Choose to re-enter your number(ac) or use your name(name).");
								try2 = in.nextLine();
							}
							if(try2.equals("ac"))
							{
								
									while(correctNum == 0) 
									{
										System.out.print("Re-enter your Account Number:");
										accNum = in.nextLine();
										while(!(MainClass.isNumeric(accNum)) || Integer.parseInt(accNum) <= 0)
										{
											System.out.print("INVALID. Please enter your Account Number:");
											accNum = in.nextLine();
										}			
											//go through accounts again
											for(int i = 0; i < accounts.size(); i++)
											{
												if(i + 1 == Integer.parseInt(accNum))
												{
													correctNum  =  i+1;
													incorrectNum = true;
													System.out.println(accounts.get(i).getName() + ", you have $" + accounts.get(i).getBalance() + " in your account.");
												}
											}
											if(incorrectNum == false)
												System.out.println("That number has not been found.");
									}
									//break;
								}
							else if(try2.equals("name"))
								{
									//USE THE NAME INPUTTED TO FIND THE ACCOUNT NUMBER AND RETURN IT 
								
									while(correctNum == 0)
									{
										System.out.print("What is your name?");
										String name = in.nextLine();
										String accountType = "";
											for(int i = 0; i < accounts.size(); i++)
											{
												if(accounts.get(i).getName().equals(name))
												{
													correctNum  =  1 + i;
													
													BankAccount test = accounts.get(correctNum-1);
													incorrectNum = true;
													
													if(test instanceof CheckingAccount)
													{
														accountType = "Checking Account";
													}
													else if(test instanceof SavingsAccount) 
													{
														accountType  = "Savings Account";
													}	
													System.out.println(accounts.get(i).getName() +", your account number is "  +  correctNum + " and it is a " + accountType);
												}												
											}
											
											if(incorrectNum == false)
												System.out.println("ERROR: Your name was not found.");
									}
								}	
						}		
				}
				else
				{
					System.out.println("There are currently no accounts. You must create an account first.");
					cont = false;
				}
				
				if(cont)
				{

					System.out.print("Would you like to  Withdraw (wd), deposit (dep), transfer (t), or get an account numbers(get Acc Num)");
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
							boolean complete = true;
							
							while(!(MainClass.isNumeric(amount)))
							{
								System.out.print("Sorry that is not a number. How much would you like to withdraw:");
								amount = in.nextLine();
							}						
							try 
							{
								accounts.get(correctNum-1).withdraw(Double.parseDouble(amount));
							
							}
							catch(IllegalArgumentException e)
							//DO NOT REPROMPT JUST SAY IT IS INVALID AND DOES NOT WORK
							{
								System.out.println("ERROR: TRANSACTION NOT AUTHORIZED");
								complete = false;
							}
							if(complete)
							{
								
								System.out.println("Withdraw: COMPLETE");
								System.out.println(accounts.get(correctNum-1).getName() +", your balance is now $"  +  accounts.get(correctNum-1).getBalance());
							}
							break;
						}
					
					//deposit
					case("dep"):
					{
						System.out.print("How much would you like to deposit into your acccount?");
						String amount = in.nextLine();
						boolean complete = true;


						while(!(MainClass.isNumeric(amount)))
						{
							System.out.print("Sorry that is not a number. Please enter how much you would like to deposit to your balance:");
							amount = in.nextLine();
						}
						try 
						{
							accounts.get(correctNum-1).deposit(Double.parseDouble(amount));
						
						}
						catch(IllegalArgumentException e)
						{
							System.out.println("ERROR: TRANSACTION NOT AUTHORIZED");
							complete = false;
					
						}
						if(complete)
						{
							System.out.println("Deposit: COMPLETE");
							System.out.println(accounts.get(correctNum-1).getName() + ", you have $" + accounts.get(correctNum-1).getBalance() + " in your account.");
						}
						break;
					}
					
					//transfer
					case("t"):
					{
						System.out.print("How much money would you like to transer out from your account?");
						String amount = in.nextLine();
						boolean complete = true;
						
						while(!(MainClass.isNumeric(amount)))
						{
							System.out.print("That is not an a number. Please enter the amount you'd like to transfer from your acccount:");
							amount = in.nextLine();
						}
					
						//GET OTHER ACCOUNT NUMBER
						
						System.out.print("What is the number of the account you'd like to transfer $" + Double.parseDouble(amount)+ " to?" );
						String otherAccount = in.nextLine();
						int otherCorrectNum = 0;
						
						
						//the answer doesnt say its wrong. this is only if its correct

						for(int i = 0; i < accounts.size(); i++)
						{			
							if(i + 1 == Integer.parseInt(otherAccount))
							{
								otherCorrectNum = i + 1;
								//incorrectNum = true;
								System.out.println("That is " + accounts.get(i).getName() +"'s account and it currently holds $" + accounts.get(i).getBalance());
							}
						}							
						
						while(!(MainClass.isNumeric(otherAccount)) || otherCorrectNum == 0)
						{
							System.out.print("That is not a valid existing account number. Please enter the account number of the other acccout: ");
							otherAccount = in.nextLine();
						}
							//DO I NEED TO ASK FOR THE ACCOUNT NUMBER OF THE OTHER ACCOUNT IN THE TRANSFER?????????????????/
							
						
						try
						{
							accounts.get(correctNum-1).transfer(accounts.get(otherCorrectNum-1), Double.parseDouble(amount));
						}
						catch(IllegalArgumentException e)
						{
							System.out.println("ERROR: TRANSACTION NOT AUTHORIZED");
							complete = false;

						}

						if(complete)
						{
							System.out.println("Transer: COMPLETE");
							System.out.println(accounts.get(correctNum-1).getName() + ", you have $" + accounts.get(correctNum-1).getBalance() + " in your account.");
							System.out.println(accounts.get(otherCorrectNum-2).getName() + "'s account now has $" + accounts.get(otherCorrectNum-2).getBalance());
						}
						break;
						
					}
						
						
						
						
						
					case("get Acc Num"):
					{
						System.out.print("What is the name of the account you'd like to find?");
						String name = in.nextLine();
						int check = 0;
						
						while(check == 0)
						{
							for(int i = 0; i < accounts.size(); i++)
							{
								if(accounts.get(i).getName().equals(name))
								{
									correctNum  =  1 + i;
									check = 1;
									//incorrectNum = true;
									System.out.println("The Account Number of " + accounts.get(i).getName() +" is "  +  correctNum);
								}		
							}
						}
					}
					
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