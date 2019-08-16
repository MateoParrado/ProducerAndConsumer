package start;
import javax.swing.JPanel;
import java.awt.*;

public class Panel extends JPanel{
	
	//synchronizes production, consumption, and the movement of the blocks downwards
	public static Object key = new Object();
	
	private static Consumer consumer;
	private static Producer producer;
	
	private static Product[] products;
	
	//used to slow down execution
	private int frameCount = 0;
	
	//consumer only takes the one on the bottom
	private static Product touchingBottom = null;
	
	public Panel() {
		consumer = Consumer.getInstance();
		producer = Producer.getInstance();
		
		products = new Product[Main.MAX_PROD];
		
		producer.start();
		
		//give the producer a head start
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		consumer.start();
		
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics graphics) {		
		Graphics2D g = (Graphics2D) graphics;

		frameCount++;
		
		
		//MOVEMENT, SYNCHRONIZED SO IT DOESNT MOVE NULL PTRS TAKEN BY CONSUMER OR IGNORE NON NULLS FROM PRODUCER
		synchronized(key) {
			if(frameCount % 10 == 0) {
				g.setColor(Color.BLACK);
				g.fillRect(0,  0,  Main.WIDTH,  Main.HEIGHT);
				
				g.setColor(Color.GREEN);
				for(int i = 0; i < Main.MAX_PROD; i++) {
					if(products[i] != null) {
						g.fillRect(50, products[i].y, 100, 100);
						
						//if its greater than HEIGHT - 150 its touching the bottom
						if(products[i].y < Main.HEIGHT - 150) {
							boolean move = true;
							
							//check for intersection with all other squares
							for(int j = 0; j < Main.MAX_PROD; j++) {
								if(j == i || products[j] == null)
									continue;
								
								if(products[j].y > products[i].y && products[i].y + 110 > products[j].y) {
									move = false;
									break;
								}
								
							}
							
							if(move)
								products[i].y++;
						}
						else {
							touchingBottom = products[i];
						}
					}
				}
			}
		}
		
		repaint();
	}
	
	//remove the square touching the bottom (called by consumer)
	public static void removeTouchingBottom() {
		synchronized(key){
			for(int i = 0; i < Main.MAX_PROD; i++) {
				if(products[i] == touchingBottom) {
					products[i] = null;
				}
			}
		}
		
		touchingBottom = null;
	}

	//returns touchingBottom, used by consumer to check if touchingBottom is null
	public static Product getTouchingBottom() {
		return touchingBottom;
	}
	
	//used by producer to check if it should produce
	public static int getProductsSize() {
		int ret = 0;
		
		for(int i = 0; i < Main.MAX_PROD; i++) {
			if(products[i] != null)
				ret++;
		}
		
		return ret;
	}
	
	//called by producer
	public static void addProduct() {
		synchronized(key) {
			for(int i = 0; i < Main.MAX_PROD; i++) {
				if(products[i] == null) {
					products[i] = new Product();
					break;
				}
			}
		}
	}
}
