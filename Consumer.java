package start;

import java.util.Random;

public class Consumer extends Thread{
	
	private Consumer() {}
	
	public static Consumer getInstance() {
		return ConsumerInstantiator.INSTANCE;
	}
	
	
	//this makes sure that the singleton is called only once without violating thread safety
	private static class ConsumerInstantiator {
		public static Consumer INSTANCE = new Consumer();
	}
	
	public void run() {	
	
		while(true) {
			
			
			try {
			
				sleep((new Random()).nextInt(1500) + 500);
			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//should we consume?
			if(Panel.getTouchingBottom() != null) {
				Panel.removeTouchingBottom();
			}
		}
	}
}
