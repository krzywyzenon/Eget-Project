package graphics;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameOver {
	JFrame overScreen;
	JLabel overLabel;
	GameOver(){
		overScreen = new JFrame();
		overScreen.setSize(555, 555);
		overScreen.setVisible(true);
		overScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		overLabel = new JLabel("Game Over");
		
		overScreen.setLayout(new GridLayout(1,1));
		
		overScreen.add(overLabel);
		
	}

}
