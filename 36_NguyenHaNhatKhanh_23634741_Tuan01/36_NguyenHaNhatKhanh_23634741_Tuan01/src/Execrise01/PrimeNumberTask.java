package Execrise01;

public class PrimeNumberTask implements Runnable{
private int x;

	public PrimeNumberTask(int x) {
	this.x = x;
}

	@Override
	public void run() {
		boolean isPrime = true;
		
		if(x < 2) {
			return;
		}
		for(int i = 2; i*i <= x; i++) {
			if(x % i == 0) {
				isPrime = false;
				break;
			}
			System.out.println(x+ " : "+isPrime);
		}
	
		
	}
	
	public static void main(String[] args) {
		int [] array = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		
		for (int i : array) {
			new Thread(new PrimeNumberTask(i)).start();;
		}
	}

}
