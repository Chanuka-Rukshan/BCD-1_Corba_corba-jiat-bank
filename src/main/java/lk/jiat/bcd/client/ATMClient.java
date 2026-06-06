package lk.jiat.bcd.client;

import BankingApp.Account;
import BankingApp.AccountHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.sql.SQLOutput;
import java.util.Scanner;

public class ATMClient {
    public static void main(String[] args) {
        ORB orb = ORB.init(args, null);

        try {
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            Account account = AccountHelper.narrow(ncRef.resolve_str("Bank"));

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter you account ID: ");
            String accountId = sc.nextLine();

            boolean running = true;

            while (running) {
                System.out.println("1. Check Balance | 2. Deposit | 3. Withdrawal | 4. Exit");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Current Balance: " + account.getBalance(accountId));
                        break;

                    case 2:
                        System.out.println("Enter amount to deposit : ");
                        double amount = sc.nextInt();
                        account.deposit(accountId, amount);
                        System.out.println("Deposit Successful");
                        System.out.println("Current Balance: " + account.getBalance(accountId));
                        break;

                    case 3:
                        System.out.println("Enter amount to withdrawal : ");
                        double withdrawalAmount = sc.nextDouble();
                        account.withdraw(accountId, withdrawalAmount);
                        System.out.println("Withdrawal Successful");
                        System.out.println("Current Balance: " + account.getBalance(accountId));
                        break;

                    case 4:
                        System.out.println("Exit Program");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid choice");

                }

            }

            sc.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
