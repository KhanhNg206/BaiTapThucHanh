package exercise1;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SumRangeParallel {
	public static void main(String[] args) {
		long range = 1_000_000L;
		long threshold = 100_000L;
		
		System.out.println(Runtime.getRuntime().availableProcessors());
		
		ForkJoinPool pool = new ForkJoinPool();
		
		SumTask3 task = new SumTask3(0, range, threshold, 0);
		Long total = pool.invoke(task);
		System.out.println(total);
		
		pool.shutdown();
	}
}

class SumTask3 extends RecursiveTask<Long> {
	private final long start;
	private final long end;
	private final long threshold;
	private  int level;

	public SumTask3(long start, long end, long threshold, int level) {
		this.start = start;
		this.end = end;
		this.threshold = threshold;
		this.level = level;
	}

	@Override
	protected Long compute() {
		
		String repeat = " ".repeat(level);
		String threadName = Thread.currentThread().getName();
		
		System.out.printf("%s%s: [%s, %s)\n", repeat, threadName, start, end);
		
		if (end - start <= threshold) {
			long total = 0L;
			for (long i = start; i < end; i++) {
				total += i;
			}

			return total;
		}
		
		long mid = (start + end) / 2;
		
		SumTask3 leftTask = new SumTask3(start, mid, threshold, level + 1);
		SumTask3 rightTask = new SumTask3(mid, end, threshold, level + 1);
		leftTask.fork();
		
		Long rightResult = rightTask.compute();  
		Long leftResult = leftTask.join();
		return leftResult + rightResult;
		
	}

}
