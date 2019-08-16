package start;

import java.util.Random;

public class Producer extends Thread{
	
	private Producer() {}
	
	public static Producer getInstance() {
		return ProducerInstantiator.INSTANCE;
	}
	
	
	//this makes sure that the singleton is called only once without violating thread safety
	private static class ProducerInstantiator {
		public static Producer INSTANCE = new Producer();
	}

	public void run() {	
		
		while(true) {
		
			try {
			
				sleep((new Random()).nextInt(1500) + 500);
			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//can we produce
			if(Panel.getProductsSize() < Main.MAX_PROD) {
				Panel.addProduct();
			}
		}
	}
}
