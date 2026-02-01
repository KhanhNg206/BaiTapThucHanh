package exercise3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankingApp {
	public static BankingAccount account = new BankingAccount("John Smith");

	public static void main(String[] args) throws InterruptedException {

		Runnable depositTask = () -> {
			double amount = 100.0;
			account.deposit(amount);
		};
		
		Callable<Double> withdrawTask = () -> {
			double amount = 50.0;
			return account.withdraw(amount);
		};

//		ExecutorService pool = Executors.newFixedThreadPool(4);
		ExecutorService pool = Executors.newCachedThreadPool();

		for (int i = 0; i < 1_000; i++) {
			pool.submit(depositTask);
		}
		
		pool.submit(depositTask);
		
		Thread.sleep(1000);
		
		pool.submit(withdrawTask);

		pool.shutdown();
		while (!pool.isTerminated()) {
		} // wait

		System.out.println("Balance: " + account.getBalance());// 100_000 --> race condition
	}
}
