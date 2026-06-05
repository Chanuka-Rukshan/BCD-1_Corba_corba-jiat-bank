package lk.jiat.bcd.server;
import BankingApp.AccountPOA;
import BankingApp.InsufficientBalance;
import java.util.HashMap;

public class AccountImpl extends AccountPOA {

    private HashMap<String, Double> db = new HashMap<>();

    public AccountImpl() {
        db.put("ACC001", 500.0);
        db.put("ACC002", 1500.0);
        db.put("ACC003", 5500.0);
        db.put("ACC004", 500.0);
        db.put("ACC005", 1000.0);
    }

    @Override
    public double getBalance(String accNo) {
        return db.getOrDefault(accNo, 0.0);
    }

    @Override
    public void deposit(String accNo, double amount) {
        double currentBalance = db.getOrDefault(accNo, 0.0);
        db.put(accNo, currentBalance + amount);
        System.out.println("Server Log: LKR " + amount + " deposited to account" + accNo);
    }

    @Override
    public void withdraw(String accNo, double amount) throws InsufficientBalance {
        double currentBalance = db.getOrDefault(accNo, 0.0);
        if (currentBalance < amount) {
            System.out.println("Server Log: Failed to withdraw requested amount");
            throw new InsufficientBalance("Transaction Denied: Insufficient Balance..!");
        }
        db.put(accNo, currentBalance - amount);
        System.out.println("Server Log: LKR " + amount + " withdrawn from account" + accNo);

    }
}
