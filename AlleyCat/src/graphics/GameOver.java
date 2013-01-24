package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class GameOver {
	JDialog overScreen;
	JLabel overLabel;
	String finishing;
	JTextArea text;
	Font font;
	Font font1;
	
	public GameOver(String condition){
		font = new Font("Verdana", Font.BOLD, 14);
		font1 = new Font("Verdana", Font.BOLD, 24);
		finishing = condition;
		overScreen = new JDialog();
		overScreen.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		overScreen.addWindowListener(exitListener);
		overScreen.setSize(750, 750);
		overScreen.setVisible(true);
		overLabel = new JLabel("         GAME OVER    ");
		overLabel.setFont(font1);
		overLabel.setOpaque(true);
		overLabel.setBackground(new Color(118, 54, 18));
		overLabel.setForeground(new Color(246, 192, 83));
		text = new JTextArea();
		text.setFont(font);
		text.setBackground(new Color(118, 54, 18));
		text.setForeground(new Color(246, 192, 83));
		overScreen.setLayout(new GridLayout(2,1));
		
		
		if(condition =="victory"){
			text.setText("Congratulations! You have succesfully copmpleted demo version of this game. \nIf you want to enjoy the full version - please transfer 10000 EUR \nto the account number: 444-444-444-444. \nIf you don't want to enjoy the full version - please transfer only 5000 EUR \nto the above-mentioned account. \nSome of the full version features: \n-new playable races: dogs, parrots and bacteria, \n-new terrifying opponents: \nEVIILLLLL mice, EVIIILLLL grashoppers and EVIILLLL earthworms, \n-stunning 3D graphics, \n-...and many more");
		}else{
			text.setText("OK! So... you were defeated. By a bat. Not even Batman, but ordinary bat. \nSeriously???? \nWhy don't you consider finding another hobby rather than playing.");
		}
		overScreen.add(overLabel);
		overScreen.add(text);
		
		overScreen.pack();
		
//		for(int i=0; i<100; i++){
//			
//		}
		
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		System.exit(0);
		
		
		
	}
	
	WindowListener exitListener = new WindowAdapter() {

        @Override
        public void windowClosing(WindowEvent e) {
            int confirm = JOptionPane.showOptionDialog(null, "Are You Sure to Close Application?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (confirm == 0) {
      
            	System.exit(0);
            }
        }
    };

}
