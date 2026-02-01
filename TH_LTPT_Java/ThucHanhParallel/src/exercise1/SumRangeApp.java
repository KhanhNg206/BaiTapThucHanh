package exercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SumRangeApp {
	public static void main(String[] args) throws InterruptedException {
		
		long range = 1_000_000L;
		int numTasks = 10;
		long subRange = range / numTasks;
		 
		List<Callable<Long>> tasks = new ArrayList<Callable<Long>>();
		
		for (int i = 0; i < numTasks; i++) {
			long start = i * subRange;
			long end = (i == numTasks - 1 ? range : start + subRange);
			Callable<Long> task = new SumRange(start, end);
			tasks.add(task);
		}
		
		ExecutorService service = Executors.newCachedThreadPool();
		List<Future<Long>> results = service.invokeAll(tasks);
		
		long total = results.stream()
				.mapToLong(result -> {
					try {
						return result.get();
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}
					return 0L;
				})
				.sum();
		System.out.println("Total: " + total);
		
		service.shutdown();
	}
}
