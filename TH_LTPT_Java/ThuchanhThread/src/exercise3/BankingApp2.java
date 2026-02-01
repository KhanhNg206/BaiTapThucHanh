package exercise3;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BankingApp2 {
	public static BankingAccount  account = new BankingAccount("John Smith", 100_000);
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		Callable<Double> withdrawTask = () -> {
			double amount = 100.0;
			return account.withdraw(amount);
		};
		
		List<Callable<Double>> tasks = new ArrayList<>();

		for (int i = 0; i < 1_000; i++) {
		    tasks.add(withdrawTask);
		}

		
//		ExecutorService pool = Executors.newFixedThreadPool(4);
		ExecutorService pool = Executors.newCachedThreadPool();
		
		List<Future<Double>> results = pool.invokeAll(tasks);		
		
		pool.shutdown();
		while(!pool.isTerminated()) {} //wait
		
		double money = 0.0;
		for(Future<Double> f: results)
			money += f.get();
		
		System.out.println("Money: " + money);//100_000
		System.out.println("Balance: " + account.getBalance());//0.0
	}
}
