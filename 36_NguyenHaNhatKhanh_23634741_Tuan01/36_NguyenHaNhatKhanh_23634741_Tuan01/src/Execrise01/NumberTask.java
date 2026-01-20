package Execrise01;

public class NumberTask implements Runnable{

	@Override
	public void run() {
		for(int i = 0 ; i <= 10 ; i++) {
			System.out.println(i);
		}
		
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new NumberTask());
		Thread t2 = new Thread(new NumberTask());
		
		t1.start();
		t2.start();
	}

}
