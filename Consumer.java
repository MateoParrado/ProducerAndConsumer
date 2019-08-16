package start;

import java.util.Random;

public class Consumer extends Thread{
	
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
