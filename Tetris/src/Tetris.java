import javax.swing.JFrame;
import javax.swing.Box;
public class Tetris extends JFrame{
	private TetrisMenu menuBar;
	private ControlPanel controlPanel;
	
	public Tetris() {
		super("Tetris");
			
			menuBar = new TetrisMenu(this);
			setJMenuBar(menuBar);
			
			GamePanel whiteboard = new GamePanel(this);
			controlPanel = new ControlPanel(whiteboard);
			whiteboard.setScoreDisplay(controlPanel);
			controlPanel.addKeyListener(new 
					TetrisKeyListener(whiteboard, controlPanel));
			Box box = Box.createHorizontalBox();
			box.add(whiteboard);
			box.add(controlPanel);
			getContentPane().add(box);
			
			newGame();		
	}
	
	public void newGame() {
		controlPanel.newGame();
	}
	
	public boolean soundEnabled() {
		return menuBar.soundEnabled();
	}
	public static void main(String[] args) {
		Tetris window = new Tetris();
		window.setBounds(100,100,540,700);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);	

	}

}
