package start;

import javax.swing.JFrame;

public class Main {

	static JFrame frame;
	
	public static final int WIDTH = 500, HEIGHT = 700;
	
	//maximum amount of product produced at once
	public static final int MAX_PROD = HEIGHT / 110;
	
	public static void main(String[] args) {
		frame = new JFrame();
		
		frame.setSize(WIDTH, HEIGHT);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setContentPane(new Panel());
		
		frame.setVisible(true);
		
	}
	
}
