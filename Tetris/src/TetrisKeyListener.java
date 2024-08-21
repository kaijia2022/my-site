import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class TetrisKeyListener implements KeyListener{
	
	private GamePanel whiteboard;
	private ControlPanel controlPanel;
	
	public TetrisKeyListener(GamePanel whiteboard, ControlPanel controlPanel) {
		this.whiteboard = whiteboard;
		this.controlPanel = controlPanel;
		whiteboard.addKeyListener(this);
	}
	
	public void keyPressed(KeyEvent e) {
		if(KeyEvent.VK_W == e.getKeyCode()) {
			whiteboard.rotateLeft();
		}
		else if (KeyEvent.VK_A == e.getKeyCode()) {
			whiteboard.moveCubeLeft();
		}
		else if (KeyEvent.VK_D == e.getKeyCode()) {
			whiteboard.moveCubeRight();
		}
		else if (KeyEvent.VK_RIGHT == e.getKeyCode() && e.isControlDown()) {
			controlPanel.speedUp();
		}
		else if (KeyEvent.VK_LEFT == e.getKeyCode() && e.isControlDown()) {
			controlPanel.slowDown();
		}
	}
	
	public void keyTyped(KeyEvent e){
		
	}
	public void keyReleased(KeyEvent e){
		
	}
}
