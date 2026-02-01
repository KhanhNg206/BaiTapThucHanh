package exercise3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankingAccount {

	private static final double MAX = 100_000.0;
	private Lock lock = new ReentrantLock();
	private Condition sufficientMoney = lock.newCondition();
	private Condition insuranceLimit = lock.newCondition();

	private String owner;
	private double balance;

	public BankingAccount(String owner, double balance) {
		super();
		this.owner = owner;
		this.balance = balance;
	}

	public BankingAccount(String owner) {
		super();
		this.owner = owner;
		this.balance = 0.0;
	}

	public void deposit(double amount) {
		// business rule: amount > 0
		if (amount <= 0)
			System.out.println("Amount must be positive");

		lock.lock();
		try {
			while (balance + amount > MAX) {
				System.out.println("Restricting deposits to $100,000. Waiting ...");
				insuranceLimit.await();
			}
			balance += amount;
//			System.out.println("New balance: " + balance);
			sufficientMoney.signal();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public double withdraw(double amount) {

		if (amount <= 0)
			System.out.println("Amount must be positive");

		lock.lock();
		try {
			while (amount > balance) {
				System.out.println("Not enough money. Waiting ...");
				sufficientMoney.await();
			}
			balance -= amount;
			System.out.println("Money: " + amount + ", new balance: " + balance);
			insuranceLimit.signal();
			
			return amount;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return 0.0;
	}

	public double getBalance() {
		return balance;
	}

	public String getOwner() {
		return owner;
	}

	@Override
	public String toString() {
		return "BankingAccount [owner=" + owner + ", balance=" + balance + "]";
	}

}
