import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;


public class TetrisMenu extends JMenuBar 
		implements ActionListener{
	private Tetris game;
	private JMenu Menu1, Menu2;
	private JMenuItem MenuItem1, MenuItem2, MenuItem3, MenuItem4, MenuItem5;
	
	public TetrisMenu(Tetris game) {
		super();
		this.game = game;
		//Game Menu
		Menu1 = new JMenu("Game");
		
		MenuItem1 = new JMenuItem("New Game");
		MenuItem1.addActionListener(this);
		MenuItem2 = new JMenuItem("Exit");
		MenuItem2.addActionListener(this);;
		MenuItem3 = new JCheckBoxMenuItem("Enable Sound");
		MenuItem3.setSelected(true);
			
		Menu1.add(MenuItem1);
		Menu1.add(MenuItem2);
		Menu1.add(MenuItem3);
		
		
		//Help Menu
		Menu2 = new JMenu("Help");
		
		MenuItem4 = new JMenuItem("Show Help");
		MenuItem4.addActionListener(this);
		MenuItem5 = new JMenuItem("Show About");
		MenuItem5.addActionListener(this);
		
		Menu2.add(MenuItem4);
		Menu2.add(MenuItem5);
		
		this.add(Menu1);
		this.add(Menu2);
		
		game.setJMenuBar(this);
	}
	
	public boolean soundEnabled() {
		if (game.isEnabled() && MenuItem3.isSelected() == true) {
			return true;
		}
		else
			return false;
	}
	
	public void actionPerformed(ActionEvent e) {
		JMenuItem src = (JMenuItem)e.getSource();
		 if (src == MenuItem1) {
			   game.newGame();
		   }
		   else if (src == MenuItem2) {
			   System.exit(0);
		   }
		   else if (src == MenuItem4) {
			   TetrisHelp.showHelp();
		   }
		   else if (src == MenuItem5) {
			   TetrisHelp.showAbout();
		   }
	}
}
