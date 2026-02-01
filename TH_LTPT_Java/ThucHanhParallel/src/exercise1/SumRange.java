package exercise1;

import java.util.concurrent.Callable;

public class SumRange implements Callable<Long>{
	private final long start;
	private final long end;
	
	public SumRange(long start, long end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	public Long call() throws Exception {
		long total = 0L;
		
		for (long i = start; i < end; i++) {
			total += i;
		}
		
		return total;
	}
}
