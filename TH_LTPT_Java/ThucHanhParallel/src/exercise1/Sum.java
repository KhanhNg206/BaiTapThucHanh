package exercise1;

public class Sum {
	public static void main(String[] args) {
		long range = 1_000_000L;
		long total = 0L;
		
		for (long i = 0; i < range; i++) {
			total += i;
		}
		
		System.out.println("Total: " + total);
	}
}
