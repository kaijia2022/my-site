import javax.swing.JOptionPane;

public class TetrisHelp {
	
public static void showHelp() {
	JOptionPane.showMessageDialog(null,
			"\"W\" == rotate the shape counterclockwise. \n" +
			"\"A\" == moves the shape left. \n" +
			"\"D\" == moves the shape right. \n" +
			"Press Ctrl+RIGHT or Ctrl+LEFT for faster or slower falling shape.\n" +
			"Score of 100 is earned per row eliminated",
			"How to Play",
			JOptionPane.PLAIN_MESSAGE);
	}


public static void showAbout() {
	JOptionPane.showMessageDialog(null,
			"Tetris version 1 designed and realized by K.J.\n" +
			"published on June 3, 2021" +
			"Copyright reserved of K.J. Bran",
			"About",
			JOptionPane.PLAIN_MESSAGE);
	}			
}