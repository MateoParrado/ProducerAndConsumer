package start;

import java.util.Random;

public class Producer extends Thread{
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
